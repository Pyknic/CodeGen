/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.codegen.examples;

import com.speedment.codegen.Formatting;
import static com.speedment.codegen.Formatting.ucfirst;
import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.DefaultGenerator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.java.JavaInstaller;
import com.speedment.codegen.lang.controller.AutoImports;
import com.speedment.codegen.lang.controller.AutoJavadoc;
import java.util.ArrayList;
import java.util.List;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Constructor;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.File;
import com.speedment.codegen.lang.models.Import;
import com.speedment.codegen.lang.models.Interface;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.Type;
import static com.speedment.codegen.lang.models.constants.DefaultAnnotationUsage.OVERRIDE;
import static com.speedment.codegen.lang.models.constants.DefaultType.VOID;
import static com.speedment.codegen.lang.models.constants.DefaultType.list;
import static com.speedment.codegen.lang.models.constants.DefaultType.map;
import com.speedment.codegen.lang.models.values.ReferenceValue;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author Emil Forslund
 */
public class TransformTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Generator gen = new DefaultGenerator(new TransformInstaller());
        
        Table user = new Table("User");
        Table picture = new Table("Picture");
        Table comment = new Table("Comment");
        
        Formatting.tab("    ");
        
        user.setColumns(Arrays.asList(
            new Column("id").setType(Integer.class),
            new Column("username").setType(String.class),
            new Column("password").setType(String.class),
            new Column("registered").setType(LocalDateTime.class)
        ));
        
        picture.setColumns(Arrays.asList(
            new Column("id").setType(Integer.class),
            new Column("title").setType(String.class),
            new Column("description").setType(String.class),
            new Column("imgData").setType(String.class)
        ));
        
        comment.setColumns(Arrays.asList(
            new Column("id").setType(Integer.class),
            new Column("user").setType(Integer.class),
            new Column("text").setType(String.class)
        ));
        
        System.out.println("***************************");
        System.out.println(gen.metaOn(user).map(m -> m.getResult()).collect(Collectors.joining("\n***************************\n")));
        System.out.println("***************************");
        System.out.println(gen.metaOn(user).map(m -> m.getResult()).collect(Collectors.joining("\n***************************\n")));
        System.out.println("***************************");
        System.out.println(gen.metaOn(user).map(m -> m.getResult()).collect(Collectors.joining("\n***************************\n")));
        System.out.println("***************************");
    }
    
    public static class TransformInstaller extends JavaInstaller {
        public TransformInstaller() {
            super("TransformInstaller");
            install(Table.class, File.class, EntityTransform.class);
            install(Table.class, File.class, ManagerTransform.class);
            install(Column.class, Field.class, ColumnTransform.class);
        }
    }
    
    public static class ManagerTransform implements Transform<Table, File> {

        @Override
        public Optional<File> transform(Generator gen, Table table) {
            final File file = File.of("org/example/" + ucfirst(table.getName()) + "Mgr.java");
            
            final Class manager = Class.of(ucfirst(table.getName()) + "Mgr").public_().final_();
            file.add(manager);
            
            final Type self = Type.of("org.example." + ucfirst(table.getName()) + "Mgr");
            final Type entity = Type.of("org.example." + ucfirst(table.getName()));
            file.add(Import.of(entity));
            
            gen.metaOn(table.getColumns(), Field.class).forEachOrdered(meta -> {
                final Field f = meta.getResult().private_();

                file.add(Import.of(Type.of(ConcurrentHashMap.class)));
                file.add(Import.of(Type.of(ArrayList.class)));
                
                manager.add(Field.of("entitiesBy" + ucfirst(f.getName()), map(f.getType(), list(entity)))
                    .set(new ReferenceValue("new ConcurrentHashMap<>();")));

                manager.add(Method.of("findBy" + ucfirst(f.getName()), self)
                    .public_()
                    .add(f).add("return this.entitiesBy" + ucfirst(f.getName()) + ".computeIfAbsent(" + f.getName() + ", () -> new ArrayList<>());")
                );
            });

            manager.add(Constructor.of().private_());
            manager.add(Field.of("INST", self).final_().static_().set(new ReferenceValue("new " + ucfirst(table.getName()) + "();")));
            manager.add(Method.of("inst", self).public_().static_().add("return INST;"));
            
            file.call(new AutoImports(gen.getDependencyMgr()));
            file.call(new AutoJavadoc());

            return Optional.of(file);
        }
    }

    public static class EntityTransform implements Transform<Table, File> {

        @Override
        public Optional<File> transform(Generator gen, Table table) {
            final Interface entity = Interface.of(ucfirst(table.getName())).public_();
            final File file = File.of("org/example/" + entity.getName() + ".java");
            
            final Class impl = Class.of(table.getName() + "Impl").public_();
            impl.setSupertype(Type.of("org.example." + entity.getName()));

            gen.metaOn(table.getColumns(), Field.class).forEachOrdered(meta -> {
                final Field f = meta.getResult().private_();

                entity.add(getter(f));

                impl.add(f);
                impl.add(setter(f));
                impl.add(getter(f).add(OVERRIDE));
            });

            entity.add(impl);
            file.add(entity);
            file.call(new AutoImports(gen.getDependencyMgr()));
            file.call(new AutoJavadoc());

            return Optional.of(file);
        }

        private static Method setter(Field field) {
            return Method.of("set" + ucfirst(field.getName()), VOID)
                .public_().add(field)
                .add("this." + field.getName() + " = " + field.getName());
        }

        private static Method getter(Field field) {
            return Method.of("get" + ucfirst(field.getName()), field.getType())
                .public_()
                .add("return " + field.getName() + ";");
        }
    }

    public static class ColumnTransform implements Transform<Column, Field> {

        @Override
        public Optional<Field> transform(Generator gen, Column model) {
            return Optional.of(Field.of(
                model.getName(), 
                Type.of(model.getType())
            ));
        }
    }

    public static class Table {
        private final String name;
        private List<Column> columns = new ArrayList<>();

        public Table(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public List<Column> getColumns() {
            return columns;
        }

        public Table setColumns(List<Column> columns) {
            this.columns = columns;
            return this;
        }
    }

    public static class Column {
        private final String name;
        private java.lang.Class<?> type;

        public Column(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public java.lang.Class<?> getType() {
            return type;
        }

        public Column setType(java.lang.Class<?> type) {
            this.type = type;
            return this;
        }
    }
}
/*
 * Copyright 2015 Emil Forslund.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.speedment.codegen.examples;

import com.speedment.codegen.Formatting;
import static com.speedment.codegen.Formatting.ucfirst;
import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.MultiGenerator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.java.JavaInstaller;
import java.util.ArrayList;
import java.util.List;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Interface;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.Type;
import static com.speedment.codegen.lang.models.constants.DefaultAnnotationUsage.OVERRIDE;
import static com.speedment.codegen.lang.models.constants.DefaultType.VOID;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class TransformTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Generator gen = new MultiGenerator(new TransformInstaller());
        
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
        System.out.println(gen.on(user).get());
        System.out.println("***************************");
        System.out.println(gen.on(picture).get());
        System.out.println("***************************");
        System.out.println(gen.on(comment).get());
        System.out.println("***************************");
    }
    
    public static class TransformInstaller extends JavaInstaller {
        public TransformInstaller() {
            super("TransformInstaller");
            install(Table.class, Interface.class, EntityTransform.class);
            install(Column.class, Field.class, ColumnTransform.class);
        }
    }

    public static class EntityTransform implements Transform<Table, Interface> {

        @Override
        public Optional<Interface> transform(Generator gen, Table table) {
            final Interface entity = Interface.of(table.getName()).public_();
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

            return Optional.of(entity);
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
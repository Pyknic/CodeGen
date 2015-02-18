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
package com.speedment.codegen.jdbc.views;

import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.jdbc.models.Column;
import com.speedment.codegen.jdbc.models.Index;
import com.speedment.codegen.jdbc.models.Table;
import com.speedment.codegen.lang.controller.AutoImports;
import com.speedment.codegen.lang.controller.AutoJavadoc;
import com.speedment.codegen.lang.controller.FinalParameters;
import com.speedment.codegen.lang.controller.SetGetAdd;
import java.util.Optional;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Javadoc;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.constants.Default;
import static com.speedment.codegen.lang.models.constants.Default.*;
import java.sql.JDBCType;

/**
 *
 * @author Emil Forslund
 */
public class TableView implements CodeView<Table> {
	private final static String FK = "FK";
	
	@Override
	public Optional<String> render(CodeGenerator cg, Table model) {
		final Class entity = new Class("org.example.authexample." + model.getName())
			.public_().add(Default.GENERATED);
		model.getComment().ifPresent(cmt -> entity.setJavadoc(new Javadoc(cmt)));

		model.getColumns().forEach(col -> {
			final Field field = new Field(
				varName(col.getName()), 
				jdbcToModel(col.getType())
			);
			
			col.getComment().ifPresent(jd -> field.setJavadoc(new Javadoc(jd)));
			col.getDefaultValue().ifPresent(dv -> field.setValue(dv));
			
			renderForeignKey(entity, col);
			renderIndexes(entity, col);
			
			entity.add(field);
		});
		
		entity
			.call(new SetGetAdd())
			.call(new FinalParameters())
			.call(new AutoJavadoc())
			.call(new AutoImports(cg.getDependencyMgr()));
		
		return cg.on(entity);
	}
	
	private void renderForeignKey(Class cls, Column col) {
		col.getForeignKey().ifPresent(fk -> {
			final String target = className(fk.getTargetTable().getName());
			
			cls.add(new Method(
				"get" + target + FK,
				new Type(target)
			).public_().setJavadoc(new Javadoc(
				"Returns the foreign " + target + 
				" referenced in the column '" + col.getName() + "'."
			)).add(
				"return " + target + ".findBy" + 
				ucfirst(fk.getTargetColumn().getName()) + 
				"(" + varName(col.getName()) + ");"
			));
		});
	}
	
	private void renderIndexes(Class cls, Column col) {
		col.getIndexes().forEach(idx -> {
			final Type returnType;
			
			if (idx.getType() == Index.Type.INDEX) {
				returnType = Default.list(cls.asType());
			} else {
				returnType = cls.asType();
			}
			
			cls.add(new Method(
				"findBy" + ucfirst(col.getName()),
				returnType
			).public_().static_().setJavadoc(new Javadoc(
				"Returns the object with the specified unique value."
			))
			.add(new Field(varName(col.getName()), jdbcToModel(col.getType())))
			.add(
				"return " + ucfirst(shortName(cls.getName())) + "Mgr.inst().findBy" + 
				ucfirst(col.getName()) + 
				"(" + varName(col.getName()) + ");"
			));
		});
	}
	
	private static String className(String cls) {
		return ucfirst(cls);
	}
	
	private static String varName(String var) {
		return lcfirst(var);
	}
	
	private static Type jdbcToModel(JDBCType jdbcType) {
		switch (jdbcType) {
			case BIGINT :				return LONG;
			case INTEGER :				return INT;
			case SMALLINT :				return SHORT;
			case NUMERIC : case FLOAT : return DOUBLE;
			case BIT : case BOOLEAN :	return BOOLEAN;
			case CHAR : case VARCHAR :	return STRING;
				
			default: throw new UnsupportedOperationException(
				"Attempting to model datatype '" + jdbcType + "' that " +
				"has not yet been implemented."
			);
		}
	}
}
package com.speedment.codegen.jdbc.views;

import static com.speedment.codegen.Formatting.ifelse;
import static com.speedment.codegen.Formatting.lcfirst;
import static com.speedment.codegen.Formatting.ucfirst;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.jdbc.models.Table;
import com.speedment.codegen.lang.controller.SetGet;
import java.util.Optional;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Javadoc;
import com.speedment.codegen.lang.models.Type;
import static com.speedment.codegen.lang.models.constants.Default.*;
import java.sql.Types;

/**
 *
 * @author Emil Forslund
 */
public class TableView implements CodeView<Table> {
	private final static String FK = "FK";
	
	@Override
	public Optional<String> render(CodeGenerator cg, Table model) {
		final Class entity = new Class(ucfirst(model.getName()));
			
		ifelse(model.getComment(), c -> entity.setJavadoc(new Javadoc(c)), null);
		
		model.getColumns().forEach(c -> {
			final Field field = new Field(
				lcfirst(c.getName()), 
				jdbcToModel(c.getType().getJdbcType())
			);
			
			ifelse(c.getComment(), x -> field.setJavadoc(new Javadoc(x)), null);
			ifelse(c.getDefaultValue(), x -> field.setValue(x), null);
			
			entity.add(field);
		});
		
		entity.call(new SetGet());
		
		return cg.on(entity);
	}
	
	private static Type jdbcToModel(int jdbcType) {
		switch (jdbcType) {
			case Types.BIGINT : return LONG;
			case Types.INTEGER : return INT;
			case Types.SMALLINT : return SHORT;
			case Types.NUMERIC : case Types.FLOAT : return DOUBLE;
			case Types.BIT : case Types.BOOLEAN : return BOOLEAN;
			case Types.CHAR : case Types.VARCHAR : return STRING;
				
			default: throw new UnsupportedOperationException(
				"Attempting to model datatype '" + jdbcType + "' that " +
				"has not yet been implemented."
			);
		}
	}
}
package com.speedment.codegen.jdbc;

import com.speedment.codegen.base.DefaultInstaller;
import com.speedment.codegen.jdbc.models.Table;
import com.speedment.codegen.jdbc.views.TableView;

/**
 *
 * @author Emil Forslund
 */
public class JDBCInstaller extends DefaultInstaller {
	public JDBCInstaller() {
		super();
		install(Table.class, TableView.class);
	}
}
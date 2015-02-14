package com.speedment.codegen.jdbc.models;

import com.speedment.codegen.lang.interfaces.Copyable;
import java.sql.Types;

/**
 *
 * @author Emil Forslund
 */
public enum ColumnType implements Copyable<ColumnType> {
	BIT(Types.BIT),
	TINYINT(Types.TINYINT),
	SMALLINT(Types.SMALLINT),
	INTEGER(Types.INTEGER),
	BIGINT(Types.BIGINT),
	FLOAT(Types.FLOAT),
	REAL(Types.REAL),
	DOUBLE(Types.DOUBLE),
	NUMERIC(Types.NUMERIC),
	DECIMAL(Types.DECIMAL),
	CHAR(Types.CHAR),
	VARCHAR(Types.VARCHAR),
	LONGVARCHAR(Types.LONGVARCHAR),
	DATE(Types.DATE),
	TIME(Types.TIME),
	TIMESTAMP(Types.TIMESTAMP),
	BINARY(Types.BINARY),
	VARBINARY(Types.VARBINARY),
	LONGVARBINARY(Types.LONGVARBINARY),
	NULL(Types.NULL),
	OTHER(Types.OTHER),
	JAVA_OBJECT(Types.JAVA_OBJECT),
	DISTINCT(Types.DISTINCT),
	STRUCT(Types.STRUCT),
	ARRAY(Types.ARRAY),
	BLOB(Types.BLOB),
	CLOB(Types.CLOB),
	REF(Types.REF),
	DATALINK(Types.DATALINK),
	BOOLEAN(Types.BOOLEAN),
	ROWID(Types.ROWID),
	NCHAR(Types.NCHAR),
	NVARCHAR(Types.NVARCHAR),
	LONGNVARCHAR(Types.LONGNVARCHAR),
	NCLOB(Types.NCLOB),
	SQLXML(Types.SQLXML),
	REF_CURSOR(Types.REF_CURSOR),
	TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE),
	TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE);
	
	private final int value;
	
	private ColumnType(int jdbcType) {
		value = jdbcType;
	}
	
	public int getJdbcType() {
		return value;
	}

	@Override
	public ColumnType copy() {
		return this;
	}
}

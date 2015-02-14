package com.speedment.codegen.jdbc.models;

import com.speedment.codegen.lang.interfaces.Commentable;
import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.codegen.lang.interfaces.Nameable;
import com.speedment.codegen.lang.models.Value;
import com.speedment.codegen.lang.models.constants.Default;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Column implements 
		Copyable<Column>,
		Nameable<Column>,
		Commentable<Column> {
	
	private String name;
	private ColumnType type;
	private int size = 11;
	private boolean nullable = true;
	private boolean increasing = true;
	private Optional<Value> defaultValue;
	private Optional<String> comment;
	private Optional<ForeignKey> foreignKey;
	private final List<Index> indexes;
	
	public Column(String name, ColumnType type) {
		this.name			= name;
		this.type			= type;
		this.defaultValue	= Optional.empty();
		this.comment		= Optional.empty();
		this.foreignKey		= Optional.empty();
		this.indexes		= new ArrayList<>();
	}
	
	private Column(Column prototype) {
		this.name			= prototype.name;
		this.type			= prototype.type;
		this.size			= prototype.size;
		this.nullable		= prototype.nullable;
		this.increasing		= prototype.increasing;
		this.defaultValue	= Copier.copy(prototype.defaultValue);
		this.comment		= Copier.copy(prototype.comment, s -> s);
		this.foreignKey		= Copier.copy(prototype.foreignKey);
		this.indexes		= Copier.copy(prototype.indexes);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Column setName(String name) {
		this.name = name;
		return this;
	}

	public ColumnType getType() {
		return type;
	}
	
	public Column setType(ColumnType type) {
		this.type = type;
		return this;
	}
	
	public int getSize() {
		return size;
	}

	public Column setSize(int size) {
		this.size = size;
		return this;
	}

	public boolean isNullable() {
		return nullable;
	}

	public Column setNullable(boolean nullable) {
		this.nullable = nullable;
		return this;
	}

	public boolean isIncreasing() {
		return increasing;
	}

	public Column setIncreasing(boolean increasing) {
		this.increasing = increasing;
		return this;
	}

	public Optional<Value> getDefaultValue() {
		return defaultValue;
	}

	public Column setDefaultValue(Value defaultValue) {
		this.defaultValue = Optional.of(defaultValue);
		return this;
	}
	
	@Override
	public Optional<String> getComment() {
		return comment;
	}

	@Override
	public Column setComment(String comment) {
		this.comment = Optional.of(comment);
		return this;
	}

	public Optional<ForeignKey> getForeignKey() {
		return foreignKey;
	}

	public Column setForeignKey(ForeignKey foreignKey) {
		this.foreignKey = Optional.of(foreignKey);
		return this;
	}
	
	public List<Index> getIndexes() {
		return indexes;
	}
	
	public Column add(Index index) {
		indexes.add(index);
		return this;
	}
	
	@Override
	public Column copy() {
		return new Column(this);
	}
}

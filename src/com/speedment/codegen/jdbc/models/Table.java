package com.speedment.codegen.jdbc.models;

import com.speedment.codegen.lang.interfaces.Commentable;
import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.codegen.lang.interfaces.Nameable;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Table implements
		Copyable<Table>,
		Nameable<Table>,
		Commentable<Table> {
	
	private String name;
	private Optional<String> comment;
	private final List<Column> columns;
	
	public Table(String name) {
		this.name			= name;
		this.comment		= Optional.empty();
		this.columns		= new ArrayList<>();
	}
	
	private Table(Table prototype) {
		this.name			= prototype.name;
		this.comment		= Copier.copy(prototype.comment, s -> s);
		this.columns		= Copier.copy(prototype.columns);
	}

	@Override
	public Table setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Optional<String> getComment() {
		return comment;
	}

	@Override
	public Table setComment(String comment) {
		this.comment = Optional.of(comment);
		return this;
	}
	
	public List<Column> getColumns() {
		return columns;
	}
	
	public Table add(Column column) {
		columns.add(column);
		return this;
	}
	
	@Override
	public Table copy() {
		return new Table(this);
	}
}
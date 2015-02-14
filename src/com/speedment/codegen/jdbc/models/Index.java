package com.speedment.codegen.jdbc.models;

import com.speedment.codegen.lang.interfaces.Commentable;
import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.codegen.lang.interfaces.Nameable;
import com.speedment.util.Copier;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Index implements
	Copyable<Index>,
	Nameable<Index>,
	Commentable<Index> {
	
	private String name;
	private Type jdbcType;
	private Column column;
	private Optional<String> comment;
	
	public static enum Type {
		INDEX, UNIQUE, PRIMARY
	}
	
	public Index(String name, Type jdbcType) {
		this.name		= name;
		this.jdbcType	= jdbcType;
	}
	
	private Index(Index prototype) {
		this.name		= prototype.name;
		this.jdbcType	= prototype.jdbcType;
		this.column		= prototype.column.copy();
		this.comment	= Copier.copy(prototype.comment, s -> s);
	}

	@Override
	public Index setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	public Type getJdbcType() {
		return jdbcType;
	}

	public Index setJdbcType(Type type) {
		this.jdbcType = type;
		return this;
	}
	
	public Column getColumn() {
		return column;
	}
	
	public Index setColumn(Column column) {
		this.column = column;
		return this;
	}
	
	@Override
	public Optional<String> getComment() {
		return comment;
	}

	@Override
	public Index setComment(String comment) {
		this.comment = Optional.of(comment);
		return this;
	}
	
	@Override
	public Index copy() {
		return new Index(this);
	}
}

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
public class ForeignKey implements 
		Copyable<ForeignKey>,
		Nameable<ForeignKey>, 
		Commentable<ForeignKey> {
	
	private String name;
	private Table targetTable;
	private Column targetColumn;
	private Optional<String> comment;
	
	public ForeignKey(String name) {
		this.name = name;
	}
	
	private ForeignKey(ForeignKey prototype) {
		name			= prototype.name;
		targetColumn	= prototype.targetColumn.copy();
		comment			= Copier.copy(prototype.comment, s -> s);
	}

	@Override
	public ForeignKey setName(String name) {
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
	public ForeignKey setComment(String comment) {
		this.comment = Optional.of(comment);
		return this;
	}

	public Column getTargetColumn() {
		return targetColumn;
	}

	public ForeignKey setTargetColumn(Column targetColumn) {
		this.targetColumn = targetColumn;
		return this;
	}
	
	@Override
	public ForeignKey copy() {
		return new ForeignKey(this);
	}
}

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
	private Type type;
	private Column column;
	private Optional<String> comment;
	
	public static enum Type {
		INDEX, UNIQUE, PRIMARY
	}
	
	public Index(String name, Type type) {
		this.name	= name;
		this.type	= type;
	}
	
	private Index(Index prototype) {
		this.name		= prototype.name;
		this.type		= prototype.type;
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

	public Type getType() {
		return type;
	}

	public Index setType(Type type) {
		this.type = type;
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

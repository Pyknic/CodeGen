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
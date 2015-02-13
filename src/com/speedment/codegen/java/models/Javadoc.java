/*
 * Copyright 2015 Emil Forslund.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.speedment.codegen.java.models;

import com.speedment.codegen.java.interfaces.Copyable;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Emil Forslund
 */
public class Javadoc implements Copyable<Javadoc> {
	private final List<String> rows;
	private final List<JavadocTag> tags;

	public Javadoc() {
		rows = new ArrayList<>();
		tags = new ArrayList<>();
	}
	
	public Javadoc(final String text) {
		rows = Arrays.asList(text.split("\n"));
		tags = new ArrayList<>();
	}
	
	private Javadoc(final Javadoc prototype) {
		rows = Copier.copy(prototype.rows, c -> c);
		tags = Copier.copy(prototype.tags);
	}
	
	public Javadoc add(String row) {
		rows.add(row);
		return this;
	}
	
	public Javadoc add(String first, String... rows) {
		this.rows.add(first);
		Collections.addAll(this.rows, rows);
		return this;
	}
	
	public List<String> getRows() {
		return rows;
	}

	@Override
	public Javadoc copy() {
		return new Javadoc(this);
	}

	public Javadoc add(JavadocTag tag) {
		tags.add(tag);
		return this;
	}

	public List<JavadocTag> getTags() {
		return tags;
	}
}

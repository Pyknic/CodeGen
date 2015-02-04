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

import com.speedment.codegen.base.CodeModel;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Emil Forslund
 */
public class Javadoc_ implements CodeModel<Javadoc_> {
	private final List<CharSequence> rows;

	public Javadoc_() {
		rows = new ArrayList<>();
	}
	
	public Javadoc_(final CharSequence text) {
		rows = Arrays.asList(text.toString().split("\n"));
	}
	
	private Javadoc_(final Javadoc_ prototype) {
		rows = Copier.copy(prototype.rows, c -> c.toString());
	}
	
	public Javadoc_ add(CharSequence row) {
		rows.add(row);
		return this;
	}
	
	public Javadoc_ add(CharSequence first, CharSequence... rows) {
		this.rows.add(first);
		Collections.addAll(this.rows, rows);
		return this;
	}
	
	public List<CharSequence> getRows() {
		return rows;
	}

	@Override
	public Javadoc_ copy() {
		return new Javadoc_(this);
	}
}

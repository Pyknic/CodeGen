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
package com.speedment.codegen.java.models.modifiers;

import com.speedment.codegen.base.CodeController;
import com.speedment.codegen.base.CodeModel;

/**
 *
 * @author Emil Forslund
 */
public enum Modifier_ implements CodeModel<Modifier_> {
	PUBLIC ("public"),
	PROTECTED ("protected"),
	PRIVATE ("private"),
	ABSTRACT ("abstract"),
	FINAL ("final"),
    STATIC ("static"),
    STRICTFP ("strictfp"),
	TRANSIENT ("transient"),
	VOLATILE ("volatile"),
	SYNCHRONIZED ("synchronized"),
	NATIVE ("native"),
	DEFAULT ("default");
	
	private final CharSequence name;
	
	Modifier_(CharSequence name) {
		this.name = name;
	}
	
	public CharSequence getName() {
		return name;
	}

	@Override
	public Modifier_ copy() {
		return this;
	}
}

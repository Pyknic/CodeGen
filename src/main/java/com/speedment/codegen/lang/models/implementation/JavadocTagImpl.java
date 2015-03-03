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
package com.speedment.codegen.lang.models.implementation;

import com.speedment.codegen.lang.models.JavadocTag;
import com.speedment.util.Copier;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class JavadocTagImpl implements JavadocTag {
	private String name;
	private Optional<String> value;
	private Optional<String> text;
	
	public JavadocTagImpl(String name) {
		this.name  = name;
		this.value = Optional.empty();
		this.text  = Optional.empty();
	}
	
	public JavadocTagImpl(String name, String text) {
		this.name  = name;
		this.value = Optional.empty();
		this.text  = Optional.of(text);
	}
	
	public JavadocTagImpl(String name, String value, String text) {
		this.name  = name;
		this.value = Optional.of(value);
		this.text  = Optional.of(text);
	}
	
	protected JavadocTagImpl(JavadocTagImpl prototype) {
		this.name  = prototype.name;
		this.value = Copier.copy(prototype.value, s -> s);
		this.text  = Copier.copy(prototype.text, s -> s);
	}

    @Override
	public Optional<String> getValue() {
		return value;
	}

    @Override
	public JavadocTag setValue(String value) {
		this.value = Optional.of(value);
		return this;
	}

    @Override
	public Optional<String> getText() {
		return text;
	}

    @Override
	public JavadocTag setText(String text) {
		this.text = Optional.of(text);
		return this;
	}

	@Override
	public JavadocTag setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public JavadocTagImpl copy() {
		return new JavadocTagImpl(this);
	}
	
	public final static class Const extends JavadocTagImpl {
		public Const(String name) {
			super(name);
		}

		public Const(String name, String value) {
			super(name, value);
		}

		public Const(String name, String value, String text) {
			super(name, value, text);
		}
		
		@Override
		public JavadocTag setValue(String value) {
			return copy().setValue(value);
		}
		
		@Override
		public JavadocTag setText(String text) {
			return copy().setText(text);
		}

		@Override
		public JavadocTag setName(String name) {
			return copy().setName(name);
		}
		
		@Override
		public JavadocTagImpl copy() {
			return new JavadocTagImpl(this);
		}
	}
}

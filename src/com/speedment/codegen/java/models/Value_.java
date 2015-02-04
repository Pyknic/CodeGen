/*
 * Copyright 2015 Duncan.
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
import com.speedment.codegen.java.interfaces.Copyable;

/**
 *
 * @author Duncan
 * @param <T>
 */
public abstract class Value_<T> implements CodeModel<Value_<T>> {
	private final T value;
	
	public Value_(final T val) {
		value = val;
	}
	
	public Value_(final Value_<T> prototype) {
		value = prototype.value;
	}
	
	public T getValue() {
		return value;
	}
	
	@Override
	public abstract Value_<T> copy();
	
	public static class Numeric extends Value_<Number> {
		public Numeric(final Number val) {super(val);}
		@Override
		public Numeric copy() {return new Numeric(getValue());}
	}
	
	public static class Text extends Value_<String> {
		public Text(final String val) {super(val);}
		@Override
		public Text copy() {return new Text(getValue());}
	}
	
	public static class Reference extends Value_<CharSequence> {
		public Reference(final CharSequence val) {super(val);}
		@Override
		public Reference copy() {return new Reference(getValue());}
	}
}
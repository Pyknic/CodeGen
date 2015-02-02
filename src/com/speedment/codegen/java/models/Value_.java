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

/**
 *
 * @author Duncan
 * @param <T>
 */
public abstract class Value_<T> implements CodeModel<Value_<T>> {
	public abstract T getValue();
	
	public static class Numeric extends Value_<Number> {
		private final Number value;
		
		public Numeric(Number value) {
			this.value = value;
		}
		
		@Override
		public Number getValue() {
			return value;
		}
	}
	
	public static class Text extends Value_<String> {
		private final String value;

		public Text(String value) {
			this.value = value;
		}

		@Override
		public String getValue() {
			return value;
		}
	}
	
	public static class Reference extends Value_<CharSequence> {
		private final CharSequence name;
		
		public Reference(CharSequence name) {
			this.name = name;
		}

		@Override
		public CharSequence getValue() {
			return name;
		}
	}
}

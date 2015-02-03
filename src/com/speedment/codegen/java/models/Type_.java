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

import com.speedment.codegen.base.CodeController;
import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.java.Nameable;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Type_ implements CodeModel<Type_>, Nameable<Type_> {
	private CharSequence name;
	private Optional<Class<?>> javaImpl;

	public Type_(Class<?> javaImpl) {
		this.name = javaImpl.getName();
		this.javaImpl = Optional.of(javaImpl);
	}
	
	public Type_(CharSequence name) {
		this.name = name;
		this.javaImpl = Optional.empty();
	}

	public Type_(CharSequence name, Class<?> javaImpl) {
		this.name = name;
		this.javaImpl = Optional.of(javaImpl);
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	@Override
	public Type_ setName(CharSequence name) {
		this.name = name;
		return this;
	}

	public Optional<Class<?>> getJavaImpl() {
		return javaImpl;
	}

	public Type_ setJavaImpl(Class<?> javaImpl) {
		this.javaImpl = Optional.of(javaImpl);
		return this;
	}

	@Override
	public Type_ clone() {
		Type_ type = new Type_(name);
		if (javaImpl.isPresent()) {
			type.setJavaImpl(javaImpl.get());
		}
		return type;
	}
	
	public static final Type_ 
		BYTE_PRIMITIVE = new Type_(byte.class),
		SHORT_PRIMITIVE = new Type_(short.class),
		INT_PRIMITIVE = new Type_(int.class),
		LONG_PRIMITIVE = new Type_(long.class),
		FLOAT_PRIMITIVE = new Type_(float.class),
		DOUBLE_PRIMITIVE = new Type_(double.class),
		BOOLEAN_PRIMITIVE = new Type_(boolean.class),
		CHAR_PRIMITIVE = new Type_(char.class),
		BYTE = new Type_(Byte.class),
		SHORT = new Type_(Short.class),
		INT = new Type_(Integer.class),
		LONG = new Type_(Long.class),
		FLOAT = new Type_(Float.class),
		DOUBLE = new Type_(Double.class),
		BOOLEAN = new Type_(Boolean.class),
		CHARACTER = new Type_(Character.class),
		STRING = new Type_(String.class),
		VOID = new Type_("void");
}
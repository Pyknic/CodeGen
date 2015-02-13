/*
 * Copyright 2015 Emil Forslund.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance copy the License.
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

import com.speedment.codegen.java.interfaces.Annotable;
import com.speedment.codegen.java.interfaces.Copyable;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.interfaces.Generable;
import com.speedment.codegen.java.interfaces.Nameable;
import com.speedment.codegen.java.interfaces.Typeable;
import com.speedment.codegen.java.models.modifiers.MethodModifier;
import com.speedment.codegen.java.models.modifiers.Modifier;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class Method implements 
		Copyable<Method>, 
		Nameable<Method>,
		Typeable<Method>,
		Generable<Method>,
		Documentable<Method>,
		Annotable<Method>,
		MethodModifier<Method> {
	
	private String name;
	private Type type;
	private Optional<Javadoc> javadoc;
	private final List<AnnotationUsage> annotations;
	private final List<Generic> generics;
	private final List<Field> params;
	private final List<String> code;
	private final Set<Modifier> modifiers;
	
	public Method(String name, Type type) {
		this.name			= name;
		this.type			= type;
		this.javadoc		= Optional.empty();
		this.annotations	= new ArrayList<>();
		this.generics		= new ArrayList<>();
		this.params			= new ArrayList<>();
		this.code			= new ArrayList<>();
		this.modifiers		= EnumSet.noneOf(Modifier.class);
	}
	
	private Method(final Method prototype) {
		name		= prototype.name;
		type		= prototype.type.copy();
		javadoc		= Copier.copy(prototype.javadoc);
		annotations	= Copier.copy(prototype.annotations);
		generics	= Copier.copy(prototype.generics);
		params		= Copier.copy(prototype.params);
		code		= Copier.copy(prototype.code, c -> c);
		modifiers	= Copier.copy(prototype.modifiers, c -> c.copy(), EnumSet.noneOf(Modifier.class));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Method setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Method setType(Type type) {
		this.type = type;
		return this;
	}

	public List<Field> getParams() {
		return params;
	}

	public Method add(Field param) {
		params.add(param);
		return this;
	}

	public List<String> getCode() {
		return code;
	}

	public Method add(String codeLine) {
		code.add(codeLine);
		return this;
	}

	@Override
	public Set<Modifier> getModifiers() {
		return modifiers;
	}

	@Override
	public Method setJavadoc(Javadoc doc) {
		javadoc = Optional.of(doc);
		return this;
	}

	@Override
	public Optional<Javadoc> getJavadoc() {
		return javadoc;
	}

	@Override
	public Method copy() {
		return new Method(this);
	}

	@Override
	public Method add(AnnotationUsage annotation) {
		annotations.add(annotation);
		return this;
	}

	@Override
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}

	@Override
	public Method add(Generic generic) {
		generics.add(generic);
		return this;
	}

	@Override
	public List<Generic> getGenerics() {
		return generics;
	}
}
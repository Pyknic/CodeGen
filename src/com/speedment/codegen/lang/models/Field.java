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
package com.speedment.codegen.lang.models;

import com.speedment.codegen.lang.interfaces.Annotable;
import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.codegen.lang.interfaces.Documentable;
import com.speedment.codegen.lang.interfaces.Nameable;
import com.speedment.codegen.lang.interfaces.Typeable;
import com.speedment.codegen.lang.interfaces.Valuable;
import com.speedment.codegen.lang.models.modifiers.FieldModifier;
import com.speedment.codegen.lang.models.modifiers.Modifier;
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
public class Field implements 
		Copyable<Field>,
		Nameable<Field>,
		Typeable<Field>,
		Documentable<Field>,
		Valuable<Field>,
		Annotable<Field>,
		FieldModifier<Field> {
	
	private String name;
	private Type type;
	private Optional<Value> value;
	private Optional<Javadoc> javadoc;
	private final List<AnnotationUsage> annotations;
	private final Set<Modifier> modifiers;
	
	public Field(String name, Type type) {
		this.name			= name;
		this.type			= type;
		this.value			= Optional.empty();
		this.javadoc		= Optional.empty();
		this.annotations	= new ArrayList<>();
		this.modifiers		= EnumSet.noneOf(Modifier.class);
	}
	
	private Field(Field prototype) {
		name		= prototype.name;
		type		= prototype.type;
		value		= Copier.copy(prototype.value);
		javadoc		= Copier.copy(prototype.javadoc);
		annotations	= Copier.copy(prototype.annotations);
		modifiers	= Copier.copy(prototype.modifiers, c -> c.copy(), EnumSet.noneOf(Modifier.class));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Field setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Field setType(Type type) {
		this.type = type;
		return this;
	}
	
	@Override
	public Set<Modifier> getModifiers() {
		return modifiers;
	}

	@Override
	public Field setJavadoc(Javadoc doc) {
		javadoc = Optional.of(doc);
		return this;
	}

	@Override
	public Optional<Javadoc> getJavadoc() {
		return javadoc;
	}

	@Override
	public Field setValue(Value val) {
		this.value = Optional.of(val);
		return this;
	}

	@Override
	public Optional<Value> getValue() {
		return value;
	}

	@Override
	public Field copy() {
		return new Field(this);
	}

	@Override
	public Field add(AnnotationUsage annotation) {
		annotations.add(annotation);
		return this;
	}

	@Override
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}
}
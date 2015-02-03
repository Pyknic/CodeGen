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
import com.speedment.codegen.java.Documentable;
import com.speedment.codegen.java.Nameable;
import com.speedment.codegen.java.models.modifiers.ClassModifier;
import com.speedment.codegen.java.models.modifiers.Modifier_;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class Class_ implements CodeModel<Class_>, 
		Nameable<Class_>, 
		Documentable<Class_>, 
		ClassModifier<Class_> {
	
	private CharSequence name;
	private Optional<Type_> superType;
	private Optional<Javadoc_> javadoc = Optional.empty();
	private final List<Dependency_> dependencies = new ArrayList<>();
	private final List<Field_> fields = new ArrayList<>();
	private final List<Method_> methods = new ArrayList<>();
	private final Set<Modifier_> modifiers = EnumSet.noneOf(Modifier_.class);

	public Class_(CharSequence name) {
		this.name = name;
		superType = Optional.empty();
	}

	public Class_(CharSequence name, Type_ superType) {
		this.name = name;
		this.superType = Optional.of(superType);
	}

	@Override
	public Class_ setName(CharSequence name) {
		this.name = name;
		return this;
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	public Class_ setSuperType(Type_ superType) {
		this.superType = Optional.of(superType);
		return this;
	}

	public Optional<Type_> getSuperType() {
		return superType;
	}
	
	public Class_ add(Dependency_ dependency) {
		dependencies.add(dependency);
		return this;
	}
	
	public Class_ add(Field_ field) {
		fields.add(field);
		return this;
	}
	
	public Class_ add(Method_ method) {
		methods.add(method);
		return this;
	}
	
	public List<Dependency_> getDependencies() {
		return dependencies;
	}

	public List<Field_> getFields() {
		return fields;
	}

	public List<Method_> getMethods() {
		return methods;
	}
	
	public Type_ asType() {
		return new Type_(name);
	}

	@Override
	public Class_ clone() {
		Class_ clone = new Class_(name.toString());
		if (superType.isPresent()) clone.setSuperType(superType.get());
		Collections.copy(clone.fields, fields);
		Collections.copy(clone.methods, methods);
		return clone;
	}

	@Override
	public Set<Modifier_> getModifiers() {
		return modifiers;
	}

	@Override
	public Class_ setJavadoc(Javadoc_ doc) {
		javadoc = Optional.of(doc);
		return this;
	}

	@Override
	public Optional<Javadoc_> getJavadoc() {
		return javadoc;
	}
}
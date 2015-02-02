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
import com.speedment.codegen.java.interfaces.Dependable;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.interfaces.Fieldable;
import com.speedment.codegen.java.interfaces.Generable;
import com.speedment.codegen.java.interfaces.Interfaceable;
import com.speedment.codegen.java.interfaces.Methodable;
import com.speedment.codegen.java.interfaces.Modifiable;
import com.speedment.codegen.java.interfaces.Nameable;
import com.speedment.codegen.java.models.modifiers.Modifier_;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Duncan
 * @param <T>
 */
public class ClassOrInterface_<T extends ClassOrInterface_<T>> implements 
		CodeModel<T>,
		Nameable<T>, 
		Documentable<T>, 
		Dependable<T>,
		Generable<T>,
		Interfaceable<T>,
		Methodable<T>,
		Fieldable<T>,
		Modifiable<T> {
	
	private CharSequence name;
	private Optional<Javadoc_> javadoc = Optional.empty();
	private final List<Dependency_> dependencies = new ArrayList<>();
	private final List<Generic_> generics = new ArrayList<>();
	private final List<Type_> interfaces = new ArrayList<>();
	private final List<Field_> fields = new ArrayList<>();
	private final List<Method_> methods = new ArrayList<>();
	private final Set<Modifier_> modifiers = EnumSet.noneOf(Modifier_.class);

	public ClassOrInterface_(CharSequence name) {
		this.name = name;
	}

	@Override
	public T setName(CharSequence name) {
		this.name = name;
		return (T) this;
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	@Override
	public T setJavadoc(Javadoc_ doc) {
		javadoc = Optional.of(doc);
		return (T) this;
	}

	@Override
	public Optional<Javadoc_> getJavadoc() {
		return javadoc;
	}

	@Override
	public T add(Dependency_ dep) {
		dependencies.add(dep);
		return (T) this;
	}

	@Override
	public List<Dependency_> getDependencies() {
		return dependencies;
	}

	@Override
	public T add(Method_ method) {
		methods.add(method);
		return (T) this;
	}

	@Override
	public List<Method_> getMethods() {
		return methods;
	}

	@Override
	public T add(Field_ field) {
		fields.add(field);
		return (T) this;
	}

	@Override
	public List<Field_> getFields() {
		return fields;
	}

	@Override
	public T add(Type_ interf) {
		interfaces.add(interf);
		return (T) this;
	}

	@Override
	public List<Type_> getInterfaces() {
		return interfaces;
	}
	
	public Type_ asType() {
		return new Type_(name);
	}
	
	@Override
	public Set<Modifier_> getModifiers() {
		return modifiers;
	}

	@Override
	public T add(Generic_ generic) {
		generics.add(generic);
		return (T) this;
	}

	@Override
	public List<Generic_> getGenerics() {
		return generics;
	}
}

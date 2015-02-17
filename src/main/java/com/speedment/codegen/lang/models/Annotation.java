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
package com.speedment.codegen.lang.models;

import com.speedment.codegen.lang.interfaces.Annotable;
import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.codegen.lang.interfaces.Dependable;
import com.speedment.codegen.lang.interfaces.Documentable;
import com.speedment.codegen.lang.interfaces.Fieldable;
import com.speedment.codegen.lang.interfaces.Modifiable;
import com.speedment.codegen.lang.interfaces.Nameable;
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
public class Annotation implements
		Copyable<Annotation>,
		Nameable<Annotation>,
		Documentable<Annotation>,
		Fieldable<Annotation>,
		Dependable<Annotation>,
		Modifiable<Annotation>,
		Annotable<Annotation> {
	
	private String name;
	private Optional<Javadoc> javadoc;
	private final List<AnnotationUsage> annotations;
	private final List<Field> fields;
	private final List<Import> dependencies;
	private final Set<Modifier> modifiers;

	public Annotation(String name) {
		this.name			= name;
		this.javadoc		= Optional.empty();
		this.annotations	= new ArrayList<>();
		this.fields			= new ArrayList<>();
		this.dependencies	= new ArrayList<>();
		this.modifiers		= EnumSet.noneOf(Modifier.class);
	}
	
	private Annotation(Annotation prototype) {
		name			= prototype.name;
		javadoc			= Copier.copy(prototype.javadoc);
		annotations		= Copier.copy(prototype.annotations);
		fields			= Copier.copy(prototype.fields);
		dependencies	= Copier.copy(prototype.dependencies);
		modifiers		= Copier.copy(prototype.modifiers, c -> c.copy(), EnumSet.noneOf(Modifier.class));
	}
	
	public Type toType() {
		return new Type(name);
	}

	@Override
	public Annotation setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Annotation add(Field field) {
		fields.add(field);
		return this;
	}

	@Override
	public List<Field> getFields() {
		return fields;
	}

	@Override
	public Annotation setJavadoc(Javadoc doc) {
		this.javadoc = Optional.of(doc);
		return this;
	}

	@Override
	public Optional<Javadoc> getJavadoc() {
		return javadoc;
	}

	@Override
	public Annotation add(Import dep) {
		dependencies.add(dep);
		return this;
	}

	@Override
	public List<Import> getDependencies() {
		return dependencies;
	}

	@Override
	public Set<Modifier> getModifiers() {
		return modifiers;
	}
	
	@Override
	public Annotation copy() {
		return new Annotation(this);
	}

	@Override
	public Annotation add(AnnotationUsage annotation) {
		annotations.add(annotation);
		return this;
	}

	@Override
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}
}

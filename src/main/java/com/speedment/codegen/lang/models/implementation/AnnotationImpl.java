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

import com.speedment.codegen.lang.models.Annotation;
import com.speedment.codegen.lang.models.AnnotationUsage;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Import;
import com.speedment.codegen.lang.models.Javadoc;
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
public class AnnotationImpl implements Annotation {
	
	private String name;
	private Javadoc javadoc;
	private final List<AnnotationUsage> annotations;
	private final List<Field> fields;
	private final List<Import> imports;
	private final Set<Modifier> modifiers;

	public AnnotationImpl(String name) {
		this.name        = name;
		this.javadoc     = null;
		this.annotations = new ArrayList<>();
		this.fields      = new ArrayList<>();
		this.imports	 = new ArrayList<>();
		this.modifiers   = EnumSet.noneOf(Modifier.class);
	}
	
	protected AnnotationImpl(Annotation prototype) {
		name        = prototype.getName();
		javadoc     = prototype.getJavadoc().map(Copier::copy).orElse(null);
		annotations = Copier.copy(prototype.getAnnotations());
		fields      = Copier.copy(prototype.getFields());
		imports     = Copier.copy(prototype.getImports());
		modifiers   = Copier.copy(prototype.getModifiers(), c -> c.copy(), EnumSet.noneOf(Modifier.class));
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
	public List<Field> getFields() {
		return fields;
	}

	@Override
	public Annotation set(Javadoc doc) {
		this.javadoc = doc;
		return this;
	}

	@Override
	public Optional<Javadoc> getJavadoc() {
		return Optional.ofNullable(javadoc);
	}

	@Override
	public List<Import> getImports() {
		return imports;
	}

	@Override
	public Set<Modifier> getModifiers() {
		return modifiers;
	}

	@Override
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}
    
    @Override
	public AnnotationImpl copy() {
		return new AnnotationImpl(this);
	}
}
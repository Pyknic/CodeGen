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

import com.speedment.codegen.lang.models.AnnotationUsage;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Generic;
import com.speedment.codegen.lang.models.Javadoc;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.Type;
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
public class MethodImpl implements Method {
	
	private String name;
	private Type type;
	private Optional<Javadoc> javadoc;
	private final List<AnnotationUsage> annotations;
	private final List<Generic> generics;
	private final List<Field> params;
	private final List<String> code;
	private final Set<Modifier> modifiers;
	
	public MethodImpl(String name, Type type) {
		this.name			= name;
		this.type			= type;
		this.javadoc		= Optional.empty();
		this.annotations	= new ArrayList<>();
		this.generics		= new ArrayList<>();
		this.params			= new ArrayList<>();
		this.code			= new ArrayList<>();
		this.modifiers		= EnumSet.noneOf(Modifier.class);
	}
	
	protected MethodImpl(final MethodImpl prototype) {
		name		= prototype.name;
		type		= prototype.type;
		javadoc		= Copier.copy(prototype.javadoc);
		annotations	= Copier.copy(prototype.annotations);
		generics	= Copier.copy(prototype.generics);
		params		= Copier.copy(prototype.params);
		code		= Copier.copy(prototype.code, s -> s);
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

	@Override
	public List<Field> getFields() {
		return params;
	}

    @Override
	public List<String> getCode() {
		return code;
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
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}

	@Override
	public List<Generic> getGenerics() {
		return generics;
	}
    
    @Override
	public MethodImpl copy() {
		return new MethodImpl(this);
	}
}
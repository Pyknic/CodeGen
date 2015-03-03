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
import com.speedment.codegen.lang.models.Constructor;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Javadoc;
import com.speedment.codegen.lang.models.modifiers.Modifier;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class ConstructorImpl implements Constructor {
	
	private Javadoc javadoc;
	private final List<AnnotationUsage> annotations;
	private final List<Field> params;
	private final List<String> code;
	private final Set<Modifier> modifiers;
	
	public ConstructorImpl() {
		javadoc		= null;
		annotations = new ArrayList<>();
		params		= new ArrayList<>();
		code		= new ArrayList<>();
		modifiers	= new HashSet<>();
	}
	
	protected ConstructorImpl(final ConstructorImpl prototype) {
		javadoc		= Copier.copy(prototype.javadoc);
		annotations	= Copier.copy(prototype.annotations);
		params		= Copier.copy(prototype.params);
		code		= Copier.copy(prototype.code, c -> c);
		modifiers	= Copier.copy(prototype.modifiers, c -> c.copy(), EnumSet.noneOf(Modifier.class));
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
	public Constructor set(Javadoc doc) {
		javadoc = doc;
		return this;
	}

	@Override
	public Optional<Javadoc> getJavadoc() {
		return Optional.ofNullable(javadoc);
	}

	@Override
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}
    
    @Override
	public ConstructorImpl copy() {
		return new ConstructorImpl(this);
	}
}
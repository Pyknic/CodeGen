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
import com.speedment.codegen.java.interfaces.Annotable;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.interfaces.Nameable;
import com.speedment.codegen.java.interfaces.Typeable;
import com.speedment.codegen.java.interfaces.Valuable;
import com.speedment.codegen.java.models.modifiers.FieldModifier;
import com.speedment.codegen.java.models.modifiers.Modifier_;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Field_ implements CodeModel<Field_>, 
		Nameable<Field_>, 
		Typeable<Field_>, 
		Documentable<Field_>,
		Valuable<Field_>,
		Annotable<Field_>,
		FieldModifier<Field_> {
	
	private CharSequence name;
	private Type_ type;
	private Optional<Value_> value;
	private Optional<Javadoc_> javadoc;
	private final List<AnnotationUsage_> annotations;
	private final EnumSet<Modifier_> modifiers;
	
	public Field_(CharSequence name, Type_ type) {
		this.name			= name;
		this.type			= type;
		this.value			= Optional.empty();
		this.javadoc		= Optional.empty();
		this.annotations	= new ArrayList<>();
		this.modifiers		= EnumSet.noneOf(Modifier_.class);
	}
	
	private Field_(Field_ prototype) {
		name		= prototype.name;
		type		= prototype.type;
		value		= Copier.copy(prototype.value);
		javadoc		= Copier.copy(prototype.javadoc);
		annotations	= Copier.copy(prototype.annotations);
		modifiers	= Copier.copy(prototype.modifiers);
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	@Override
	public Field_ setName(CharSequence name) {
		this.name = name;
		return this;
	}

	@Override
	public Type_ getType() {
		return type;
	}

	@Override
	public Field_ setType(Type_ type) {
		this.type = type;
		return this;
	}
	
	@Override
	public EnumSet<Modifier_> getModifiers() {
		return modifiers;
	}

	@Override
	public Field_ setJavadoc(Javadoc_ doc) {
		javadoc = Optional.of(doc);
		return this;
	}

	@Override
	public Optional<Javadoc_> getJavadoc() {
		return javadoc;
	}

	@Override
	public Field_ setValue(Value_ val) {
		this.value = Optional.of(val);
		return this;
	}

	@Override
	public Optional<Value_> getValue() {
		return value;
	}

	@Override
	public Field_ copy() {
		return new Field_(this);
	}

	@Override
	public Field_ add(AnnotationUsage_ annotation) {
		annotations.add(annotation);
		return this;
	}

	@Override
	public List<AnnotationUsage_> getAnnotations() {
		return annotations;
	}
}
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

import com.speedment.codegen.java.models.modifiers.ClassModifier;
import com.speedment.util.Copier;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Class_ extends ClassOrInterface_<Class_> implements 
		ClassModifier<Class_> {

	private Optional<Type_> superType;

	public Class_(CharSequence name) {
		super(name);
		superType = Optional.empty();
	}

	public Class_(CharSequence name, Type_ superType) {
		super(name);
		this.superType = Optional.of(superType);
	}
	
	private Class_(Class_ prototype) {
		super (prototype);
		this.superType = Copier.copy(prototype.superType);
	}

	public Class_ setSuperType(Type_ superType) {
		this.superType = Optional.of(superType);
		return this;
	}

	public Optional<Type_> getSuperType() {
		return superType;
	}

	@Override
	public Class_ copy() {
		return new Class_(this);
	}
}
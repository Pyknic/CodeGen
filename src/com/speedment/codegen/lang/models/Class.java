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

import com.speedment.codegen.lang.models.modifiers.ClassModifier;
import com.speedment.util.Copier;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Class extends ClassOrInterface<Class> implements 
		ClassModifier<Class> {

	private Optional<Type> superType;

	public Class(String name) {
		super(name);
		superType = Optional.empty();
	}

	public Class(String name, Type superType) {
		super(name);
		this.superType = Optional.of(superType);
	}
	
	private Class(Class prototype) {
		super (prototype);
		this.superType = Copier.copy(prototype.superType);
	}

	public Class setSuperType(Type superType) {
		this.superType = Optional.of(superType);
		return this;
	}

	public Optional<Type> getSuperType() {
		return superType;
	}

	@Override
	public Class copy() {
		return new Class(this);
	}
}
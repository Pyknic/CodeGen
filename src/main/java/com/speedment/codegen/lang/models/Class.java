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

import com.speedment.codegen.lang.interfaces.Constructable;
import com.speedment.codegen.lang.interfaces.Supertypeable;
import com.speedment.codegen.lang.models.modifiers.ClassModifier;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Class extends ClassOrInterface<Class> implements 
		Constructable<Class>,
		Supertypeable<Class>,
		ClassModifier<Class> {

	private Optional<Type> superType;
	private final List<Constructor> constructors;

	public Class(String name) {
		super(name);
		superType = Optional.empty();
		constructors = new ArrayList<>();
	}

	public Class(String name, Type superType) {
		super(name);
		this.superType = Optional.of(superType);
		this.constructors = new ArrayList<>();
	}
	
	private Class(Class prototype) {
		super (prototype);
		this.superType = Copier.copy(prototype.superType);
		this.constructors = Copier.copy(prototype.constructors);
	}

	@Override
	public Class setSupertype(Type superType) {
		this.superType = Optional.of(superType);
		return this;
	}

	@Override
	public Optional<Type> getSupertype() {
		return superType;
	}

	@Override
	public Class copy() {
		return new Class(this);
	}

	@Override
	public Class add(Constructor constr) {
		constructors.add(constr);
		return this;
	}

	@Override
	public List<Constructor> getConstructors() {
		return constructors;
	}
}
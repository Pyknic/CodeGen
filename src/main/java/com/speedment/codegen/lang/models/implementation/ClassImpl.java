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

import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Constructor;
import com.speedment.codegen.lang.models.Type;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class ClassImpl extends ClassOrInterfaceImpl<Class> implements Class {

	private Optional<Type> superType;
	private final List<Constructor> constructors;

	public ClassImpl(String name) {
		super(name);
		superType = Optional.empty();
		constructors = new ArrayList<>();
	}

	public ClassImpl(String name, Type superType) {
		super(name);
		this.superType = Optional.of(superType);
		this.constructors = new ArrayList<>();
	}
	
	protected ClassImpl(ClassImpl prototype) {
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
	public List<Constructor> getConstructors() {
		return constructors;
	}
    
    @Override
	public ClassImpl copy() {
		return new ClassImpl(this);
	}
}
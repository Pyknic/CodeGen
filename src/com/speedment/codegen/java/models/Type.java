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
import com.speedment.codegen.java.interfaces.Generable;
import com.speedment.codegen.java.interfaces.Nameable;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Type implements CodeModel<Type>, 
		Nameable<Type>,
		Generable<Type>,
		Annotable<Type> {
	
	private String name;
	private int arrayDimension;
	private final List<AnnotationUsage> annotations;
	private final List<Generic> generics;
	private Optional<java.lang.Class<?>> javaImpl;

	public Type(java.lang.Class<?> javaImpl) {
		this (javaImpl.getName(), javaImpl);
	}
	
	public Type(String name) {
		this (name, null);
	}

	public Type(String name, java.lang.Class<?> javaImpl) {
		this.name			= name;
		this.arrayDimension = 0;
		this.annotations	= new ArrayList<>();
		this.generics		= new ArrayList<>();
		this.javaImpl		= Optional.ofNullable(javaImpl);
	}
	
	private Type(Type prototype) {
		name			= prototype.name;
		arrayDimension	= prototype.arrayDimension;
		annotations		= Copier.copy(prototype.annotations);
		generics		= Copier.copy(prototype.generics);
		javaImpl		= prototype.javaImpl;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type setName(String name) {
		this.name = name;
		return this;
	}

	public Optional<java.lang.Class<?>> getJavaImpl() {
		return javaImpl;
	}

	public Type setJavaImpl(java.lang.Class<?> javaImpl) {
		this.javaImpl = Optional.of(javaImpl);
		return this;
	}

	public int getArrayDimension() {
		return arrayDimension;
	}

	public Type setArrayDimension(int arrayDimension) {
		this.arrayDimension = arrayDimension;
		return this;
	}
	
	@Override
	public Type add(Generic generic) {
		generics.add(generic);
		return this;
	}

	@Override
	public List<Generic> getGenerics() {
		return generics;
	}

	@Override
	public Type copy() {
		return new Type(this);
	}

	@Override
	public Type add(AnnotationUsage annotation) {
		annotations.add(annotation);
		return this;
	}

	@Override
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}
	
	public static final class Const extends Type {
		public Const(java.lang.Class<?> javaImpl) {super(javaImpl);}
		public Const(String name) {super(name);}
		public Const(String name, java.lang.Class<?> javaImpl) {
			super(name, javaImpl);
		}
		
		@Override
		public Type setArrayDimension(int arrayDimension) {
			return copy().setArrayDimension(arrayDimension);
		}

		@Override
		public Type setJavaImpl(java.lang.Class<?> javaImpl) {
			return copy().setJavaImpl(javaImpl);
		}

		@Override
		public Type setName(String name) {
			return copy().setName(name);
		}

		@Override
		public Type add(Generic generic) {
			return copy().add(generic);
		}
	}
}
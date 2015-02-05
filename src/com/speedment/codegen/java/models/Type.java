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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Emil Forslund
 */
public class Type implements CodeModel<Type>, 
		Nameable<Type>,
		Generable<Type>,
		Annotable<Type> {
	
	private CharSequence name;
	private int arrayDimension;
	private final List<AnnotationUsage> annotations;
	private final List<Generic> generics;
	private Optional<java.lang.Class<?>> javaImpl;

	public Type(java.lang.Class<?> javaImpl) {
		this (javaImpl.getName(), javaImpl);
	}
	
	public Type(CharSequence name) {
		this (name, null);
	}

	public Type(CharSequence name, java.lang.Class<?> javaImpl) {
		this.name			= name;
		this.arrayDimension = 0;
		this.annotations	= new ArrayList<>();
		this.generics		= new ArrayList<>();
		this.javaImpl		= Optional.ofNullable(javaImpl);
	}
	
	private Type(Type prototype) {
		name			= prototype.name.toString();
		arrayDimension	= prototype.arrayDimension;
		annotations		= Copier.copy(prototype.annotations);
		generics		= Copier.copy(prototype.generics);
		javaImpl		= prototype.javaImpl;
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	@Override
	public Type setName(CharSequence name) {
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
		public Const(CharSequence name) {super(name);}
		public Const(CharSequence name, java.lang.Class<?> javaImpl) {
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
		public Type setName(CharSequence name) {
			return copy().setName(name);
		}

		@Override
		public Type add(Generic generic) {
			return copy().add(generic);
		}
	}

	public static final Type 
		BYTE_PRIMITIVE = new Const(byte.class),
		SHORT_PRIMITIVE = new Const(short.class),
		INT_PRIMITIVE = new Const(int.class),
		LONG_PRIMITIVE = new Const(long.class),
		FLOAT_PRIMITIVE = new Const(float.class),
		DOUBLE_PRIMITIVE = new Const(double.class),
		BOOLEAN_PRIMITIVE = new Const(boolean.class),
		CHAR_PRIMITIVE = new Const(char.class),
		BYTE = new Const(Byte.class),
		SHORT = new Const(Short.class),
		INT = new Const(Integer.class),
		LONG = new Const(Long.class),
		FLOAT = new Const(Float.class),
		DOUBLE = new Const(Double.class),
		BOOLEAN = new Const(Boolean.class),
		CHARACTER = new Const(Character.class),
		STRING = new Const(String.class),
		VOID = new Const("void"),
		WILDCARD = new Const("?"),
		LIST = new Const(List.class),
		SET = new Const(Set.class),
		MAP = new Const(Map.class),
		QUEUE = new Const(Queue.class),
		STACK = new Const(Stack.class),
		OPTIONAL = new Const(Optional.class),
		ENTRY = new Const(HashMap.Entry.class),
		FUNCTION = new Const(Function.class),
		PREDICATE = new Const(Predicate.class),
		CONSUMER = new Const(Consumer.class);
		
	public static final Type list(Type innerType) {
		return LIST.add(new Generic().add(innerType));
	}
	
	public static final Type set(Type innerType) {
		return SET.add(new Generic().add(innerType));
	}
	
	public static final Type map(Type innerTypeA, Type innerTypeB) {
		return MAP.add(new Generic().add(innerTypeA).add(innerTypeB));
	}
	
	public static final Type queue(Type innerType) {
		return QUEUE.add(new Generic().add(innerType));
	}
	
	public static final Type stack(Type innerType) {
		return STACK.add(new Generic().add(innerType));
	}
	
	public static final Type optional(Type innerType) {
		return OPTIONAL.add(new Generic().add(innerType));
	}
	
	public static final Type entry(Type innerTypeA, Type innerTypeB) {
		return ENTRY.add(new Generic().add(innerTypeA).add(innerTypeB));
	}
	
	public static final Type function(Type innerTypeA, Type innerTypeB) {
		return FUNCTION.add(new Generic().add(innerTypeA).add(innerTypeB));
	}
	
	public static final Type predicate(Type innerType) {
		return PREDICATE.add(new Generic().add(innerType));
	}
	
	public static final Type consumer(Type innerType) {
		return CONSUMER.add(new Generic().add(innerType));
	}
}
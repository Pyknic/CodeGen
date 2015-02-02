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
import com.speedment.codegen.java.interfaces.Generable;
import com.speedment.codegen.java.interfaces.Nameable;
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
public class Type_ implements CodeModel<Type_>, 
		Nameable<Type_>,
		Generable<Type_> {
	
	private CharSequence name;
	private int arrayDimension = 0;
	private final List<Generic_> generics = new ArrayList<>();
	private Optional<Class<?>> javaImpl;

	public Type_(Class<?> javaImpl) {
		this.name = javaImpl.getName();
		this.javaImpl = Optional.of(javaImpl);
	}
	
	public Type_(CharSequence name) {
		this.name = name;
		this.javaImpl = Optional.empty();
	}

	public Type_(CharSequence name, Class<?> javaImpl) {
		this.name = name;
		this.javaImpl = Optional.of(javaImpl);
	}
	
	private Type_(Type_ type) {
		name = type.name;
		javaImpl = type.javaImpl;
		arrayDimension = type.arrayDimension;
		generics.addAll(type.generics);
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	@Override
	public Type_ setName(CharSequence name) {
		this.name = name;
		return this;
	}

	public Optional<Class<?>> getJavaImpl() {
		return javaImpl;
	}

	public Type_ setJavaImpl(Class<?> javaImpl) {
		this.javaImpl = Optional.of(javaImpl);
		return this;
	}

	public int getArrayDimension() {
		return arrayDimension;
	}

	public Type_ setArrayDimension(int arrayDimension) {
		this.arrayDimension = arrayDimension;
		return this;
	}
	
	@Override
	public Type_ add(Generic_ generic) {
		generics.add(generic);
		return this;
	}

	@Override
	public List<Generic_> getGenerics() {
		return generics;
	}
	
	public static final class Const extends Type_ {
		public Const(Class<?> javaImpl) {super(javaImpl);}
		public Const(CharSequence name) {super(name);}
		public Const(CharSequence name, Class<?> javaImpl) {super(name, javaImpl);}
		
		@Override
		public Type_ setArrayDimension(int arrayDimension) {
			return new Type_(this).setArrayDimension(arrayDimension);
		}

		@Override
		public Type_ setJavaImpl(Class<?> javaImpl) {
			return new Type_(this).setJavaImpl(javaImpl);
		}

		@Override
		public Type_ setName(CharSequence name) {
			return new Type_(this).setName(name);
		}

		@Override
		public Type_ add(Generic_ generic) {
			return new Type_(this).add(generic);
		}
	}

	public static final Type_ 
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
		
	public static final Type_ list(Type_ innerType) {
		return LIST.add(new Generic_().add(innerType));
	}
	
	public static final Type_ set(Type_ innerType) {
		return SET.add(new Generic_().add(innerType));
	}
	
	public static final Type_ map(Type_ innerTypeA, Type_ innerTypeB) {
		return MAP.add(new Generic_().add(innerTypeA).add(innerTypeB));
	}
	
	public static final Type_ queue(Type_ innerType) {
		return QUEUE.add(new Generic_().add(innerType));
	}
	
	public static final Type_ stack(Type_ innerType) {
		return STACK.add(new Generic_().add(innerType));
	}
	
	public static final Type_ optional(Type_ innerType) {
		return OPTIONAL.add(new Generic_().add(innerType));
	}
	
	public static final Type_ entry(Type_ innerTypeA, Type_ innerTypeB) {
		return ENTRY.add(new Generic_().add(innerTypeA).add(innerTypeB));
	}
	
	public static final Type_ function(Type_ innerTypeA, Type_ innerTypeB) {
		return FUNCTION.add(new Generic_().add(innerTypeA).add(innerTypeB));
	}
	
	public static final Type_ predicate(Type_ innerType) {
		return PREDICATE.add(new Generic_().add(innerType));
	}
	
	public static final Type_ consumer(Type_ innerType) {
		return CONSUMER.add(new Generic_().add(innerType));
	}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.java.models.constants;

import com.speedment.codegen.java.models.AnnotationUsage;
import com.speedment.codegen.java.models.Generic;
import com.speedment.codegen.java.models.Type;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Native;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
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
public abstract class Default {
	private Default() {}
	
	public final static AnnotationUsage
		OVERRIDE	= new AnnotationUsage.Const(new Type(Override.class)),
		DOCUMENTED	= new AnnotationUsage.Const(new Type(Documented.class)),
		INHERITED	= new AnnotationUsage.Const(new Type(Inherited.class)),
		NATIVE		= new AnnotationUsage.Const(new Type(Native.class)),
		REPEATABLE	= new AnnotationUsage.Const(new Type(Repeatable.class)),
		RETENTION	= new AnnotationUsage.Const(new Type(Retention.class)),
		TARGET		= new AnnotationUsage.Const(new Type(Target.class));
	
	public static final Type 
		BYTE_PRIMITIVE = new Type.Const(byte.class),
		SHORT_PRIMITIVE = new Type.Const(short.class),
		INT_PRIMITIVE = new Type.Const(int.class),
		LONG_PRIMITIVE = new Type.Const(long.class),
		FLOAT_PRIMITIVE = new Type.Const(float.class),
		DOUBLE_PRIMITIVE = new Type.Const(double.class),
		BOOLEAN_PRIMITIVE = new Type.Const(boolean.class),
		CHAR_PRIMITIVE = new Type.Const(char.class),
		BYTE = new Type.Const(Byte.class),
		SHORT = new Type.Const(Short.class),
		INT = new Type.Const(Integer.class),
		LONG = new Type.Const(Long.class),
		FLOAT = new Type.Const(Float.class),
		DOUBLE = new Type.Const(Double.class),
		BOOLEAN = new Type.Const(Boolean.class),
		CHARACTER = new Type.Const(Character.class),
		STRING = new Type.Const(String.class),
		VOID = new Type.Const("void"),
		WILDCARD = new Type.Const("?"),
		LIST = new Type.Const(List.class),
		SET = new Type.Const(Set.class),
		MAP = new Type.Const(Map.class),
		QUEUE = new Type.Const(Queue.class),
		STACK = new Type.Const(Stack.class),
		OPTIONAL = new Type.Const(Optional.class),
		ENTRY = new Type.Const(HashMap.Entry.class),
		FUNCTION = new Type.Const(Function.class),
		PREDICATE = new Type.Const(Predicate.class),
		CONSUMER = new Type.Const(Consumer.class);
		
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

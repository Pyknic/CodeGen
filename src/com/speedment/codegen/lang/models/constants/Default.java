package com.speedment.codegen.lang.models.constants;

import com.speedment.codegen.lang.models.AnnotationUsage;
import com.speedment.codegen.lang.models.Generic;
import com.speedment.codegen.lang.models.JavadocTag;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.Value;
import com.speedment.codegen.lang.models.values.NullValue;
import com.speedment.codegen.lang.models.values.TextValue;
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
import javax.annotation.Generated;


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
		TARGET		= new AnnotationUsage.Const(new Type(Target.class)),
		GENERATED	= new AnnotationUsage.Const(new Type(Generated.class));
	
	public final static JavadocTag
		PARAM		= new JavadocTag.Const("param"),
		AUTHOR		= new JavadocTag.Const("author"),
		DEPRICATED	= new JavadocTag.Const("depricated"),
		RETURN		= new JavadocTag.Const("return"),
		SEE			= new JavadocTag.Const("see"),
		THROWS		= new JavadocTag.Const("throws"),
		SINCE		= new JavadocTag.Const("since"),
		VERSION		= new JavadocTag.Const("version");
	
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
	
	public final static Value
		NULL			= new NullValue(),
		EMPTY_STRING	= new TextValue("");
		
	public static final Type list(Type innerType) {
		return LIST.add(new Generic().add(innerType));
	}
	
	public static final Type set(Type innerType) {
		return SET.add(new Generic().add(innerType));
	}
	
	public static final Type map(Type innerTypeA, Type innerTypeB) {
		return MAP.add(new Generic().add(innerTypeA)).add(new Generic(innerTypeB));
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
	
	public static boolean isVoid(Type type) {
		return type == null || VOID.equals(type) || "void".equals(type.getName());
	}
}

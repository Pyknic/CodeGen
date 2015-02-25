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
package com.speedment.codegen.lang.models.constants;

import com.speedment.codegen.lang.models.AnnotationUsage;
import com.speedment.codegen.lang.models.JavadocTag;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.Value;
import com.speedment.codegen.lang.models.implementation.AnnotationUsageImpl;
import com.speedment.codegen.lang.models.implementation.GenericImpl;
import com.speedment.codegen.lang.models.implementation.JavadocTagImpl;
import com.speedment.codegen.lang.models.implementation.TypeImpl;
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
		OVERRIDE	= new AnnotationUsageImpl.Const(new TypeImpl(Override.class)),
		DOCUMENTED	= new AnnotationUsageImpl.Const(new TypeImpl(Documented.class)),
		INHERITED	= new AnnotationUsageImpl.Const(new TypeImpl(Inherited.class)),
		NATIVE		= new AnnotationUsageImpl.Const(new TypeImpl(Native.class)),
		REPEATABLE	= new AnnotationUsageImpl.Const(new TypeImpl(Repeatable.class)),
		RETENTION	= new AnnotationUsageImpl.Const(new TypeImpl(Retention.class)),
		TARGET		= new AnnotationUsageImpl.Const(new TypeImpl(Target.class)),
		GENERATED	= new AnnotationUsageImpl.Const(new TypeImpl(Generated.class));
	
	public final static JavadocTag
		PARAM		= new JavadocTagImpl.Const("param"),
		AUTHOR		= new JavadocTagImpl.Const("author"),
		DEPRICATED	= new JavadocTagImpl.Const("depricated"),
		RETURN		= new JavadocTagImpl.Const("return"),
		SEE			= new JavadocTagImpl.Const("see"),
		THROWS		= new JavadocTagImpl.Const("throws"),
		SINCE		= new JavadocTagImpl.Const("since"),
		VERSION		= new JavadocTagImpl.Const("version");
	
	public static final Type
		BYTE_PRIMITIVE = new TypeImpl.Const(byte.class),
		SHORT_PRIMITIVE = new TypeImpl.Const(short.class),
		INT_PRIMITIVE = new TypeImpl.Const(int.class),
		LONG_PRIMITIVE = new TypeImpl.Const(long.class),
		FLOAT_PRIMITIVE = new TypeImpl.Const(float.class),
		DOUBLE_PRIMITIVE = new TypeImpl.Const(double.class),
		BOOLEAN_PRIMITIVE = new TypeImpl.Const(boolean.class),
		CHAR_PRIMITIVE = new TypeImpl.Const(char.class),
		BYTE = new TypeImpl.Const(Byte.class),
		SHORT = new TypeImpl.Const(Short.class),
		INT = new TypeImpl.Const(Integer.class),
		LONG = new TypeImpl.Const(Long.class),
		FLOAT = new TypeImpl.Const(Float.class),
		DOUBLE = new TypeImpl.Const(Double.class),
		BOOLEAN = new TypeImpl.Const(Boolean.class),
		CHARACTER = new TypeImpl.Const(Character.class),
		STRING = new TypeImpl.Const(String.class),
		OBJECT = new TypeImpl.Const(Object.class),
		VOID = new TypeImpl.Const("void"),
		WILDCARD = new TypeImpl.Const("?"),
		LIST = new TypeImpl.Const(List.class),
		SET = new TypeImpl.Const(Set.class),
		MAP = new TypeImpl.Const(Map.class),
		QUEUE = new TypeImpl.Const(Queue.class),
		STACK = new TypeImpl.Const(Stack.class),
		OPTIONAL = new TypeImpl.Const(Optional.class),
		ENTRY = new TypeImpl.Const(HashMap.Entry.class),
		FUNCTION = new TypeImpl.Const(Function.class),
		PREDICATE = new TypeImpl.Const(Predicate.class),
		CONSUMER = new TypeImpl.Const(Consumer.class);
	
	public final static Value<?>
		NULL			= new NullValue(),
		EMPTY_STRING	= new TextValue("");
		
	public static final Type list(Type innerType) {
		return LIST.add(new GenericImpl().add(innerType));
	}
	
	public static final Type set(Type innerType) {
		return SET.add(new GenericImpl().add(innerType));
	}
	
	public static final Type map(Type innerTypeA, Type innerTypeB) {
		return MAP.add(new GenericImpl().add(innerTypeA)).add(new GenericImpl(innerTypeB));
	}
	
	public static final Type queue(Type innerType) {
		return QUEUE.add(new GenericImpl().add(innerType));
	}
	
	public static final Type stack(Type innerType) {
		return STACK.add(new GenericImpl().add(innerType));
	}
	
	public static final Type optional(Type innerType) {
		return OPTIONAL.add(new GenericImpl().add(innerType));
	}
	
	public static final Type entry(Type innerTypeA, Type innerTypeB) {
		return ENTRY.add(new GenericImpl().add(innerTypeA).add(innerTypeB));
	}
	
	public static final Type function(Type innerTypeA, Type innerTypeB) {
		return FUNCTION.add(new GenericImpl().add(innerTypeA).add(innerTypeB));
	}
	
	public static final Type predicate(Type innerType) {
		return PREDICATE.add(new GenericImpl().add(innerType));
	}
	
	public static final Type consumer(Type innerType) {
		return CONSUMER.add(new GenericImpl().add(innerType));
	}
	
	public static boolean isVoid(Type type) {
		return type == null || VOID.equals(type) || "void".equals(type.getName());
	}
}

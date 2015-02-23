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
package com.speedment.codegen.lang.controller;

import com.speedment.codegen.lang.interfaces.Fieldable;
import com.speedment.codegen.lang.interfaces.Methodable;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.constants.Default;
import java.util.function.Consumer;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.lang.interfaces.Dependable;
import com.speedment.codegen.lang.interfaces.Nameable;
import com.speedment.codegen.lang.models.Import;
import com.speedment.codegen.lang.models.Type;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public class AutoEquals<T extends Fieldable<T>&Methodable<T>&Nameable<T>&Dependable<T>> implements Consumer<T> {
	private final static String 
		M_EQUALS = "equals",
//		M_COMPARE_TO = "compareTo",
		F_OTHER = "other",
		F_SUCCESS = "success",
		RETURN_FALSE = "return false;",
		IF = "if (",
		IF_NOT = "if (!",
		RETURN = "return ",
//		IF_OTHER_IS_NULL = "if (other == null) ",
		IF_CLASS_DIFFERS = "if (other.class != this.class) {",
		CLASS = ".getClass()",
		THIS = "this",
		ELSE = " else ",
		FINAL = "final ",
		O = "o",
		O_EQUALS = " o = (",
		REF_OTHER = ") other;",
		NULL = "null",
//		OR = " || ",
//		AND = " && ",
		ASSIGN = " = ",
		EQUALS = " == ",
		NOT_EQUALS = " != ",
		TRUE = "true",
		FALSE = "false",
		BOOLEAN = "boolean ";
	
	private boolean hasImportedObjects = false;
	
	@Override
	public void accept(T t) {
		t.getFields();
		hasImportedObjects = false;
		
		if (!hasMethod(t, "equals", 1)) {
			final String type = shortName(t.getName());
			t.add(new Method("equals", Default.BOOLEAN_PRIMITIVE)
				.public_()
				.add(new Field("other", Default.OBJECT))
				.add(Default.OVERRIDE)
				.add("if (other == null) return false;" + nl())
				.add(
					"if (!getClass().equals(other.getClass())) " + block(
						"return false;"
					) + nl()
				)
				.add("final " + type + " o = (" + type + ") other;")
				.add(t.getFields().stream().map(f -> compare(t, f)).collect(
					Collectors.joining(nl() + " && ", "return (", ");")
				))
			);
		}
	}
	
	private String compare(T t, Field f) {
		if (isPrimitive(f.getType())) {
			return "(" + f.getName() + " == o." + f.getName() + ")";
		} else {
			if (!hasImportedObjects) {
				t.add(new Import(new Type(Objects.class)));
				hasImportedObjects = true;
			}
			return "Objects.equals(this." + f.getName() + ", o." + f.getName() + ")";
		}
	}
	
	private boolean isPrimitive(Type type) {
		switch (type.getName()) {
			case "byte" : case "short" : case "int" : case "long" : 
			case "float" : case "double" : case "boolean" : case "char" :
				return true;
			default:
				return false;
		}
	}
	
	private boolean hasMethod(T t, String method, int params) {
		Method found = null;
		
		for (Method m : t.getMethods()) {
			if (method.equals(m.getName())
			&&  m.getFields().size() == params) {
				found = m;
				break;
			}
		}
		
		return found != null;
	}
}

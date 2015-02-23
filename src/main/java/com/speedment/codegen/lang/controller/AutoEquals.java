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
	private boolean hasImportedObjects = false;
	
	@Override
	public void accept(T t) {
		t.getFields();
		hasImportedObjects = false;
		
		if (!hasMethod(t, "equals", 1)) {
			final String type = shortName(t.getName());
			t.add(new Method("equals", Default.BOOLEAN_PRIMITIVE)
				.add(Default.OVERRIDE)
				.public_()
				.add(new Field("other", Default.OBJECT))
				
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
		
		if (!hasMethod(t, "hashCode", 0)) {
			t.add(new Method("hashCode", Default.INT_PRIMITIVE)
				.add(Default.OVERRIDE)
				.public_()
				.add("int hash = 7;")
				.add(t.getFields().stream().map(f -> hash(f)).collect(Collectors.joining(nl())))
				.add("return hash;")
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
	
	private String hash(Field f) {
		final String prefix = "hash = 13 * hash + ";
		final String suffix = ".hashCode(this." + f.getName() + ");";

		switch (f.getType().getName()) {
			case "byte" : return prefix + "Byte" + suffix;
			case "short" : return prefix + "Short" + suffix;
			case "int" : return prefix + "Integer" + suffix;
			case "long" : return prefix + "Long" + suffix;
			case "float" : return prefix + "Float" + suffix;
			case "double" : return prefix + "Double" + suffix;
			case "boolean" : return prefix + "Boolean" + suffix;
			case "char" : return prefix + "Character" + suffix;
			default: return prefix + "this." + f.getName() + ".hashCode();";
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

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
package com.speedment.codegen.lang.controller;

import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Method;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.lang.models.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Emil Forslund
 */
public class SetGet implements Consumer<Class> {
	private final static String 
		SET_STRING = "set",
		GET_STRING = "get",
		THIS_STRING = "this.",
		ASSIGN_STRING = " = ",
		RETURN_STRING = "return this";
	
	private final List<String> methods;
	
	public SetGet() {
		this.methods = Collections.EMPTY_LIST;
	}
	
	public SetGet(String... methods) {
		this.methods = Arrays.asList(methods);
	}
	
	@Override
	public void accept(Class model) {
		model.getFields().stream().forEach(f -> {
			f.private_();
			final String setName = SET_STRING + ucfirst(f.getName());
			if (includeMethod(model, setName)) {
				model.add(new Method(setName, model.asType())
					.public_()
					.add(new Field(f.getName(), f.getType()))
					.add(THIS_STRING + f.getName() + ASSIGN_STRING + f.getName() + SC)
					.add(RETURN_STRING + SC)
				);
			}
			
			f.private_();
			final String getName = GET_STRING + ucfirst(f.getName());
			if (includeMethod(model, getName)) {
				model.add(new Method(getName, f.getType())
					.public_()
					.add(RETURN_STRING + DOT + f.getName() + SC)
				);
			}
		});
	}

	private boolean includeMethod(Class class_, String method) {
		if (methods.isEmpty() || methods.contains(method)) {
			return !class_.getMethods().stream().anyMatch(
					m -> method.equals(m.getName())
			);
		} else {
			return false;
		}
	}
}
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
package com.speedment.codegen.java.controller;

import com.speedment.codegen.base.CodeController;
import com.speedment.codegen.java.models.Class_;
import com.speedment.codegen.java.models.Method_;
import com.speedment.util.$;
import static com.speedment.codegen.Formatting.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Emil Forslund
 */
public class SetGet implements CodeController<Class_> {
	private final static CharSequence 
		SET_STRING = "set",
		GET_STRING = "get",
		THIS_STRING = "this.",
		ASSIGN_STRING = " = ",
		RETURN_STRING = "return this";
	
	private final List<CharSequence> methods;
	
	public SetGet() {
		this.methods = Collections.EMPTY_LIST;
	}
	
	public SetGet(CharSequence... methods) {
		this.methods = Arrays.asList(methods);
	}
	
	@Override
	public void accept(Class_ model) {
		model.getFields().stream().forEach(f -> {
			f.private_();
			final CharSequence setName = new $(SET_STRING, ucfirst(f.getName()));
			if (includeMethod(model, setName)) {
				model.add(new Method_(setName, model.asType())
					.public_()
					.add(f.clone())
					.add(new $(THIS_STRING, f.getName(), ASSIGN_STRING, f.getName(), SC))
					.add(new $(RETURN_STRING, SC))
				);
			}
			
			f.private_();
			final CharSequence getName = new $(GET_STRING, ucfirst(f.getName()));
			if (includeMethod(model, getName)) {
				model.add(new Method_(getName, f.getType())
					.public_()
					.add(new $(RETURN_STRING, DOT, f.getName(), SC))
				);
			}
		});
	}

	private boolean includeMethod(Class_ class_, CharSequence method) {
		if (methods.isEmpty() || methods.contains(method)) {
			return !class_.getMethods().stream().anyMatch(
					m -> method.equals(m.getName())
			);
		} else {
			return false;
		}
	}
}
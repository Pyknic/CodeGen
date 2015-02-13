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
package com.speedment.codegen.java.views;

import com.speedment.codegen.Formatting;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Interface;
import com.speedment.codegen.lang.models.InterfaceField;
import com.speedment.codegen.lang.models.InterfaceMethod;
import com.speedment.codegen.lang.models.Method;

/**
 *
 * @author Emil Forslund
 */
public class InterfaceView extends ClassOrInterfaceView<Interface> {
	@Override
	protected String classOrInterfaceLabel() {
		return INTERFACE_STRING;
	}

	@Override
	protected String extendsOrImplementsLabel() {
		return EXTENDS_STRING;
	}

	@Override
	protected String onSuperType(CodeGenerator cg, Interface model) {
		return Formatting.EMPTY;
	}

	@Override
	protected Object wrapField(Field field) {
		return new InterfaceField(field);
	}

	@Override
	protected Object wrapMethod(Method method) {
		return new InterfaceMethod(method);
	}
}
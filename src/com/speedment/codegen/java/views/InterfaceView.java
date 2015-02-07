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
import static com.speedment.codegen.Formatting.dnl;
import static com.speedment.codegen.Formatting.nl;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.Version;
import com.speedment.codegen.java.models.Interface;
import com.speedment.codegen.java.models.InterfaceMethod;
import com.speedment.util.CodeCombiner;

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
	protected <V extends Version<V>> String onMethods(CodeGenerator<V> cg, Interface model) {
		return model.getMethods().stream()
			.map(m -> cg.on(new InterfaceMethod(m)))
			.filter(m -> m.isPresent())
			.map(m -> m.get())
			.collect(CodeCombiner.joinIfNotEmpty(dnl()));
	}
}
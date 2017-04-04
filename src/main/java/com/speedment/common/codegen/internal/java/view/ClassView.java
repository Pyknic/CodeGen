/**
 *
 * Copyright (c) 2006-2017, Speedment, Inc. All Rights Reserved.
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
package com.speedment.common.codegen.internal.java.view;

import com.speedment.common.codegen.Generator;
import com.speedment.common.codegen.model.Class;

import static com.speedment.common.codegen.util.Formatting.dnl;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

/**
 * Transforms from a {@link Class} to java code.
 * 
 * @author Emil Forslund
 */
public final class ClassView extends ClassOrInterfaceView<Class> {

	@Override
	protected String renderDeclarationType() {
		return CLASS_STRING;
	}

	@Override
	public String extendsOrImplementsInterfaces() {
		return IMPLEMENTS_STRING;
	}

	@Override
	protected String renderSupertype(Generator gen, Class model) {
        requireNonNull(gen);
        requireNonNull(model);
        
		if (model.getSupertype().isPresent()) {
			return EXTENDS_STRING + gen.on(model.getSupertype().get()).orElse("") + " ";
		} else {
			return "";
		}
	}

    @Override
    protected String renderConstructors(Generator gen, Class model) {
        requireNonNull(gen);
        requireNonNull(model);
        
		return gen.onEach(model.getConstructors())
            .collect(joining(dnl()));
    }
}
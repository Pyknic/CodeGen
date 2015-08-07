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
package com.speedment.codegen.java.views;

import static com.speedment.codegen.util.Formatting.*;
import com.speedment.codegen.base.Generator;
import com.speedment.codegen.lang.models.Class;
import static java.util.stream.Collectors.joining;

/**
 * Transforms from a {@link Class} to java code.
 * 
 * @author Emil Forslund
 */
public class ClassView extends ClassOrInterfaceView<Class> {
    
    /**
     * {@inheritDoc}
     */
	@Override
	protected String renderDeclarationType() {
		return CLASS_STRING;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public String extendsOrImplementsInterfaces() {
		return IMPLEMENTS_STRING;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	protected String renderSupertype(Generator gen, Class model) {
		if (model.getSupertype().isPresent()) {
			return EXTENDS_STRING + gen.on(model.getSupertype().get()).orElse(EMPTY) + SPACE;
		} else {
			return EMPTY;
		}
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected String renderConstructors(Generator gen, Class model) {
		return gen.onEach(model.getConstructors())
            .collect(joining(dnl()));
    }
}
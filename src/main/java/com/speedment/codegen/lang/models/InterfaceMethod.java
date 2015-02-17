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
package com.speedment.codegen.lang.models;

import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.codegen.lang.models.modifiers.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class InterfaceMethod implements Copyable<InterfaceMethod> {
	private final Method m;
	
	public InterfaceMethod(Method wrapped) {
		this.m = wrapped;
	}
	
	public String getName() {
		return m.getName();
	}

	public Type getType() {
		return m.getType();
	}

	public List<Field> getParams() {
		return m.getParams();
	}

	public List<String> getCode() {
		return m.getCode();
	}

	public Set<Modifier> getModifiers() {
		return m.getModifiers();
	}

	public Optional<Javadoc> getJavadoc() {
		return m.getJavadoc();
	}

	public List<AnnotationUsage> getAnnotations() {
		return m.getAnnotations();
	}

	@Override
	public InterfaceMethod copy() {
		return new InterfaceMethod(m.copy());
	}
}
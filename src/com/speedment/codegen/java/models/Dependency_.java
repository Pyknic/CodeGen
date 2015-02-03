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
package com.speedment.codegen.java.models;

import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.java.Typeable;
import com.speedment.codegen.java.models.modifiers.DependencyModifier;
import com.speedment.codegen.java.models.modifiers.Modifier_;
import java.util.EnumSet;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class Dependency_ implements CodeModel<Dependency_>, 
		Typeable<Dependency_>,
		DependencyModifier<Dependency_> {
	
	private Type_ type;
	private final Set<Modifier_> modifiers = EnumSet.noneOf(Modifier_.class);

	public Dependency_() {}

	public Dependency_(Type_ type) {
		this.type = type;
	}

	@Override
	public Dependency_ setType(Type_ type) {
		this.type = type;
		return this;
	}

	@Override
	public Type_ getType() {
		return type;
	}

	@Override
	public Set<Modifier_> getModifiers() {
		return this.modifiers;
	}
}

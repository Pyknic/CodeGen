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
import com.speedment.codegen.java.interfaces.Typeable;
import com.speedment.codegen.java.models.modifiers.DependencyModifier;
import com.speedment.codegen.java.models.modifiers.Modifier_;
import com.speedment.util.Copier;
import java.util.EnumSet;

/**
 *
 * @author Emil Forslund
 */
public class Import_ implements CodeModel<Import_>, 
		Typeable<Import_>,
		DependencyModifier<Import_> {
	
	private Type_ type;
	private final EnumSet<Modifier_> modifiers;

	public Import_(Type_ type) {
		this.type = type;
		this.modifiers = EnumSet.noneOf(Modifier_.class);
	}
	
	private Import_(Import_ prototype) {
		type		= type.copy();
		modifiers	= Copier.copy(prototype.modifiers);
	}

	@Override
	public Import_ setType(Type_ type) {
		this.type = type;
		return this;
	}

	@Override
	public Type_ getType() {
		return type;
	}

	@Override
	public EnumSet<Modifier_> getModifiers() {
		return this.modifiers;
	}

	@Override
	public Import_ copy() {
		return new Import_(this);
	}
}

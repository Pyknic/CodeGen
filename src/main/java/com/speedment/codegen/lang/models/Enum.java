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

import com.speedment.codegen.lang.interfaces.Constructable;
import com.speedment.codegen.lang.models.modifiers.EnumModifier;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil Forslund
 */
public class Enum extends ClassOrInterface<Enum> implements 
		EnumModifier<Enum>,
		Constructable<Enum> {
	
	private final List<EnumConstant> constants;
	private final List<Constructor> constructors;
	
	public Enum(String name) {
		super(name);
		constants = new ArrayList<>();
		constructors = new ArrayList<>();
	}
	
	private Enum(Enum prototype) {
		super (prototype);
		constants = Copier.copy(prototype.constants);
		constructors = Copier.copy(prototype.constructors);
	}
	
	public Enum add(EnumConstant constant) {
		constants.add(constant);
		return this;
	}
	
	public List<EnumConstant> getConstants() {
		return constants;
	}
	
	@Override
	public Enum add(Constructor constr) {
		constructors.add(constr);
		return this;
	}
	
	@Override
	public List<Constructor> getConstructors() {
		return constructors;
	}

	@Override
	public Enum copy() {
		return new Enum(this);
	}
}
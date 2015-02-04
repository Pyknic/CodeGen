/*
 * Copyright 2015 Duncan.
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

import com.speedment.codegen.java.models.modifiers.EnumModifier;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duncan
 */
public class Enum_ extends ClassOrInterface_<Enum_> implements EnumModifier<Enum_> {
	private final List<EnumConstant_> constants;
	
	public Enum_(CharSequence name) {
		super(name);
		constants = new ArrayList<>();
	}
	
	private Enum_(Enum_ prototype) {
		super (prototype);
		constants = Copier.copy(prototype.constants);
	}
	
	public Enum_ add(EnumConstant_ constant) {
		constants.add(constant);
		return this;
	}
	
	public List<EnumConstant_> getConstants() {
		return constants;
	}

	@Override
	public Enum_ copy() {
		return new Enum_(this);
	}
}

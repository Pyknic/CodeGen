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

import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.java.interfaces.Nameable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duncan
 */
public class EnumConstant_ implements CodeModel<EnumConstant_>, 
		Nameable<EnumConstant_> {
	
	private CharSequence name;
	private final List<Value_> values = new ArrayList<>();

	public EnumConstant_(CharSequence name) {
		this.name = name;
	}

	@Override
	public EnumConstant_ setName(CharSequence name) {
		this.name = name;
		return this;
	}

	@Override
	public CharSequence getName() {
		return name;
	}
	
	public EnumConstant_ add(Value_ value) {
		values.add(value);
		return this;
	}
	
	public List<Value_> getValues() {
		return values;
	}
}

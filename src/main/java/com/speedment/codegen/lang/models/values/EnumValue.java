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
package com.speedment.codegen.lang.models.values;

import com.speedment.codegen.lang.interfaces.Typeable;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.implementation.ValueImpl;
import java.util.Objects;

/**
 *
 * @author Emil Forslund
 */
public class EnumValue extends ValueImpl<String>
		implements Typeable<EnumValue> {
	
	private Type type;
	
	public EnumValue(Type type, String value) { 
		super (value); 
		this.type = type;
	}
	
	private EnumValue(EnumValue prototype) {
		this (prototype.type, prototype.getValue());
	}

	@Override
	public EnumValue setType(Type type) {
		this.type = type;
		return this;
	}

	@Override
	public Type getType() {
		return type;
	}
	
	@Override
	public EnumValue copy() {
		return new EnumValue(this);
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 59 * hash + Objects.hashCode(this.type);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		final EnumValue other = (EnumValue) obj;
		if (!Objects.equals(this.type, other.type)) {
			return false;
		}
		
		return super.equals(obj);
	}
}
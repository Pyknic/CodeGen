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
package com.speedment.codegen.lang.models.implementation;

import com.speedment.codegen.lang.models.AnnotationUsage;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.Value;
import com.speedment.util.Copier;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationUsageImpl implements AnnotationUsage {
	
	private Type type;
	private Optional<Value> value;
	private final List<Entry<String, Value<?>>> values;
	
	public AnnotationUsageImpl(Type type) {
		this.type	= type;
		this.value	= Optional.empty();
		this.values = new ArrayList<>();
	}
	
	private AnnotationUsageImpl(AnnotationUsageImpl prototype) {
		type   = prototype.type.copy();
		value  = Copier.copy(prototype.value);
		values = Copier.copy(prototype.values, 
			e -> new AbstractMap.SimpleEntry<>(
				e.getKey(), e.getValue().copy()
			)
		);
	}
	
	@Override
	public AnnotationUsage setValue(Value val) {
		value = Optional.of(val);
		return this;
	}
	
    @Override
	public AnnotationUsage setValue(String key, Value val) {
		values.add(new AbstractMap.SimpleEntry<>(key, val));
		return this;
	}
	
	@Override
	public Optional<Value> getValue() {
		return value;
	}
	
    @Override
	public List<Entry<String, Value<?>>> getValues() {
		return values;
	}

	@Override
	public AnnotationUsage setType(Type type) {
		this.type = type;
		return this;
	}

	@Override
	public Type getType() {
		return type;
	}
    
    @Override
	public AnnotationUsageImpl copy() {
		return new AnnotationUsageImpl(this);
	}
	
	public final static class Const extends AnnotationUsageImpl {
		public Const(Type type) { 
			super(type); 
		}

		@Override
		public AnnotationUsage setValue(Value val) {
			return copy().setValue(val);
		}
		
		@Override
		public AnnotationUsage setValue(String key, Value val) {
			return copy().setValue(key, val);
		}

		@Override
		public AnnotationUsage setType(Type type) {
			return copy().setType(type);
		}
	}
}

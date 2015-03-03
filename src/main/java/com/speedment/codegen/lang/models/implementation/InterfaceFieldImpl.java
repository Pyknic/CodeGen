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
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.InterfaceField;
import com.speedment.codegen.lang.models.Javadoc;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.Value;
import com.speedment.codegen.lang.models.modifiers.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class InterfaceFieldImpl implements InterfaceField {
	private final Field f;
	
	public InterfaceFieldImpl(Field wrapped) {
		f = wrapped;
	}

    @Override
	public String getName() {
		return f.getName();
	}
	
    @Override
	public Type getType() {
		return f.getType();
	}

    @Override
	public Set<Modifier> getModifiers() {
		return f.getModifiers();
	}

    @Override
	public Optional<Javadoc> getJavadoc() {
		return f.getJavadoc();
	}

    @Override
	public Optional<Value<?>> getValue() {
		return f.getValue();
	}

    @Override
	public List<AnnotationUsage> getAnnotations() {
		return f.getAnnotations();
	}

    @Override
    public InterfaceField setName(String name) {
        f.setName(name);
        return this;
    }

    @Override
    public InterfaceField setType(Type type) {
        f.setType(type);
        return this;
    }

    @Override
    public InterfaceField setJavadoc(Javadoc doc) {
        f.setJavadoc(doc);
        return this;
    }

    @Override
    public InterfaceField setValue(Value<?> val) {
        f.setValue(val);
        return this;
    }
    
	@Override
	public InterfaceFieldImpl copy() {
		return new InterfaceFieldImpl(f.copy());
	}
}
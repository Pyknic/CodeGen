/**
 *
 * Copyright (c) 2006-2017, Speedment, Inc. All Rights Reserved.
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
package com.speedment.common.codegen.internal.model;

import com.speedment.common.codegen.internal.util.Copier;
import com.speedment.common.codegen.model.Class;
import com.speedment.common.codegen.model.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This is the default implementation of the {@link Class} interface.
 * This class should not be instantiated directly. Instead you should call the
 * {@link Class#of(String)} method to get an instance. In that way,
 * you can layer change the implementing class without modifying the using code.
 * 
 * @author Emil Forslund
 * @see    Class
 */
public final class ClassImpl extends ClassOrInterfaceImpl<Class> implements Class {

	private Type superType;
	private final List<Constructor> constructors;

    /**
     * Initializes this class using a name.
     * <p>
     * <b>Warning!</b> This class should not be instantiated directly but using 
     * the {@link Class#of(String)} method!
     * 
     * @param name  the name
     */
	public ClassImpl(String name) {
		super(name);
		superType = null;
		constructors = new ArrayList<>();
	}
	
    /**
     * Copy constructor.
     * 
     * @param prototype  the prototype
     */
	protected ClassImpl(Class prototype) {
		super (prototype);
		this.superType = prototype.getSupertype().orElse(null);
		this.constructors = Copier.copy(prototype.getConstructors());
	}

	@Override
	public Class setSupertype(Type superType) {
		this.superType = superType;
		return this;
	}

	@Override
	public Optional<Type> getSupertype() {
		return Optional.ofNullable(superType);
	}

	@Override
	public List<Constructor> getConstructors() {
		return constructors;
	}

    @Override
	public ClassImpl copy() {
		return new ClassImpl(this);
	}

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.superType);
        hash = 11 * hash + Objects.hashCode(this.constructors);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClassImpl other = (ClassImpl) obj;
        if (!Objects.equals(this.superType, other.superType)) {
            return false;
        }
        return Objects.equals(this.constructors, other.constructors);
    }


}
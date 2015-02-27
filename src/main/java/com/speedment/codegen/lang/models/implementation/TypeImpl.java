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
import com.speedment.codegen.lang.models.Generic;
import com.speedment.codegen.lang.models.Type;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class TypeImpl implements Type {

    private String name;
    private int arrayDimension;
    private final List<AnnotationUsage> annotations;
    private final List<Generic> generics;
    private Optional<java.lang.Class<?>> javaImpl;

    public TypeImpl(java.lang.Class<?> javaImpl) {
        this(javaImpl.getName(), javaImpl);
    }

    public TypeImpl(String name) {
        this(name, null);
    }

    public TypeImpl(String name, java.lang.Class<?> javaImpl) {
        this.name = name;
        this.arrayDimension = 0;
        this.annotations = new ArrayList<>();
        this.generics = new ArrayList<>();
        this.javaImpl = Optional.ofNullable(javaImpl);
    }

    private TypeImpl(TypeImpl prototype) {
        name = prototype.name;
        arrayDimension = prototype.arrayDimension;
        annotations = Copier.copy(prototype.annotations);
        generics = Copier.copy(prototype.generics);
        javaImpl = prototype.javaImpl;
	}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Optional<java.lang.Class<?>> getJavaImpl() {
        return javaImpl;
    }

    @Override
    public Type setJavaImpl(java.lang.Class<?> javaImpl) {
        this.javaImpl = Optional.of(javaImpl);
        this.name = javaImpl.getName();
        return this;
    }

    @Override
    public int getArrayDimension() {
        return arrayDimension;
    }

    @Override
    public Type setArrayDimension(int arrayDimension) {
        this.arrayDimension = arrayDimension;
        return this;
    }

    @Override
    public List<Generic> getGenerics() {
        return generics;
    }
    
    @Override
    public List<AnnotationUsage> getAnnotations() {
        return annotations;
    }
    
    @Override
    public TypeImpl copy() {
        return new TypeImpl(this);
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj instanceof TypeImpl) {
			final TypeImpl other = (TypeImpl) obj;
			return other.name.equals(name)
				&& other.arrayDimension == arrayDimension
				&& other.annotations.size() == annotations.size()
				&& other.generics.size() == generics.size()
				&& Arrays.deepEquals(other.annotations.toArray(), annotations.toArray())
				&& Arrays.deepEquals(other.generics.toArray(), generics.toArray())
				&& other.javaImpl.equals(javaImpl)
			;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 61 * hash + Objects.hashCode(this.name);
		hash = 61 * hash + this.arrayDimension;
		hash = 61 * hash + Objects.hashCode(this.annotations);
		hash = 61 * hash + Objects.hashCode(this.generics);
		hash = 61 * hash + Objects.hashCode(this.javaImpl);
		return hash;
	}

    public static final class Const extends TypeImpl {

        public Const(java.lang.Class<?> javaImpl) {
            super(javaImpl);
        }

        public Const(String name) {
            super(name);
        }

        public Const(String name, java.lang.Class<?> javaImpl) {
            super(name, javaImpl);
        }

        @Override
        public Type setArrayDimension(int arrayDimension) {
            return copy().setArrayDimension(arrayDimension);
        }

        @Override
        public Type setJavaImpl(java.lang.Class<?> javaImpl) {
            return copy().setJavaImpl(javaImpl);
        }

        @Override
        public Type setName(String name) {
            return copy().setName(name);
        }

        @Override
        public Type add(AnnotationUsage annotation) {
            return copy().add(annotation);
        }

        @Override
        public Type add(Generic generic) {
            return copy().add(generic);
        }
    }
}
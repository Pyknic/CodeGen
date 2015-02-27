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

import com.speedment.codegen.lang.models.Generic;
import com.speedment.codegen.lang.models.Type;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class GenericImpl implements Generic {
	
	private Optional<String> lowerBound;
	private final List<Type> upperBounds;

	private BoundType type = BoundType.EXTENDS;
	
	public GenericImpl() {
		lowerBound  = Optional.empty();
		upperBounds = new ArrayList<>();
	}

	public GenericImpl(String lowerBound) {
		this(lowerBound, new Type[0]);
	}
	
	public GenericImpl(Type... upperBounds) {
		this(null, upperBounds);
	}
	
	public GenericImpl(String lowerBound, Type... upperBounds) {
		this.lowerBound = Optional.ofNullable(lowerBound);
		this.upperBounds = Arrays.asList(upperBounds);
	}
	
	private GenericImpl(GenericImpl prototype) {
		lowerBound  = Copier.copy(prototype.lowerBound, s -> s);
		upperBounds = Copier.copy(prototype.upperBounds);
	}

    @Override
	public Generic setLowerBound(String lowerBound) {
		this.lowerBound = Optional.of(lowerBound);
		return this;
	}

    @Override
	public Optional<String> getLowerBound() {
		return lowerBound;
	}
	
    @Override
	public Generic setBoundType(BoundType type) {
		this.type = type;
		return this;
	}
	
    @Override
	public BoundType getBoundType() {
		return type;
	}

    @Override
	public List<Type> getUpperBounds() {
		return upperBounds;
	}
	
    @Override
	public Optional<Type> asType() {
		if (lowerBound.isPresent()) {
			return Optional.of(new TypeImpl(lowerBound.get()));
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public GenericImpl copy() {
		return new GenericImpl(this);
	}
}
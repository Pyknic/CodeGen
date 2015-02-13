/*
 * Copyright 2015 Duncan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance copy the License.
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
package com.speedment.codegen.lang.models;

import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Duncan
 */
public class Generic implements Copyable<Generic> {
	
	private Optional<String> lowerBound;
	private final List<Type> upperBounds;
	
	public static enum BoundType {UPPER, LOWER};
	private BoundType type = BoundType.UPPER;
	
	public Generic() {
		lowerBound  = Optional.empty();
		upperBounds = new ArrayList<>();
	}

	public Generic(String lowerBound) {
		this(lowerBound, new Type[0]);
	}
	
	public Generic(Type... upperBounds) {
		this(null, upperBounds);
	}
	
	public Generic(String lowerBound, Type... upperBounds) {
		this.lowerBound = Optional.ofNullable(lowerBound);
		this.upperBounds = Arrays.asList(upperBounds);
	}
	
	private Generic(Generic prototype) {
		lowerBound  = Copier.copy(prototype.lowerBound, c -> c);
		upperBounds = Copier.copy(prototype.upperBounds);
	}

	public Generic setLowerBound(String lowerBound) {
		this.lowerBound = Optional.of(lowerBound);
		return this;
	}

	public Optional<String> getLowerBound() {
		return lowerBound;
	}

	public Generic add(Type upperBound) {
		upperBounds.add(upperBound);
		return this;
	}
	
	public Generic setBoundType(BoundType type) {
		this.type = type;
		return this;
	}
	
	public BoundType getBoundType() {
		return type;
	}

	public List<Type> getUpperBounds() {
		return upperBounds;
	}
	
	public Optional<Type> asType() {
		if (lowerBound.isPresent()) {
			return Optional.of(new Type(lowerBound.get()));
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public Generic copy() {
		return new Generic(this);
	}
}
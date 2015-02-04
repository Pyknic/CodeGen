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
package com.speedment.codegen.java.models;

import com.speedment.codegen.base.CodeModel;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Duncan
 */
public class Generic_ implements CodeModel<Generic_> {
	
	private Optional<CharSequence> lowerBound;
	private final List<Type_> upperBounds;
	
	public static enum BoundType {UPPER, LOWER};
	private BoundType type = BoundType.UPPER;
	
	public Generic_() {
		lowerBound  = Optional.empty();
		upperBounds = new ArrayList<>();
	}

	public Generic_(CharSequence lowerBound) {
		this.lowerBound  = Optional.of(lowerBound);
		this.upperBounds = new ArrayList<>();
	}
	
	private Generic_(Generic_ prototype) {
		lowerBound  = Copier.copy(prototype.lowerBound, c -> c.toString());
		upperBounds = Copier.copy(prototype.upperBounds);
	}

	public Generic_ setLowerBound(CharSequence lowerBound) {
		this.lowerBound = Optional.of(lowerBound);
		return this;
	}

	public Optional<CharSequence> getLowerBound() {
		return lowerBound;
	}

	public Generic_ add(Type_ upperBound) {
		upperBounds.add(upperBound);
		return this;
	}
	
	public Generic_ setBoundType(BoundType type) {
		this.type = type;
		return this;
	}
	
	public BoundType getBoundType() {
		return type;
	}

	public List<Type_> getUpperBounds() {
		return upperBounds;
	}
	
	public Optional<Type_> asType() {
		if (lowerBound.isPresent()) {
			return Optional.of(new Type_(lowerBound.get()));
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public Generic_ copy() {
		return new Generic_(this);
	}
}
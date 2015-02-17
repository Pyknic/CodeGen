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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.lang.models;

import com.speedment.codegen.lang.interfaces.Copyable;

/**
 *
 * @author Emil Forslund
 * @param <T>
 * @param <R>
 */
public abstract class Value<T, R extends Value<T, R>> implements Copyable<R> {
	private T value;
	
	public Value(T val) {
		value = val;
	}

	public R setValue(T value) {
		this.value = value;
		return (R) this;
	}

	public T getValue() {
		return value;
	}

	@Override
	public abstract R copy();
}

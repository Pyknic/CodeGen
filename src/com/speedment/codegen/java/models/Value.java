/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.java.models;

import com.speedment.codegen.java.interfaces.Copyable;

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

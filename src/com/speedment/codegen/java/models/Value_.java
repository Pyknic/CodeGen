/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.java.models;

import com.speedment.codegen.base.CodeModel;

/**
 *
 * @author Emil Forslund
 * @param <T>
 * @param <R>
 */
public abstract class Value_<T, R extends Value_<T, R>> implements CodeModel<Value_<T, R>> {
	private T value;
	
	public Value_(T val) {
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

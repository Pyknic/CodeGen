package com.speedment.codegen.lang.interfaces;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public interface Copyable<T extends Copyable<T>> {
	public T copy();
}
package com.speedment.codegen.java.interfaces;

import java.util.function.Consumer;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public interface Callable<T> {
	default public T call(Consumer<T> procedure) {
		procedure.accept((T) this);
		return (T) this;
	}
}

package com.speedment.codegen.base;

import java.util.Map;

/**
 *
 * @author Emil Forslund
 */
public interface Installer {
	<M, V extends CodeView<M>> void install(Class<M> model, Class<V> view);
	<M, V extends CodeView<M>> Map<Class<M>, Class<V>> getInstallments();
	boolean hasInstallment(Class<?> model);
	CodeView get(Class<?> model);
}
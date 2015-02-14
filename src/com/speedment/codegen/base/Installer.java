package com.speedment.codegen.base;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Emil Forslund
 */
public interface Installer {
	/**
	 * Installs the specified View.
	 * @param <M>
	 * @param <V>
	 * @param model The model.
	 * @param view The view.
	 */
	<M, V extends CodeView<M>> void install(Class<M> model, Class<V> view);
	
	/**
	 * Returns a view if there is one that matched the specified model.
	 * @param model The model.
	 * @return The view or empty as an Optional.
	 */
	Optional<CodeView> withOne(Class<?> model);
	
	/**
	 * Builds a stream of all views that match the specified model.
	 * @param model The model.
	 * @return A stream of all matching views.
	 */
	Stream<CodeView> withAll(Class<?> model);
	
	/**
	 * Instantiates the specified class and returns it.
	 * @param <T>
	 * @param clazz
	 * @return 
	 */
	public static <T> T create(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			Logger.getLogger(DefaultInstaller.class.getName()).log(Level.SEVERE, 
				"The class '" + clazz.getName() + 
				"' could not be instantiated using the default constructor. " +
				"Make sure it is the correct class and that the default " +
				"constructor has been properly defined without no parameters.", ex);
		}
		
		return null;
	}
}
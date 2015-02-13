package com.speedment.codegen.base;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emil Forslund
 */
public class DefaultInstaller implements Installer {
	private final Map<Class<?>, Class<? extends CodeView>> 
			modelToView = new HashMap<>();

	@Override
	public <M, V extends CodeView<M>> void install(Class<M> model, Class<V> view) {
		modelToView.put(model, view);
	}
			
	@Override
	public boolean hasInstallment(Class<?> model) {
		return modelToView.containsKey(model);
	}

	@Override
	public Map<Class<?>, Class<? extends CodeView>> getInstallments() {
		return modelToView;
	}

	@Override
	public CodeView get(Class<?> model) {
		return create(viewOf(model));
	}
	
	private Class<? extends CodeView> viewOf(Class<?> model) {
		final Class<? extends CodeView> result = modelToView.get(model);
		
		if (result == null) {
			for (Map.Entry<Class<?>, Class<? extends CodeView>> e : modelToView.entrySet()) {
				if (e.getKey().isAssignableFrom(model)) {
					return e.getValue();
				}
			}
		} else {
			return result;
		}
		
		throw new UnsupportedOperationException(
			"Attemting to view the model '" + model.getName() + 
			"' that is not associated in the current Version." +
			"Please make sure there exists a view for each model " + 
			"and that they have been properly installed to the " +
			"class extending Version."
		);
	}
	
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
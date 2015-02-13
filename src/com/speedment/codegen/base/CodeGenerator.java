package com.speedment.codegen.base;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author Emil Forslund
 */
public interface CodeGenerator {
	/**
	 * @return the installer used in this generator.
	 */
	Installer getInstaller();
	
	/**
	 * @return the dependency manager.
	 */
	DependencyManager getDependencyMgr();
	
	/**
	 * Locates the <code>CodeView</code> that corresponds to the specified model
	 * and uses it to generate a String. If no view is associated with the 
	 * model type, a <code>NullPointerException</code> will be thrown.
	 * 
	 * The result will be a <code>Optional</code>. It is present only if the
	 * result from the view is present.
	 * 
	 * @param model The model.
	 * @return The viewed text if any.
	 */
	Optional<String> on(Object model);
	
	/**
	 * The same as <code>on(CodeModel)</code> except it takes an <code>Optional\<CodeModel\></code>
	 * instead. The model will be checked to see if it is present before calling
	 * the standard <code>on</code>-method. If the model is not present, an
	 * empty <code>Optional</code> will be returned.
	 * @param <T> The model class to render.
	 * @param model The model.
	 * @return The viewed text if any.
	 */
	<T> Optional<String> on(Optional<T> model);
	
	/**
	 * Attempts to generate a text from each of the models in the collection.
	 * The texts that are present will be added to a stream that is then returned.
	 * @param <T>
	 * @param models A collection of models to view.
	 * @return The viewed text.
	 */
	public <T> Stream<String> onEach(Collection<T> models);
	
	/**
	 * Locates the <code>CodeView</code> that corresponds to the specified model
	 * and uses it to generate a String. If no view is associated with the 
	 * model type, a <code>NullPointerException</code> will be thrown.
	 * 
	 * Since views may not return a result for a particular model, the consumer
	 * might not be called.
	 * 
	 * @param model The model.
	 * @param consumer The consumer to accept the resulting String.
	 */
	public void on(Object model, Consumer<String> consumer);
	
	/**
	 * The same as <code>on(CodeModel)</code> except it takes an <code>Optional\<CodeModel\></code>
	 * instead. The model will be checked to see if it is present before calling
	 * the standard <code>on</code>-method. If the model is not present or the
	 * view does not render any result, the consumer will not be called.
	 * @param <T> The model class to render.
	 * @param model The model.
	 * @param consumer The consumer to accept the result.
	 */
	public <T> void on(Optional<T> model, Consumer<String> consumer);
	
	/**
	 * Attempts to generate a text from each of the models in the collection.
	 * The texts that are present will be added to a stream that is then returned.
	 * @param <T> The model class to render.
	 * @param models A collection of models to view.
	 * @param consumer The consumer to accept the result.
	 */
	public <T> void onEach(Collection<T> models, Consumer<String> consumer);
}

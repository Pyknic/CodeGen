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
package com.speedment.codegen.base;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author Emil Forslund
 */
public interface CodeGenerator {	
	/**
	 * @return the dependency manager.
	 */
	DependencyManager getDependencyMgr();
	
	/**
	 * Returns the current rendering stack. The top element will be the one most
	 * recent rendered and the bottom one will be the element that was first
	 * passed to the generator. Elements are removed from the stack once they
	 * have finished rendering.
	 * 
	 * If an element needs to access its parent, it can call this method and
	 * peek on the second element from the top.
	 * 
	 * The elements in the Stack will be of Object type. That is because the
	 * framework doesn't put any constraints on what can be rendered.
	 * The elements should not be cast directly to the model class but rather
	 * to an interface describing the properties you need to read. That way,
	 * the design remains dynamic even if the exact implementation isn't the
	 * same.
	 * 
	 * The returned Stack will be immutable.
	 * 
	 * @return the current rendering stack.
	 */
	List<Object> getRenderStack();
	
	/**
	 * Locates the <code>CodeView</code> that corresponds to the specified model
	 * and uses it to generate a String. If no view is associated with the 
	 * model type, a <code>UnsupportedOperationException</code> will be thrown.
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
	default public <T> Optional<String> on(Optional<T> model) {
		return model.flatMap(m -> on(m));
	}
	
	/**
	 * Attempts to generate a text from each of the models in the collection.
	 * The texts that are present will be added to a stream that is then returned.
	 * @param <T>
	 * @param models A collection of models to view.
	 * @return The viewed text.
	 */
	default public <T> Stream<String> onEach(Collection<T> models) {
		final Stream.Builder<String> build = Stream.builder();
		models.forEach(m -> {
			final Optional<String> str = on(m);
			if (str.isPresent()) build.add(str.get());
		});
		return build.build();
	}
	
	/**
	 * Locates the <code>CodeView</code> that corresponds to the specified model
	 * and uses it to generate a String. If no view is associated with the 
	 * model type, a <code>UnsupportedOperationException</code> will be thrown.
	 * 
	 * Since views may not return a result for a particular model, the consumer
	 * might not be called. If the same model has multiple views, they are all
	 * executed.
	 * 
	 * @param model The model.
	 * @param consumer The consumer to accept the resulting String.
	 */
	void on(Object model, Consumer<String> consumer);
	
	/**
	 * The same as <code>on(CodeModel)</code> except it takes an <code>Optional\<CodeModel\></code>
	 * instead. The model will be checked to see if it is present before calling
	 * the standard <code>on</code>-method. If the model is not present or the
	 * view does not render any result, the consumer will not be called.
	 * @param <T> The model class to render.
	 * @param model The model.
	 * @param consumer The consumer to accept the result.
	 */
	default public <T> void on(Optional<T> model, Consumer<String> consumer) {
		model.ifPresent(m -> on(m, consumer));
	}
	
	/**
	 * Attempts to generate a text from each of the models in the collection.
	 * The texts that are present will be added to a stream that is then returned.
	 * @param <T> The model class to render.
	 * @param models A collection of models to view.
	 * @param consumer The consumer to accept the result.
	 */
	default public <T> void onEach(Collection<T> models, Consumer<String> consumer) {
		models.forEach(m -> on(m, consumer));
	}
}

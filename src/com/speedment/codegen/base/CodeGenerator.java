/*
 * Copyright 2015 Emil Forslund.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.speedment.codegen.base;

import com.speedment.codegen.DefaultDependencyManager;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A <code>CodeGenerator</code> is the class that is called to view a model. The
 * class is instantiated with a particular <code>VersionEnum</code> so that it
 * can map inputed models to the appropriate view. 
 * 
 * The code generator can also be used to keep track of which dependencies has
 * been imported.
 * 
 * @author Emil Forslund
 */
public abstract class CodeGenerator {
	private final Version version;
	private final DependencyManager dependencyMgr;
	
	/**
	 * Initialises the code generator using a default dependency manager.
	 * See <code>CodeGenerator(Version, DefaultDependencyManager)</code> for a full
	 * description.
	 * @param version
	 */
	public CodeGenerator(Version version) {
		this(version, new DefaultDependencyManager());
	}
	
	/**
	 * Initialises a code generator based on the specified code version. The
	 * code version will be used to map models to the appropriate view.
	 * @param version A subclass of <code>Version</code>.
	 * @param mgr A DefaultDependencyManager to keep track of dependencies.
	 */
	public CodeGenerator(Version version, DependencyManager mgr) {
		this.version = version;
		this.dependencyMgr = mgr;
	}
	
	/**
	 * @return the dependency manager.
	 */
	public DependencyManager getDependencyMgr() {
		return dependencyMgr;
	}
	
	/**
	 * Locates the <code>CodeView</code> that corresponds to the specified model
	 * and uses it to generate a String. If no view is associated with the 
	 * model type, a <code>NullPointerException</code> will be thrown.
	 * 
	 * The result will be a <code>Optional</code>. It is present only if the
	 * result from the view is present.
	 * 
	 * @param m The model.
	 * @return The viewed text if any.
	 */
	public Optional<String> on(Object m) {
		return version.get(m.getClass()).render(this, m);
	}
	
	/**
	 * The same as <code>on(CodeModel)</code> except it takes an <code>Optional\<CodeModel\></code>
	 * instead. The model will be checked to see if it is present before calling
	 * the standard <code>on</code>-method. If the model is not present, an
	 * empty <code>Optional</code> will be returned.
	 * @param <M> The model class to render.
	 * @param m The model.
	 * @return The viewed text if any.
	 */
	public <M> Optional<String> on(Optional<M> m) {
		if (m.isPresent()) {
			return on(m.get());
		} else {
			return Optional.<String>empty();
		}
	}
	
	/**
	 * Attempts to generate a text from each of the models in the collection.
	 * The texts that are present will be added to a stream that is then returned.
	 * @param <M>
	 * @param models A collection of models to view.
	 * @return The viewed text.
	 */
	public <M> Stream<String> onEach(Collection<M> models) {
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
	 * model type, a <code>NullPointerException</code> will be thrown.
	 * 
	 * Since views may not return a result for a particular model, the consumer
	 * might not be called.
	 * 
	 * @param m The model.
	 * @param consumer The consumer to accept the resulting String.
	 * @return The viewed text if any.
	 */
	public CodeGenerator on(Object m, Consumer<String> consumer) {
		on(m).ifPresent(consumer);
		return this;
	}
	
	/**
	 * The same as <code>on(CodeModel)</code> except it takes an <code>Optional\<CodeModel\></code>
	 * instead. The model will be checked to see if it is present before calling
	 * the standard <code>on</code>-method. If the model is not present or the
	 * view does not render any result, the consumer will not be called.
	 * @param <M> The model class to render.
	 * @param m The model.
	 * @param consumer The consumer to accept the result.
	 * @return The viewed text if any.
	 */
	public <M> CodeGenerator on(Optional<M> m, Consumer<String> consumer) {
		if (m.isPresent()) {
			on(m.get(), consumer);
		}
		
		return this;
	}
	
	/**
	 * Attempts to generate a text from each of the models in the collection.
	 * The texts that are present will be added to a stream that is then returned.
	 * @param <M> The model class to render.
	 * @param models A collection of models to view.
	 * @param consumer The consumer to accept the result.
	 * @return The viewed text.
	 */
	public <M> CodeGenerator onEach(Collection<M> models, Consumer<String> consumer) {
		models.forEach(m -> on(m, consumer));
		return this;
	}
}
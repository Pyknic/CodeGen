/*
 * Copyright 2015 Emil Forslund.
 *
 * Licensed under the Apache License, DefaultInstaller 2.0 (the "License");
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

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A <code>DefaultCodeGenerator</code> is the class that is called to view a model. The
 * class is instantiated with a particular <code>VersionEnum</code> so that it
 * can map inputed models to the appropriate view. 
 * 
 * The code generator can also be used to keep track of which dependencies has
 * been imported.
 * 
 * @author Emil Forslund
 */
public class DefaultCodeGenerator implements CodeGenerator {
	private final Installer installer;
	private final DependencyManager dependencyMgr;
	
	/**
	 * Initialises the code generator using a default dependency manager.
	 * See <code>CodeGenerator(DefaultInstaller, DefaultDependencyManager)</code> for a full
	 * description.
	 * @param installer
	 */
	public DefaultCodeGenerator(Installer installer) {
		this(installer, new DefaultDependencyManager());
	}
	
	/**
	 * Initialises a code generator based on the specified code version. The
	 * code version will be used to map models to the appropriate view.
	 * @param installer A subclass of <code>DefaultInstaller</code>.
	 * @param mgr A DefaultDependencyManager to keep track of dependencies.
	 */
	public DefaultCodeGenerator(Installer installer, DependencyManager mgr) {
		this.installer = installer;
		this.dependencyMgr = mgr;
	}
	
	/**
	 * @return the installer used by this generator.
	 */
	@Override
	public Installer getInstaller() {
		return installer;
	}
	
	/**
	 * @return the dependency manager.
	 */
	@Override
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
	@Override
	public Optional<String> on(Object m) {
		return installer.get(m.getClass()).render(this, m);
	}
	
	/**
	 * The same as <code>on(CodeModel)</code> except it takes an <code>Optional\<CodeModel\></code>
	 * instead. The model will be checked to see if it is present before calling
	 * the standard <code>on</code>-method. If the model is not present, an
	 * empty <code>Optional</code> will be returned.
	 * @param <T> The model class to render.
	 * @param m The model.
	 * @return The viewed text if any.
	 */
	@Override
	public <T> Optional<String> on(Optional<T> m) {
		if (m.isPresent()) {
			return on(m.get());
		} else {
			return Optional.<String>empty();
		}
	}
	
	/**
	 * Attempts to generate a text from each of the models in the collection.
	 * The texts that are present will be added to a stream that is then returned.
	 * @param <T>
	 * @param models A collection of models to view.
	 * @return The viewed text.
	 */
	@Override
	public <T> Stream<String> onEach(Collection<T> models) {
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
	 */
	@Override
	public void on(Object m, Consumer<String> consumer) {
		on(m).ifPresent(consumer);
	}
	
	/**
	 * The same as <code>on(CodeModel)</code> except it takes an <code>Optional\<CodeModel\></code>
	 * instead. The model will be checked to see if it is present before calling
	 * the standard <code>on</code>-method. If the model is not present or the
	 * view does not render any result, the consumer will not be called.
	 * @param <T> The model class to render.
	 * @param m The model.
	 * @param consumer The consumer to accept the result.
	 */
	@Override
	public <T> void on(Optional<T> m, Consumer<String> consumer) {
		if (m.isPresent()) {
			on(m.get(), consumer);
		}
	}
	
	/**
	 * Attempts to generate a text from each of the models in the collection.
	 * The texts that are present will be added to a stream that is then returned.
	 * @param <T> The model class to render.
	 * @param models A collection of models to view.
	 * @param consumer The consumer to accept the result.
	 */
	@Override
	public <T> void onEach(Collection<T> models, Consumer<String> consumer) {
		models.forEach(m -> on(m, consumer));
	}
}
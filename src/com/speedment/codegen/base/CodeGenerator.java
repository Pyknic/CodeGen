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
import com.speedment.codegen.java.JavaGenerator;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @param <T> The code version to use when mapping models to views.
 */
public abstract class CodeGenerator<T extends Enum<T> & VersionEnum> {
	private final Map<T, CodeView> views;
	private final Class<T> tclass;
	private final DependencyManager dependencyMgr;
	
	/**
	 * Initialises the code generator using a default dependency manager.
	 * See <code>CodeGenerator(Class\<T\>, DefaultDependencyManager)</code> for a full
	 * description.
	 * @param tclass 
	 */
	public CodeGenerator(Class<T> tclass) {
		this(tclass, new DefaultDependencyManager());
	}
	
	/**
	 * Initialises a code generator based on the specified code version. The
	 * code version will be used to map models to the appropriate view.
	 * @param tclass An enum that implements <code>VersionEnum</code>.
	 * @param mgr A DefaultDependencyManager to keep track of dependencies.
	 */
	public CodeGenerator(Class<T> tclass, DependencyManager mgr) {
		views = new EnumMap<>(tclass);
		this.tclass = tclass;

		Arrays.asList(tclass.getEnumConstants()).forEach(e -> {
			try {
				views.put(e, e.getViewClass().newInstance());
			} catch (InstantiationException | IllegalAccessException ex) {
				Logger.getLogger(JavaGenerator.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		
		dependencyMgr = mgr;
	}
	
	/**
	 * @return the dependency manager.
	 */
	public DependencyManager getDependencyMgr() {
		return dependencyMgr;
	}
	
	/**
	 * Locates the <code>CodeView</code> that corresponds to the specified model
	 * and uses it to generate a CharSequence. If no view is associated with the 
	 * model type, a <code>NullPointerException</code> will be thrown.
	 * 
	 * The result will be a <code>Optional</code>. It is present only if the
	 * result from the view is present.
	 * 
	 * @param m The model.
	 * @return The viewed text if any.
	 */
	public Optional<CharSequence> on(CodeModel m) {
		return views.get(VersionEnum.of(m.getClass(), tclass)).render(this, m);
	}
	
	/**
	 * The same as <code>on(CodeModel)</code> except it takes an <code>Optional\<CodeModel\></code>
	 * instead. The model will be checked to see if it is present before calling
	 * the standard <code>on</code>-method. If the model is not present, an
	 * empty <code>Optional</code> will be returned.
	 * @param m The model.
	 * @return The viewed text if any.
	 */
	public Optional<CharSequence> on(Optional<CodeModel> m) {
		if (m.isPresent()) {
			return on(m.get());
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Attempts to generate a text from each of the models in the collection.
	 * The texts that are present will be added to a stream that is then returned.
	 * @param models A collection of models to view.
	 * @return The viewed text.
	 */
	public Stream<CharSequence> onEach(Collection<CodeModel> models) {
		final Stream.Builder<CharSequence> build = Stream.builder();
		models.forEach(m -> {
			final Optional<CharSequence> str = on(m);
			if (str.isPresent()) build.add(str.get());
		});
		return build.build();
	}
}
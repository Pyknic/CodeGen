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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A generator that can have multiple installers. If the same model is associated
 * with multiple views, only one will be called in the on-methods that return
 * a String, but all of them will be executed in the on-methods that takes a
 * consumer.
 * 
 * @author Emil Forslund
 */
public class MultiGenerator implements CodeGenerator {
	private final DependencyManager mgr;
	private final List<Installer> installers;
	
	/**
	 * Creates a new MultiGenerator.
	 * @param installers 
	 */
	public MultiGenerator(Installer... installers) {
		this(new DefaultDependencyManager(), installers);
	}
	
	/**
	 * Creates a new MultiGenerator.
	 * @param mgr
	 * @param installers 
	 */
	public MultiGenerator(DependencyManager mgr, Installer... installers) {
		this.installers = Arrays.asList(installers);
		this.mgr = mgr;
	}
	
	/**
	 * @return the dependency manager.
	 */
	@Override
	public DependencyManager getDependencyMgr() {
		return mgr;
	}

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
	@Override
	public Optional<String> on(Object model) {
		for (Installer i : installers) {
			final Optional<CodeView> view = i.withOne(model.getClass());
			if (view.isPresent()) {
				return view.get().render(this, model);
			}
		}
		
		return Optional.empty();
	}

	/**
	 * Locates the <code>CodeView</code> that corresponds to the specified model
	 * and uses it to generate a String. If no view is associated with the 
	 * model type, a <code>NullPointerException</code> will be thrown.
	 * 
	 * Since views may not return a result for a particular model, the consumer
	 * might not be called. If the same model has multiple views, they are all
	 * executed.
	 * 
	 * @param model The model.
	 * @param consumer The consumer to accept the resulting String.
	 */
	@Override
	public void on(Object model, Consumer<String> consumer) {
		installers.stream()
			.flatMap(i -> i.withAll(model.getClass()))
			.forEach(v -> v.render(this, model).ifPresent(consumer));
	}
}
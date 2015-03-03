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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Supplier;
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
	private final Stack<Object> renderStack;
	
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
		this.renderStack = new Stack<>();
	}
	
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
	@Override
	public List<Object> getRenderStack() {
		return Collections.unmodifiableList(renderStack);
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
	 * model type, a <code>UnsupportedOperationException</code> will be thrown.
	 * 
	 * The result will be a <code>Optional</code>. It is present only if the
	 * result from the view is present.
	 * 
	 * @param model The model.
	 * @return The viewed text if any.
	 */
	@Override
    @SuppressWarnings("unchecked")
	public Optional<String> on(Object model) {
		return render(
            (CodeView<Object>) installer
                .withOne(model.getClass())
                .orElseThrow(UnsupportedOperationException::new),
            model
        );
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
	@Override
    @SuppressWarnings("unchecked")
	public void on(Object model, Consumer<String> consumer) {
		final Supplier<Stream<CodeView<Object>>> supplier = () ->
            installer.withAll(model.getClass()).map(v -> (CodeView<Object>) v);
        
        if (supplier.get().anyMatch(v -> true)) {
            supplier.get().forEach(v -> render((CodeView<Object>) v, model));
        } else {
            throw new UnsupportedOperationException(
                "The model of type " + model.getClass().getName() + 
                " passed to DefaultCodeGenerator does not have a corresponding view."
            );
        }
	}
	
	private <M> Optional<String> render(CodeView<M> view, M model) {
		renderStack.push(model);
		final Optional<String> result = view.render(this, model);
		renderStack.pop();
		return result;
	}
}
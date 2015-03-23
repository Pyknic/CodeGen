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
import java.util.stream.Stream;

/**
 * A generator that can have multiple installers. If the same model is associated
 * with multiple views, only one will be called in the on-methods that return
 * a String, but all of them will be executed in the on-methods that takes a
 * consumer.
 * 
 * @author Emil Forslund
 */
public class DefaultGenerator implements Generator {
    
	private final DependencyManager mgr;
	private final List<Installer> installers;
	private final DefaultRenderStack renderStack;
	
	/**
	 * Creates a new MultiGenerator.
	 * @param installers 
	 */
	public DefaultGenerator(Installer... installers) {
		this(new DefaultDependencyManager(), installers);
	}
	
	/**
	 * Creates a new MultiGenerator.
	 * @param mgr
	 * @param installers 
	 */
	public DefaultGenerator(DependencyManager mgr, Installer... installers) {
		this.installers = Arrays.asList(installers);
		this.mgr = mgr;
		this.renderStack = new DefaultRenderStack();
	}
	
	/**
	 * @return the dependency manager.
	 */
	@Override
	public DependencyManager getDependencyMgr() {
		return mgr;
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
	public RenderStack getRenderStack() {
		return renderStack;
	}

    /**
     * Renders the specified model into a stream of code models.
     * This is used internally to provide the other interface methods.
     * 
     * @param model The model to generate.
     * @return A stream of code objects.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <A, B> Stream<Meta<A, B>> metaOn(A model, Class<B> to) {
        if (model instanceof Optional) {
            throw new UnsupportedOperationException(
                "Model must not be an Optional!"
            );
        }

        return installers.stream().flatMap(installer ->
            BridgeTransform.create(installer, model.getClass(), to)
            .map(t -> (Transform<A, B>) t)
            .map(t -> transform(t, model, installer, false))
            .filter(o -> o.isPresent())
            .map(o -> o.get())
        );
    }

    @Override
	public <A, B> Optional<Meta<A, B>> transform(Transform<A, B> transform, A model, Installer installer) {
        return transform(transform, model, installer, true);
	}
    
    private <A, B> Optional<Meta<A, B>> transform(Transform<A, B> transform, A model, Installer installer, boolean useStack) {
        
        /*if (useStack) */renderStack.push(model);

        final Optional<Meta<A, B>> meta = transform
            .transform(this, model)
            .map(s -> new Meta.Impl<A, B>()
            .setModel(model)
            .setResult(s)
            .setTransform(transform)
            .setInstaller(installer)
            .setRenderStack(new DefaultRenderStack(renderStack))
        );
        
        /*if (useStack) */renderStack.pop();
        
        return meta;
    }
}
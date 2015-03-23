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
import java.util.stream.Stream;

/**
 *
 * @author Emil Forslund
 */
public interface Generator {

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
     * framework doesn't put any constraints on what can be rendered. The
     * elements should not be cast directly to the model class but rather to an
     * interface describing the properties you need to read. That way, the
     * design remains dynamic even if the exact implementation isn't the same.
     *
     * The returned Stack will be immutable.
     *
     * @return the current rendering stack.
     */
    List<Object> getRenderStack();
    
    /**
     * Renders the specified model into a stream of code models. This is used
     * internally to provide the other interface methods.
     *
     * @param <A>
     * @param <B>
     * @param model The model to generate.
     * @return A stream of code objects.
     */
    <A, B> Stream<Meta<A, B>> metaOn(A model, Class<B> to);

    /**
     * Renders the specified model into a stream of code models. This is used
     * internally to provide the other interface methods.
     *
     * @param <M>
     * @param model The model to generate.
     * @return A stream of code objects.
     */
    default <M> Stream<Meta<M, String>> metaOn(M model) {
        return metaOn(model, String.class);
    }

    /**
     * Renders all the specified models into a stream of code models. This is
     * used internally to provide the other interface methods. ¨
     *
     * @param <A>
     * @param models The models to generate.
     * @return A stream of code objects.
     */
    default <A> Stream<Meta<A, String>> metaOn(Collection<A> models) {
        return models.stream().map(model -> metaOn(model)).flatMap(m -> m);
    }
    
    /**
     * Renders all the specified models into a stream of code models. This is
     * used internally to provide the other interface methods. ¨
     *
     * @param <A>
     * @param models The models to generate.
     * @return A stream of code objects.
     */
    default <A, B> Stream<Meta<A, B>> metaOn(Collection<A> models, Class<B> to) {
        return models.stream().map(model -> metaOn(model, to)).flatMap(m -> m);
    }

    /**
     * Locates the <code>CodeView</code> that corresponds to the specified model
     * and uses it to generate a String. If no view is associated with the model
     * type, an empty optional will be returned.
     *
     * @param model The model.
     * @return The viewed text if any.
     */
    default Optional<String> on(Object model) {
        if (model instanceof Optional) {
            final Optional<?> result = (Optional<?>) model;
            if (result.isPresent()) {
                model = result.get();
            } else {
                return Optional.empty();
            }
        }
        
        return metaOn(model).map(c -> c.getResult()).findAny();
    }

    /**
     * Renders all the specified models into a stream of strings.
     *
     * @param <M>
     * @param models The models to generate.
     * @return A stream of code objects.
     */
    default <M> Stream<String> onEach(Collection<M> models) {
        return metaOn(models).map(c -> c.getResult());
    }
    
    /**
     * Transforms the specified model using the specified transform from the
     * specified installer.
     * 
     * @param <A>
     * @param <B>
     * @param transform
     * @param model
     * @param installer
     * @return 
     */
    <A, B> Optional<Meta<A, B>> transform(Transform<A, B> transform, A model, Installer installer);
}

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
package com.speedment.codegen.lang.interfaces;

import com.speedment.codegen.lang.models.Type;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public interface HasImplements<T extends HasImplements<T>> {
    
    @SuppressWarnings("unchecked")
    default T add(final Type interf) {
        getInterfaces().add(interf.copy());
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    default T addAllImplements(final Collection<? extends Type> interf) {
        interf.forEach(this::add);
        return (T) this;
    }
    
    List<Type> getInterfaces();
}

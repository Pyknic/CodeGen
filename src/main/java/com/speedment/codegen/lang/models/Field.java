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
package com.speedment.codegen.lang.models;

import com.speedment.codegen.lang.interfaces.HasAnnotationUsage;
import com.speedment.codegen.lang.interfaces.Callable;
import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.codegen.lang.interfaces.HasJavadoc;
import com.speedment.codegen.lang.interfaces.HasName;
import com.speedment.codegen.lang.interfaces.HasType;
import com.speedment.codegen.lang.interfaces.HasValue;
import com.speedment.codegen.lang.models.implementation.FieldImpl;
import com.speedment.codegen.lang.models.modifiers.FieldModifier;
import java.util.function.Supplier;

/**
 * A model that represents a field in code. This can be either as a method
 * parameter or as a class member.
 * 
 * @author Emil Forslund
 */
public interface Field extends Copyable<Field>, Callable<Field>, HasName<Field>, 
    HasType<Field>, HasJavadoc<Field>, HasValue<Field>, HasAnnotationUsage<Field>, 
    FieldModifier<Field> {

    /**
     * Factory holder.
     */
    enum Factory { INST;
        private Supplier<Field> supplier = () -> new FieldImpl(null, null);
    }

    /**
     * Creates a new instance implementing this interface by using the class
     * supplied by the default factory. To change implementation, please use
     * the {@link #setSupplier(java.util.function.Supplier) setSupplier} method.
     * 
     * @param name  the name
     * @param type  the type
     * @return      the new instance
     */
    static Field of(String name, Type type) {
        return Factory.INST.supplier.get().setName(name).set(type);
    }
        
    /**
     * Sets the instantiation method used to create new instances of this
     * interface.
     * 
     * @param supplier  the new constructor 
     */
    static void setSupplier(Supplier<Field> supplier) {
        Factory.INST.supplier = supplier;
    }
}
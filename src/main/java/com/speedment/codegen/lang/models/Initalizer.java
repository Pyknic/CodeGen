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

import com.speedment.codegen.lang.interfaces.Callable;
import com.speedment.codegen.lang.interfaces.Codeable;
import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.codegen.lang.models.implementation.InitalizerImpl;
import com.speedment.codegen.lang.models.modifiers.InitalizerModifier;

/**
 *
 * @author Emil Forslund
 */
public interface Initalizer extends Copyable<Initalizer>, Callable<Initalizer>, 
Codeable<Initalizer>, InitalizerModifier<Initalizer> {
    enum Factory { INST;
        private Initalizer prototype = new InitalizerImpl();
    }

    static Initalizer of() {
        return Factory.INST.prototype.copy();
    }
    
    static void setPrototype(Initalizer a) {
        Factory.INST.prototype = a;
    }
}
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
package com.speedment.codegen.java.views.interfaces;

import static com.speedment.codegen.Formatting.COMMA_SPACE;
import static com.speedment.codegen.Formatting.SPACE;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.interfaces.Interfaceable;
import static com.speedment.util.CodeCombiner.joinIfNotEmpty;

/**
 *
 * @author Emil Forslund
 * @param <M>
 */
public interface InterfaceableView<M extends Interfaceable<M>> extends CodeView<M> {
    
    String extendsOrImplementsInterfaces();
    
    default String renderInterfaces(CodeGenerator cg, M model) {
        return cg.onEach(model.getInterfaces()).collect(
            joinIfNotEmpty(
                COMMA_SPACE, 
                extendsOrImplementsInterfaces(), 
                SPACE
            )
        );
    }
}

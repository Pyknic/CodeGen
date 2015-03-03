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
package com.speedment.codegen.lang.models.implementation;

import com.speedment.codegen.lang.models.Initalizer;
import com.speedment.codegen.lang.models.modifiers.Modifier;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class InitalizerImpl implements Initalizer {

    private final List<String> code;
    private final Set<Modifier> modifiers;
    
    public InitalizerImpl() {
        code      = new ArrayList<>();
        modifiers = EnumSet.noneOf(Modifier.class);
    }
    
    protected InitalizerImpl(Initalizer prototype) {
        code      = Copier.copy(prototype.getCode(), c -> c);
        modifiers = Copier.copy(prototype.getModifiers(), c -> c);
    }

    @Override
    public List<String> getCode() {
        return code;
    }

    @Override
    public Set<Modifier> getModifiers() {
        return modifiers;
    }
    
    @Override
    public InitalizerImpl copy() {
        return new InitalizerImpl(this);
    }
}
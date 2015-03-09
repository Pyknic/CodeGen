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

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Emil Forslund
 */
public interface Wrappable {
    default <In, C extends Collection<In>> Collection<Object> 
		wrap(C models, Function<In, Object> wrapper) {
		return models.stream().map(wrapper).collect(Collectors.toList());
	}
}

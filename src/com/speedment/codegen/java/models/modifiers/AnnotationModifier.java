/*
 * Copyright 2015 Duncan.
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
package com.speedment.codegen.java.models.modifiers;

import com.speedment.codegen.java.interfaces.Modifiable;
import static com.speedment.codegen.java.models.modifiers.Modifier_.PUBLIC;

/**
 *
 * @author Duncan
 * @param <T>
 */
public interface AnnotationModifier<T extends AnnotationModifier<T>> extends Modifiable<T> {
	default T public_() {
		getModifiers().add(PUBLIC);
		return (T) this;
	}
}
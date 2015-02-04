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
package com.speedment.codegen.base;

import com.speedment.codegen.java.interfaces.Copyable;
import java.util.function.Consumer;

/**
 * 
 * @author Emil Forslund
 * @param <T>
 */
public interface CodeModel<T extends CodeModel<T>> extends Copyable<T> {
	default T call(Consumer<T> control) {
		control.accept((T) this);
		return (T) this;
	}
}
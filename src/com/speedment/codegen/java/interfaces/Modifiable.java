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
package com.speedment.codegen.java.interfaces;

import com.speedment.codegen.java.models.modifiers.Modifier;
import java.util.EnumSet;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public interface Modifiable<T extends Modifiable<T>> {
	public EnumSet<Modifier> getModifiers();
	
//	T public_();
//	T protected_();
//	T private_();
//	T abstract_();
//	T static_();
//	T final_();
//	T strictfp_();
//	T transient_();
//	T volatile_();
//	T synchronized_();
//	T native_();
//	T default_();
}

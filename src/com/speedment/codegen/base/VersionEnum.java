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

/**
 *
 * @author Emil Forslund
 */
public interface VersionEnum {
	Class<? extends CodeView> getViewClass();
	Class<? extends CodeModel> getModelClass();
	
	public static <E extends Enum<E> & VersionEnum> E of(Class<? extends CodeModel> modelClass, Class<E> versionClass) {
		for (final E value : versionClass.getEnumConstants()) {
			if (value.getModelClass().isAssignableFrom(modelClass)) {
				return value;
			}
		}
		
		throw new UnsupportedOperationException(
			"Attemting to view a model that is not associated in the current VersionEnum.\n" +
			"Please make sure there exists a view for each model and that they have been\n" +
			"properly added to the class implementing VersionEnum."
		);
	}
}

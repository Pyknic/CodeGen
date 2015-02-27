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
package com.speedment.codegen.base;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * @author Emil Forslund
 */
public class DefaultInstaller implements Installer {
	private final Set<Map.Entry<Class<?>, Class<? extends CodeView<?>>>> modelToView;
	
	public DefaultInstaller() {
		modelToView = new HashSet<>();
	}

	@Override
	public <M, V extends CodeView<M>> void install(Class<M> model, Class<V> view) {
		modelToView.add(new SimpleImmutableEntry<>(model, view));
	}

	@Override
	public Optional<CodeView<?>> withOne(Class<?> model) {
		for (final Map.Entry<Class<?>, Class<? extends CodeView<?>>> e : modelToView) {
			if (e.getKey().isAssignableFrom(model)) {
				return Optional.of(Installer.create(e.getValue()));
			}
		}
		return Optional.empty();
	}
	
	@Override
    @SuppressWarnings("unchecked")
	public <M> Stream<CodeView<M>> withAll(Class<M> model) {
		return modelToView.stream()
			.filter(e -> e.getKey().isAssignableFrom(model))
			.map(e -> (CodeView<M>) Installer.create(e.getValue()));
	}
}
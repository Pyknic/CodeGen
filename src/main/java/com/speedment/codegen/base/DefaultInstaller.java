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

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author Emil Forslund
 */
public class DefaultInstaller implements Installer {

    private final Map<Class<?>, Set<Map.Entry<Class<?>, Class<? extends Transform<?, ?>>>>> transforms;
	private final String name;
    
	public DefaultInstaller(String name) {
        this.name = name;
        this.transforms = new ConcurrentHashMap<>();
	}
    
    @Override
    public String getName() {
        return name;
    }

	@Override
	public <A, B, T extends Transform<A, B>> Installer install(Class<A> from, Class<B> to, Class<T> transform) {
        transforms.computeIfAbsent(from, f -> new HashSet<>()).add(new AbstractMap.SimpleEntry<>(to, transform));
        return this;
	}

	@Override
    @SuppressWarnings("unchecked")
	public <A, T extends Transform<A, ?>> Map<Class<?>, T> allFrom(Class<A> model) {
		return new HashSet<>(transforms.entrySet()).stream()
			.filter(e -> e.getKey().isAssignableFrom(model))
            .flatMap(e -> e.getValue().stream())
            .collect(Collectors.toMap(e -> e.getKey(), e -> (T) Installer.create(e.getValue())));
	}
}
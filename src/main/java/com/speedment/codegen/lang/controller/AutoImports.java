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
package com.speedment.codegen.lang.controller;

import com.speedment.codegen.base.DependencyManager;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Import;
import com.speedment.codegen.lang.models.Type;
import java.util.Collection;
import java.util.function.Consumer;

/**
 *
 * @author Duncan
 */
public class AutoImports implements Consumer<Class> {
	private final DependencyManager mgr;
	
	public AutoImports(DependencyManager mgr) {
		this.mgr = mgr;
	}
 
    @Override
    public void accept(Class model) {
		model.getSuperType().ifPresent(st -> add(model, st));
		addAll(model, model.getInterfaces());
		model.getFields().forEach(f -> add(model, f.getType()));
		model.getMethods().forEach(m -> {
			add(model, m.getType());
			m.getParams().forEach(p -> add(model, p.getType()));
		});
    }
	
	public void add(Class model, Type type) {
		if (!model.getName().equals(type.getName())) {
			if (!mgr.isIgnored(type.getName())) {
				if (!model.getDependencies().stream()
				.anyMatch(imp -> imp.getType().getName().equals(type.getName()))) {
					model.add(new Import(new Type(type.getName())));
				}
			}
		}
	}
	
	public void addAll(Class model, Collection<Type> types) {
		types.forEach(t -> add(model, t));
	}
}

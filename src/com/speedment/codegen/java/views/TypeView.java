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
package com.speedment.codegen.java.views;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.Type_;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.DependencyManager;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class TypeView implements CodeView<Type_> {
	@Override
	public Optional<CharSequence> render(CodeGenerator cg, Type_ model) {
		final String name = model.getName().toString();
		final DependencyManager mgr = cg.getDependencyMgr();

		if (!mgr.isIgnored(name) && (
			!mgr.isAlreadyDependentOf(name) || 
			mgr.isNameTaken(name))) {
			
			mgr.declareDependency(name);
			return Optional.of(name);
		} else {
			return Optional.of(shortName(name));
		}
	}
}
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
import com.speedment.codegen.java.models.Type;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.DependencyManager;
import java.util.Optional;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Emil Forslund
 */
public class TypeView implements CodeView<Type> {
	private Optional<String> renderName(CodeGenerator cg, Type model, String name) {
		return Optional.of(
			name +
			cg.onEach(model.getGenerics()).collect(
				CodeCombiner.joinIfNotEmpty(
					COMMA, 
					SS, 
					SE
				)
			)
		);
	}
	
	@Override
	public Optional<String> render(CodeGenerator cg, Type model) {
		final DependencyManager mgr = cg.getDependencyMgr();

		if (mgr.isLoaded(model.getName())) {
			return renderName(cg, model, shortName(model.getName()));
		} else {
			return renderName(cg, model, model.getName());
		}
	}
}
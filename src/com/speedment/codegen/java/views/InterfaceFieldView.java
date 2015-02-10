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
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.java.models.InterfaceField;
import static com.speedment.codegen.java.models.modifiers.Modifier.FINAL;
import static com.speedment.codegen.java.models.modifiers.Modifier.STATIC;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class InterfaceFieldView implements CodeView<InterfaceField> {

	@Override
	public Optional<String> render(CodeGenerator cg, InterfaceField model) {
		if (model.getModifiers().contains(STATIC)) {
			return Optional.of(
				cg.on(model.getJavadoc()).orElse(EMPTY) +	
				(model.getModifiers().contains(FINAL) ?
					cg.on(FINAL).get() + SPACE : EMPTY
				) +		
				cg.on(model.getType()).orElse(EMPTY) + SPACE +
				model.getName()
			);
		} else {
			return Optional.empty();
		}
	}
	
}
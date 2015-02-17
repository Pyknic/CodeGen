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
package com.speedment.codegen.java.views;

import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.Annotation;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationView implements CodeView<Annotation> {
	private final static String 
		INTERFACE_STRING = "@interface ",
		DEFAULT_STRING = " default ";
	
	@Override
	public Optional<String> render(CodeGenerator cg, Annotation model) {
		return Optional.of(
			// Javadoc (optional)
			ifelse(cg.on(model.getJavadoc()), c -> c + nl(), EMPTY) +
				
			// Modifiers (public)
			cg.onEach(model.getModifiers()).collect(
				CodeCombiner.joinIfNotEmpty(SPACE)
			) +
				
			// Declaration
			INTERFACE_STRING + model.getName() +
				
			// Block of code
			block(
				model.getFields().stream().map(f ->
					// Field javadoc (optional)
					ifelse(cg.on(f.getJavadoc()), jd -> nl() + jd + nl(), EMPTY) +
					
					// Field declaration
					cg.on(f.getType()) + SPACE + f.getName() + PS + PE +
						
					// Default value (optional)
					ifelse(cg.on(f.getValue()), v -> (DEFAULT_STRING + v), EMPTY) +
							
					SC
				).collect(CodeCombiner.joinIfNotEmpty(nl()))
			)
		);
	}
}

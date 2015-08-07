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

import com.speedment.codegen.lang.models.Annotation;
import java.util.Optional;
import static com.speedment.codegen.util.Formatting.*;
import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.java.views.interfaces.HasAnnotationUsageView;
import com.speedment.codegen.java.views.interfaces.HasJavadocView;
import com.speedment.codegen.java.views.interfaces.HasNameView;
import com.speedment.codegen.util.CodeCombiner;

/**
 * Transforms from an {@link Annotation} to java code.
 * 
 * @author Emil Forslund
 */
public class AnnotationView implements Transform<Annotation, String>, 
    HasJavadocView<Annotation>, HasAnnotationUsageView<Annotation>, 
    HasNameView<Annotation> {
	
    private final static String 
		INTERFACE_STRING = "@interface ",
		DEFAULT_STRING = " default ";
	
    /**
     * {@inheritDoc}
     */
	@Override
	public Optional<String> transform(Generator gen, Annotation model) {
		return Optional.of(
			renderAnnotations(gen, model) +
			renderAnnotations(gen, model) +
			INTERFACE_STRING + 
            renderName(gen, model) +
			block(
				model.getFields().stream().map(f -> 
					// Field javadoc (optional)
					ifelse(gen.on(f.getJavadoc()), jd -> nl() + jd + nl(), EMPTY) +
					
					// Field declaration
					gen.on(f.getType()) + SPACE + f.getName() + PS + PE +
						
					// Default value (optional)
					ifelse(gen.on(f.getValue()), v -> (DEFAULT_STRING + v), EMPTY) +
							
					SC
				).collect(CodeCombiner.joinIfNotEmpty(nl()))
			)
		);
	}
}

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
import com.speedment.codegen.lang.models.AnnotationUsage;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationUsageView implements CodeView<AnnotationUsage> {
	private final static String 
		PSTART = " (", 
		EQUALS = " = ";

	@Override
	public Optional<String> render(CodeGenerator cg, AnnotationUsage model) {
		return Optional.of(
			AT + cg.on(model.getType()).get() +
			model.getValues().stream()
				.map(e -> e.getKey() + cg.on(e.getValue()).map(s -> EQUALS + s).orElse(EMPTY))
				.collect(
					CodeCombiner.joinIfNotEmpty(
						cnl(),
						PSTART,
						PE
					)
				) +
			ifelse(
				cg.on(model.getValue()), 
				c -> c,
				EMPTY
			)
		);
	}
	
}

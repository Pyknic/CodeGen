/*
 * Copyright 2015 Duncan.
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

import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.Generic;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Duncan
 */
public class GenericView implements CodeView<Generic> {
	private final static String 
			EXTENDS_STRING = " extends ", 
			SUPER_STRING = " super ";

	@Override
	public Optional<String> render(CodeGenerator cg, Generic model) {
		if (!model.getLowerBound().isPresent() 
		&&   model.getUpperBounds().isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(
				model.getLowerBound().orElse(EMPTY) +
				cg.onEach(model.getUpperBounds()).collect(CodeCombiner.joinIfNotEmpty(AND, 
						model.getLowerBound().isPresent() ? 
							model.getBoundType() == Generic.BoundType.UPPER ?
							EXTENDS_STRING : SUPER_STRING
						: EMPTY, 
						EMPTY
					)
				)
			);
		}
	}
}
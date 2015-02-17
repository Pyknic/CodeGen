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
package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.values.ArrayValue;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;

/**
 *
 * @author Emil Forslund
 */
public class ArrayValueView implements CodeView<ArrayValue> {
	@Override
	public Optional<String> render(CodeGenerator cg, ArrayValue model) {
		return Optional.of(
			cg.onEach(model.getValue()).collect(
				Collectors.joining(
					COMMA_SPACE, 
					BS, 
					BE
				)
			)
		);
	}
}

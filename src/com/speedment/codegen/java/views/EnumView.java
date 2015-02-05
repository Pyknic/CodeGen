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

import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.java.models.Enum;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Duncan
 */
public class EnumView extends ClassOrInterfaceView<Enum> {
	@Override
	protected CharSequence classOrInterfaceLabel() {
		return ENUM_STRING;
	}

	@Override
	protected CharSequence extendsOrImplementsLabel() {
		return IMPLEMENTS_STRING;
	}

	@Override
	protected CharSequence onSuperType(CodeGenerator cg, Enum model) {
		return EMPTY;
	}

	@Override
	protected CharSequence onBeforeFields(CodeGenerator cg, Enum model) {
		return model.getConstants().stream()
			.map(c -> (CharSequence) cg.on(c).get()).collect(
				CodeCombiner.joinIfNotEmpty(
					(!model.getConstants().isEmpty()
					&& !model.getConstants().get(0).getValues().isEmpty())
					? cnl() : COMMA_SPACE, 
					EMPTY, 
					SC
				)
			);
	}
}

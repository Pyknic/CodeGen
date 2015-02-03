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

import com.speedment.util.CodeCombiner;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.Javadoc_;
import java.util.Optional;
import com.speedment.util.$;
import static com.speedment.codegen.Formatting.*;

/**
 *
 * @author Emil Forslund
 */
public class JavadocView implements CodeView<Javadoc_> {
	private final static CharSequence
		JAVADOC_DELIMITER = new $(nl(), SPACE, STAR, SPACE).toString(),
		JAVADOC_PREFIX = new $(SLASH, STAR, STAR, nl(), SPACE, STAR, SPACE).toString(),
		JAVADOC_SUFFIX = new $(nl(), SPACE, STAR, SLASH, nl()).toString();
	
	@Override
	public Optional<CharSequence> render(CodeGenerator cg, Javadoc_ model) {
		return CodeCombiner.ifEmpty(
			model.getRows().stream().collect(
				CodeCombiner.joinIfNotEmpty(
					JAVADOC_DELIMITER, 
					JAVADOC_PREFIX, 
					JAVADOC_SUFFIX
				)
			)
		);
	}
	
}

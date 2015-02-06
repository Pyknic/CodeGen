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
import com.speedment.codegen.java.models.Javadoc;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.VersionEnum;

/**
 *
 * @author Emil Forslund
 */
public class JavadocView implements CodeView<Javadoc> {
	private final static String
		JAVADOC_DELIMITER = nl() + SPACE + STAR + SPACE,
		JAVADOC_PREFIX = SLASH + STAR + STAR + nl() + SPACE + STAR + SPACE,
		JAVADOC_SUFFIX = nl() + SPACE + STAR + SLASH + nl();
	
	@Override
	public <V extends Enum<V> & VersionEnum> Optional<String> render(CodeGenerator<V> cg, Javadoc model) {
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

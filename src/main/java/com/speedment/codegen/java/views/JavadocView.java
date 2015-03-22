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

import com.speedment.util.CodeCombiner;
import com.speedment.codegen.base.View;
import com.speedment.codegen.lang.models.Javadoc;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.Generator;
import java.util.stream.Stream;

/**
 *
 * @author Emil Forslund
 */
public class JavadocView implements View<Javadoc> {
	private final static String
		JAVADOC_DELIMITER = nl() + SPACE + STAR + SPACE,
		JAVADOC_PREFIX = SLASH + STAR + STAR + nl() + SPACE + STAR + SPACE,
		JAVADOC_SUFFIX = nl() + SPACE + STAR + SLASH;
	
	@Override
	public Optional<String> render(Generator cg, Javadoc model) {
		return CodeCombiner.ifEmpty(
            Stream.of(
                model.getRows().stream(),
                renderParams(cg, model)
			).flatMap(s -> s).collect(
				CodeCombiner.joinIfNotEmpty(
					JAVADOC_DELIMITER, 
					JAVADOC_PREFIX, 
					JAVADOC_SUFFIX
				)
			)
		);
	}

    private static Stream<String> renderParams(Generator cg, Javadoc model) {
        final Stream<String> stream = cg.onEach(model.getTags());
        
        if (model.getTags().isEmpty()) {
            return stream;
        } else {
            return Stream.of(Stream.of(EMPTY), stream).flatMap(s -> s);
        }
    }
}
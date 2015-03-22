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

import com.speedment.codegen.base.View;
import com.speedment.codegen.lang.models.AnnotationUsage;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.Generator;
import com.speedment.util.CodeCombiner;
import java.util.stream.Stream;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationUsageView implements View<AnnotationUsage> {
	private final static String 
		PSTART = "(", 
		EQUALS = " = ";

	@Override
	public Optional<String> render(Generator cg, AnnotationUsage model) {
        final Optional<String> value = cg.on(model.getValue());
        final Stream<String> valueStream = value.isPresent() ? Stream.of(value.get()) : Stream.empty();
        
		return Optional.of(
			AT + cg.on(model.getType()).get() +
            Stream.of(
                model.getValues().stream().map(e -> e.getKey() + cg.on(e.getValue()).map(s -> EQUALS + s).orElse(EMPTY)),
                valueStream
            ).flatMap(s -> s).collect(
                CodeCombiner.joinIfNotEmpty(
						cnl(),
						PSTART,
						PE
				)
            )
		);
	}
}

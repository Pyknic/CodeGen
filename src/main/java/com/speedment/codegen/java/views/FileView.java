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

import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.File;
import com.speedment.util.CodeCombiner;
import java.util.Optional;

/**
 *
 * @author Duncan
 */
public class FileView implements CodeView<File> {
	private final static String PACKAGE_STRING = "package ";
	
	private String renderPackage(File file) {
		final Optional<String> pack = packageName(file.getName());
		if (pack.isPresent()) {
			return PACKAGE_STRING + pack.get() + scdnl();
		} else {
			return EMPTY;
		}
	}
	
	@Override
	public Optional<String> render(CodeGenerator cg, File model) {
		return Optional.of(
			ifelse(cg.on(model.getJavadoc()), s -> s + nl(), EMPTY) +
			renderPackage(model) +
			cg.onEach(model.getDependencies()).collect(CodeCombiner.joinIfNotEmpty(nl(), EMPTY, dnl())) +
			cg.onEach(model.getClasses()).collect(CodeCombiner.joinIfNotEmpty(dnl()))
		);
	}
}
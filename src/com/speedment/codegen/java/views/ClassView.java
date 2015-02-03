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

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.Class_;
import com.speedment.util.$;
import com.speedment.util.CodeCombiner;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.DependencyManager;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class ClassView implements CodeView<Class_> {
	private final static CharSequence
		CLASS_STRING = "class ",
		EXTENDS_STRING = "extends ",
		PACKAGE_STRING = "package ";
	
	private Optional<CharSequence> renderPackage(Class_ model) {
		Optional<CharSequence> pack = packageName(model.getName());
		if (pack.isPresent()) {
			return Optional.of(new $(PACKAGE_STRING, pack, scdnl()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<CharSequence> render(CodeGenerator cg, Class_ model) {
		Optional<CharSequence> packageName = packageName(model.getName());
		final DependencyManager mgr = cg.getDependencyMgr();
		
		if (packageName.isPresent()) {
			if (mgr.isIgnored(packageName.get())) {
				packageName = Optional.empty();
			} else {
				mgr.ignorePackage(new $(packageName.get(), DOT));
			}
		}
		
		final Optional<CharSequence> view = Optional.of(new $(
			renderPackage(model),
			cg.onEach(model.getDependencies()).collect(CodeCombiner.joinIfNotEmpty(nl(), EMPTY, dnl())),
			cg.on(model.getJavadoc()),
			cg.onEach(model.getModifiers()).collect(CodeCombiner.joinIfNotEmpty(SPACE, EMPTY, SPACE)),
			CLASS_STRING, shortName(model.getName()), SPACE,
			model.getSuperType().isPresent() ?
				new $(EXTENDS_STRING, new $(cg.on(model.getSuperType().get()), SPACE)) : EMPTY,
			looseBracketsIndent(new $(
				cg.onEach(model.getFields()).collect(CodeCombiner.joinIfNotEmpty(scnl(), EMPTY, scdnl())),
				cg.onEach(model.getMethods()).collect(CodeCombiner.joinIfNotEmpty(dnl()))
			))
		));
		
		if (packageName.isPresent()) {
			mgr.acceptPackage(packageName.get());
		}
		
		return view;
	}
	
}
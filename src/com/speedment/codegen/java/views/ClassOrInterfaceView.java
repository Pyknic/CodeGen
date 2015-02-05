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

import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.base.DependencyManager;
import com.speedment.codegen.base.VersionEnum;
import com.speedment.codegen.java.models.ClassOrInterface;
import java.util.Optional;
import com.speedment.util.$;
import com.speedment.util.CodeCombiner;


/**
 *
 * @author Emil Forslund
 * @param <M>
 */
public abstract class ClassOrInterfaceView<M extends ClassOrInterface> implements CodeView<M> {
	protected final static CharSequence
		CLASS_STRING = "class ",
		INTERFACE_STRING = "interface ",
		ENUM_STRING = "enum ",
		IMPLEMENTS_STRING = "implements ",
		EXTENDS_STRING = "extends ",
		PACKAGE_STRING = "package ";
	
	private CharSequence renderPackage(M model) {
		Optional<CharSequence> pack = packageName(model.getName());
		if (pack.isPresent()) {
			return new $(PACKAGE_STRING, pack.get(), scdnl());
		} else {
			return EMPTY;
		}
	}
	
	protected CharSequence onBeforeFields(CodeGenerator cg, M model) {return EMPTY;}
	
	protected abstract CharSequence classOrInterfaceLabel();
	protected abstract CharSequence extendsOrImplementsLabel();
	protected abstract CharSequence onSuperType(CodeGenerator cg, M model);

	@Override
	public <V extends Enum<V> & VersionEnum> Optional<CharSequence> render(CodeGenerator<V> cg, M model) {
		Optional<CharSequence> packageName = packageName(model.getName());
		final DependencyManager mgr = cg.getDependencyMgr();
		mgr.clearDependencies();
		
		if (packageName.isPresent()) {
			final String name = packageName.get().toString();
			if (mgr.isIgnored(name)) {
				packageName = Optional.empty();
			} else {
				mgr.ignorePackage(name);
			}
		}
		
		final Optional<CharSequence> view = Optional.of(new $(
			renderPackage(model),
			cg.onEach(model.getDependencies()).collect(CodeCombiner.joinIfNotEmpty(nl(), EMPTY, dnl())),
			cg.on(model.getJavadoc()),
			cg.onEach(model.getModifiers()).collect(CodeCombiner.joinIfNotEmpty(SPACE, EMPTY, SPACE)),
			classOrInterfaceLabel(), shortName(model.getName()), SPACE,
			onSuperType(cg, model),
			cg.onEach(model.getInterfaces()).collect(CodeCombiner.joinIfNotEmpty(SPACE, extendsOrImplementsLabel(), SPACE)),
			looseBracketsIndent(new $(
				onBeforeFields(cg, model),
				cg.onEach(model.getFields()).collect(CodeCombiner.joinIfNotEmpty(scnl(), EMPTY, scdnl())),
				cg.onEach(model.getMethods()).collect(CodeCombiner.joinIfNotEmpty(dnl()))
			))
		));
		
		if (packageName.isPresent()) {
			mgr.acceptPackage(packageName.get().toString());
		}
		
		return view;
	}
	
}
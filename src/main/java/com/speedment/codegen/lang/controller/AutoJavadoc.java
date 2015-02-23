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
package com.speedment.codegen.lang.controller;

import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.lang.interfaces.Constructable;
import com.speedment.codegen.lang.interfaces.Documentable;
import com.speedment.codegen.lang.interfaces.Fieldable;
import com.speedment.codegen.lang.interfaces.Generable;
import com.speedment.codegen.lang.interfaces.Methodable;
import com.speedment.codegen.lang.models.ClassOrInterface;
import com.speedment.codegen.lang.models.Javadoc;
import com.speedment.codegen.lang.models.JavadocTag;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.constants.Default;
import java.util.function.Consumer;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public class AutoJavadoc<T extends Documentable> implements Consumer<T> {
	private final static String 
			DEFAULT_TEXT = "Write some documentation here.",
			DEFAULT_NAME = "Your Name";
 
    @Override
    public void accept(Documentable model) {
		createJavadoc(model);
    }
	
	private static <T extends Documentable<?>> T createJavadoc(T model) {
		final Javadoc doc = model.getJavadoc().orElse(new Javadoc(DEFAULT_TEXT));
		model.setJavadoc(doc);
		
		// Add @param for each type variable.
		if (model instanceof Generable) {
			((Generable<?>) model).getGenerics().forEach(g -> 
				g.getLowerBound().ifPresent(t -> addTag(doc, 
					Default.PARAM.setValue(SS + t + SE)
				))
			);
		}

		if (model instanceof ClassOrInterface) {
			// Add @author
			doc.add(Default.AUTHOR.setValue(DEFAULT_NAME));
		} else {
			// Add @param for each parameter.
			if (model instanceof Fieldable) {
				((Fieldable<?>) model).getFields().forEach(f -> 
					addTag(doc, Default.PARAM.setValue(f.getName()))
				);
			}
		}
		
		// Add @return to methods.
		if (model instanceof Method) {
			addTag(doc, Default.RETURN);
		}
		
		// Generate javadoc for each constructor.
		if (model instanceof Constructable) {
			((Constructable<?>) model).getConstructors()
				.forEach(m -> createJavadoc(m));
		}

		// Generate javadoc for each method.
		if (model instanceof Methodable) {
			((Methodable<?>) model).getMethods()
				.forEach(m -> createJavadoc(m));
		}
		
		return model;
	}
	
	private static void addTag(Javadoc doc, JavadocTag tag) {
		if (!hasTagAlready(doc, tag)) {
			doc.add(tag);
		}
	}
	
	private static boolean hasTagAlready(Javadoc doc, JavadocTag tag) {
		return doc.getTags().stream().anyMatch(t -> 
			tag.getName().equals(t.getName())
			&& tag.getValue().equals(t.getValue())
		);
	}
}

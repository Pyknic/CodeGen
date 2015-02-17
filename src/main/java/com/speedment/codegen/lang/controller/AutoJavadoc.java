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
import com.speedment.codegen.lang.interfaces.Documentable;
import com.speedment.codegen.lang.interfaces.Generable;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Javadoc;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.constants.Default;
import java.util.function.Consumer;

/**
 *
 * @author Duncan
 */
public class AutoJavadoc implements Consumer<Class> {
 
    @Override
    public void accept(Class model) {
		generateJavadocFor(model);
		model.getMethods().forEach(m -> 
			paramsForMethod(
				generateJavadocFor(m)
			)
		);
    }
    
    private static <T extends Documentable & Generable> T generateJavadocFor(T m) {
		if (!m.getJavadoc().isPresent()) {
			final Javadoc doc = new Javadoc("Write some documentation here.");
			if (m instanceof Class) {
				doc.add(Default.AUTHOR.setValue("Your Name"));
			}
			paramsForGenerable(doc, m);
			m.setJavadoc(doc);
		}

		return m;
    }
	
	private static Generable<?> paramsForGenerable(Javadoc doc, Generable<?> gen) {
		gen.getGenerics().forEach(g -> 
			g.getLowerBound().ifPresent(t -> doc.add(
				Default.PARAM.setValue(SS + t + SE)
			))
		);
		return gen;
	}
	
	private static Method paramsForMethod(Method m) {
		m.getJavadoc().ifPresent(doc -> {
			m.getParams().forEach(p -> {
				if (!(doc.getTags().stream().anyMatch(tag ->
					tag.getName().equals("param") &&
					ifelse(tag.getValue(), s -> p.getName().equals(s), false)
				))) {
					doc.add(Default.PARAM.setValue(p.getName()));
				}
			});

			if (!Default.isVoid(m.getType())) {
				doc.add(Default.RETURN);
			}
		});
		return m;
	}
}

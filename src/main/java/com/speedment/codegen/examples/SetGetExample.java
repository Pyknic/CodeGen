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
package com.speedment.codegen.examples;

import com.speedment.codegen.Formatting;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.java.JavaGenerator;
import com.speedment.codegen.java.JavaInstaller;
import com.speedment.codegen.lang.controller.AutoImports;
import com.speedment.codegen.lang.controller.SetGetAdd;
import com.speedment.codegen.lang.models.File;
import com.speedment.codegen.lang.models.constants.Default;
import com.speedment.codegen.lang.models.implementation.ClassImpl;
import com.speedment.codegen.lang.models.implementation.FieldImpl;
import com.speedment.codegen.lang.models.implementation.FileImpl;
import com.speedment.codegen.lang.models.implementation.TypeImpl;

/**
 *
 * @author Emil Forslund
 */
public class SetGetExample {
	public static void main(String... params) {
		final CodeGenerator cg = new JavaGenerator(new JavaInstaller());
		Formatting.tab("    ");
		
		final File f = new FileImpl("org/example/codegen/Game.java")
			.add(new ClassImpl("Game").public_()
				.add(new FieldImpl("width", Default.INT_PRIMITIVE))
				.add(new FieldImpl("height", Default.INT_PRIMITIVE))
				.add(new FieldImpl("entities", Default.list(
					new TypeImpl("org.example.codegen.Entity")
				)))
				.call(new SetGetAdd())
			).call(new AutoImports(cg.getDependencyMgr()))
		;
		
		System.out.println(cg.on(f).get());
	}
}
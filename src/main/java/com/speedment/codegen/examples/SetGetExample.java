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
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.File;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.constants.Default;

/**
 *
 * @author Duncan
 */
public class SetGetExample {
	public static void main(String... params) {
		final CodeGenerator cg = new JavaGenerator(new JavaInstaller());
		Formatting.tab("    ");
		
		final File f = new File("org/example/codegen/Game.java")
			.add(new Class("Game").public_()
				.add(new Field("width", Default.INT_PRIMITIVE))
				.add(new Field("height", Default.INT_PRIMITIVE))
				.add(new Field("entities", Default.list(
					new Type("org.example.codegen.Entity")
				)))
				.call(new SetGetAdd())
			).call(new AutoImports(cg.getDependencyMgr()))
		;
		
		System.out.println(cg.on(f).get());
	}
}
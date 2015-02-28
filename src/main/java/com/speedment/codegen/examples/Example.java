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
import static com.speedment.codegen.Formatting.block;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.java.JavaGenerator;
import com.speedment.codegen.lang.controller.AutoEquals;
import com.speedment.codegen.lang.controller.AutoImports;
import com.speedment.codegen.lang.controller.AutoJavadoc;
import com.speedment.codegen.lang.controller.FinalParameters;
import com.speedment.codegen.lang.controller.SetGetAdd;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.File;
import com.speedment.codegen.lang.models.Javadoc;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Constructor;
import com.speedment.codegen.lang.models.Generic;
import com.speedment.codegen.lang.models.Method;
import static com.speedment.codegen.lang.models.constants.Default.INT_PRIMITIVE;
import static com.speedment.codegen.lang.models.constants.Default.STRING;
import static com.speedment.codegen.lang.models.constants.Default.VOID;
import static com.speedment.codegen.lang.models.constants.Default.list;

/**
 *
 * @author Emil Forslund
 */
public class Example {
	private Example() {}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		final CodeGenerator cg = new JavaGenerator();
		
		Formatting.tab("    ");

		final Type typeThread  = Type.of(Thread.class);
		final Type spriteStore = Type.of("org.duncan.test.SpriteStore");
		final Type soundStore  = Type.of("org.duncan.test.SoundStore");

		System.out.println(cg.on(File.of("org/duncan/test/MittTest.java")
			.setJavadoc(Javadoc.of("Copyright (c) Example Company, 2015."))
			.add(Class.of("MittTest").setSupertype(typeThread)
				/***** Class declaration *****/
				.public_()
				.setJavadoc(Javadoc.of(
					"This is a test class to demonstrate how the ",
					"code generator is working."
				))
				
				/***** Fields *****/
				.add(Field.of("player1Name", STRING))
				.add(Field.of("player2Name", STRING))
				.add(Field.of("player1Score", INT_PRIMITIVE))
				.add(Field.of("player2Score", INT_PRIMITIVE))
				.add(Field.of("players", list(Type.of("org.duncan.test.Player"))))
				.add(Field.of("spriteStore", spriteStore))
				.add(Field.of("soundStore", soundStore))
					
				/***** Subclass *****/
				.add(Class.of("Impl").private_()
					.add(Field.of("gameId", INT_PRIMITIVE).private_().final_())
					.add(Constructor.of().protected_()
						.add(Field.of("gameId", INT_PRIMITIVE))
						.add("this.gameId = gameId;")
					)
					.call(new SetGetAdd())
					.call(new FinalParameters())
					.call(new AutoJavadoc<>())
				)
					
				/***** Methods *****/
				.add(Method.of("spawnPlayer1", VOID).public_()
					.setJavadoc(Javadoc.of(
						"This function is used to reset Player 1."
					))
					.add(Field.of("name", STRING))
					.add(Field.of("score", INT_PRIMITIVE))
					.add("this.player1Name = name;")
					.add("this.player1Score = score;")
				)
				.add(Method.of("spawnPlayer2", VOID).public_()
					.setJavadoc(Javadoc.of(
						"This function is used to reset Player 2."
					))
					.add(Field.of("name", STRING))
					.add(Field.of("score", INT_PRIMITIVE))
					.add("this.player2Name = name;")
					.add("this.player2Score = score;")
				)
				.add(Method.of("killPlayer", VOID).public_()
					.setJavadoc(Javadoc.of(
						"This method can be used to kill either player."
					))
					.add(Field.of("name", STRING))
					.add("switch (name) " + block(
						"case (this.player1Name) : killPlayer1(); break;",
						"case (this.player2Name) : killPlayer2(); break;"
					))
				)
				.add(Method.of("killPlayer1", VOID).protected_()
					.add("this.player2Score += 20;")
				)
				.add(Method.of("killPlayer2", VOID).protected_()
					.add("this.player1Score += 20;")
				)
				.add(Method.of("restart", VOID).public_()
					.add("spawnPlayer1();")
					.add("spawnPlayer2();")
				)
				
				/***** Controllers *****/
				.call(new SetGetAdd())
				.call(new AutoEquals<>())
				.call(new FinalParameters())
				.call(new AutoJavadoc<>())
			
			).call(new AutoImports(cg.getDependencyMgr()))
		).get() + "\n");
	}
}
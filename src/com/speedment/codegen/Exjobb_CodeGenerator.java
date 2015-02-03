package com.speedment.codegen;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.java.JavaGenerator;
import com.speedment.codegen.java.controller.SetGet;
import com.speedment.codegen.java.models.Class_;
import com.speedment.codegen.java.models.Field_;
import com.speedment.codegen.java.models.Method_;
import com.speedment.codegen.java.models.Type_;
import static com.speedment.codegen.java.models.Type_.*;

import com.speedment.codegen.java.controller.FinalParameters;
import com.speedment.codegen.java.models.Javadoc_;
import com.speedment.util.$;
import com.speedment.codegen.java.models.Dependency_;

/**
 *
 * @author Emil Forslund
 */
public class Exjobb_CodeGenerator {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		final CodeGenerator cg = new JavaGenerator();
		
		Formatting.tab("    ");
		
		final Type_ typeThread  = new Type_(Thread.class);
		final Type_ spriteStore = new Type_("org.duncan.test.SpriteStore");
		final Type_ soundStore  = new Type_("org.duncan.test.SoundStore");
		
		System.out.println(
			cg.on(new Class_("org.duncan.test.MittTest", typeThread)
				/***** Dependencies *****/
				.add(new Dependency_(spriteStore))
				
				/***** Class declaration *****/
				.public_()
				.setJavadoc(new Javadoc_(new $(
					"This is a test class to demonstrate how the\n",
					"code generator is working."
				)))
				
				/***** Fields *****/
				.add(new Field_("player1Name", STRING))
				.add(new Field_("player2Name", STRING))
				.add(new Field_("player1Score", INT_PRIMITIVE))
				.add(new Field_("player2Score", INT_PRIMITIVE))
				.add(new Field_("spriteStore", spriteStore))
				.add(new Field_("soundStore", soundStore))
					
				/***** Methods *****/
				.add(new Method_("spawnPlayer1", VOID).public_()
					.setJavadoc(new Javadoc_(new $(
						"This function is used to reset Player 1."
					)))
					.add(new Field_("name", STRING))
					.add(new Field_("score", INT_PRIMITIVE))
					.add("this.player1Name = name;")
					.add("this.player1Score = score;")
				)
				.add(new Method_("spawnPlayer2", VOID).public_()
					.setJavadoc(new Javadoc_(new $(
						"This function is used to reset Player 2."
					)))
					.add(new Field_("name", STRING))
					.add(new Field_("score", INT_PRIMITIVE))
					.add("this.player2Name = name;")
					.add("this.player2Score = score;")
				)
				.add(new Method_("killPlayer", VOID).public_()
					.setJavadoc(new Javadoc_(new $(
						"This method can be used to kill either player."
					)))
					.add(new Field_("name", STRING))
					.add("switch (name) {")
					.add("case (this.player1Name) : killPlayer1(); break;")
					.add("case (this.player2Name) : killPlayer2(); break;")
					.add("}")
				)
				.add(new Method_("killPlayer1", VOID).protected_()
					.add("this.player2Score += 20;")
				)
				.add(new Method_("killPlayer2", VOID).protected_()
					.add("this.player1Score += 20;")
				)
				.add(new Method_("restart", VOID).public_()
					.add("spawnPlayer1();")
					.add("spawnPlayer2();")
				)
				
				/***** Controllers *****/
				.call(new SetGet())
				.call(new FinalParameters())
			
			).get()
		);
	}
	
}

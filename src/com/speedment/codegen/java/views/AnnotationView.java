package com.speedment.codegen.java.views;

import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.Annotation;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationView implements CodeView<Annotation> {
	private final static String 
		INTERFACE_STRING = "@interface ",
		DEFAULT_STRING = " default ";
	
	@Override
	public Optional<String> render(CodeGenerator cg, Annotation model) {
		return Optional.of(
			// Javadoc (optional)
			ifelse(cg.on(model.getJavadoc()), c -> c + nl(), EMPTY) +
				
			// Modifiers (public)
			cg.onEach(model.getModifiers()).collect(
				CodeCombiner.joinIfNotEmpty(SPACE)
			) +
				
			// Declaration
			INTERFACE_STRING + model.getName() +
				
			// Block of code
			block(
				model.getFields().stream().map(f ->
					// Field javadoc (optional)
					ifelse(cg.on(f.getJavadoc()), jd -> nl() + jd + nl(), EMPTY) +
					
					// Field declaration
					cg.on(f.getType()) + SPACE + f.getName() + PS + PE +
						
					// Default value (optional)
					ifelse(cg.on(f.getValue()), v -> (DEFAULT_STRING + v), EMPTY) +
							
					SC
				).collect(CodeCombiner.joinIfNotEmpty(nl()))
			)
		);
	}
}

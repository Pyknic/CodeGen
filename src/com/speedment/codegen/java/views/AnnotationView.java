package com.speedment.codegen.java.views;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.Annotation_;
import java.util.Optional;
import com.speedment.util.$;
import static com.speedment.codegen.Formatting.*;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationView implements CodeView<Annotation_> {
	private final static CharSequence 
		INTERFACE_STRING = "@interface ",
		DEFAULT_STRING = " default ";
	
	@Override
	public Optional<CharSequence> render(CodeGenerator cg, Annotation_ model) {
		return Optional.of(new $(
			// Javadoc (optional)
			ifelse(cg.on(model.getJavadoc()), c -> new $(c, nl()), EMPTY),
				
			// Modifiers (public)
			cg.onEach(model.getModifiers()).collect(
				CodeCombiner.joinIfNotEmpty(SPACE)
			),
				
			// Declaration
			INTERFACE_STRING, model.getName(),
				
			// Block of code
			looseBracketsIndent(new $(
				model.getFields().stream().map(f -> new $(
					// Field javadoc (optional)
					ifelse(cg.on(f.getJavadoc()), jd -> new $(nl(), jd, nl()), EMPTY),
					
					// Field declaration
					cg.on(f.getType()), SPACE, f.getName(), PS, PE,
						
					// Default value (optional)
					ifelse(cg.on(f.getValue()), v -> new $(DEFAULT_STRING, v), EMPTY),
					SC
				)).collect(CodeCombiner.joinIfNotEmpty(nl()))
			))
		));
	}
}

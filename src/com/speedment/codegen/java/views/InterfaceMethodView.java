package com.speedment.codegen.java.views;

import static com.speedment.codegen.Formatting.COMMA_SPACE;
import static com.speedment.codegen.Formatting.EMPTY;
import static com.speedment.codegen.Formatting.PE;
import static com.speedment.codegen.Formatting.PS;
import static com.speedment.codegen.Formatting.SC;
import static com.speedment.codegen.Formatting.SPACE;
import static com.speedment.codegen.Formatting.looseBracketsIndent;
import static com.speedment.codegen.Formatting.nl;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.InterfaceMethod;
import static com.speedment.codegen.java.models.modifiers.Modifier.*;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Emil Forslund
 */
public class InterfaceMethodView implements CodeView<InterfaceMethod> {
	@Override
	public Optional<String> render(CodeGenerator cg, InterfaceMethod model) {
		return Optional.of(
			cg.on(model.getJavadoc()).orElse(EMPTY) +
					
			// The only modifiers allowed are default and static
			(model.getModifiers().contains(DEFAULT) ? cg.on(DEFAULT) + SPACE : EMPTY) +
			(model.getModifiers().contains(STATIC) ? cg.on(STATIC) + SPACE : EMPTY) +
			
			cg.on(model.getType()).orElse(EMPTY) + SPACE +
			model.getName() +
			cg.onEach(model.getParams()).collect(
				Collectors.joining(COMMA_SPACE, PS, PE)
			) +
					
			// Append body only if it is either default or static.
			(model.getModifiers().contains(DEFAULT) 
			|| model.getModifiers().contains(STATIC) ?
			SPACE + looseBracketsIndent(
				model.getCode().stream().collect(
					Collectors.joining(nl())
				)
			) : SC)
		);
	}
}

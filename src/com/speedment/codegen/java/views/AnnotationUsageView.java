package com.speedment.codegen.java.views;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.AnnotationUsage;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationUsageView implements CodeView<AnnotationUsage> {
	private final static String 
		PSTART = " (", 
		EQUALS = " = ";

	@Override
	public Optional<String> render(CodeGenerator cg, AnnotationUsage model) {
		return Optional.of(
			AT + cg.on(model.getType()) +
			model.getValues().stream()
				.map(e -> e.getKey() + EQUALS + cg.on(e.getValue()))
				.collect(
					CodeCombiner.joinIfNotEmpty(
						cnl(),
						PSTART,
						PE
					)
				) +
			ifelse(
				cg.on(model.getValue()), 
				c -> c,
				EMPTY
			)
		);
	}
	
}

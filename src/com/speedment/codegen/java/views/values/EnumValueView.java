package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.values.EnumValue;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;

/**
 *
 * @author Emil Forslund
 */
public class EnumValueView implements CodeView<EnumValue> {
	@Override
	public Optional<String> render(CodeGenerator cg, EnumValue model) {
		return Optional.of(
			cg.on(model.getType()).orElse(EMPTY) + DOT +
			model.getValue()
		);
	}
}
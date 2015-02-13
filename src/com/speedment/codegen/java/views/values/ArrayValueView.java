package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.values.ArrayValue;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.CodeGenerator;

/**
 *
 * @author Emil Forslund
 */
public class ArrayValueView implements CodeView<ArrayValue> {
	@Override
	public Optional<String> render(CodeGenerator cg, ArrayValue model) {
		return Optional.of(
			cg.onEach(model.getValue()).collect(
				Collectors.joining(
					COMMA_SPACE, 
					BS, 
					BE
				)
			)
		);
	}
}

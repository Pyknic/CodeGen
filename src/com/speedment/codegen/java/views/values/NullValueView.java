package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.values.NumberValue;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class NullValueView implements CodeView<NumberValue> {
	private final static String NULL = "null";
	
	@Override
	public Optional<String> render(CodeGenerator cg, NumberValue model) {
		return Optional.of(NULL);
	}
}
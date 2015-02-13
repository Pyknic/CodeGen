package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.values.ReferenceValue;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class ReferenceValueView implements CodeView<ReferenceValue> {
	@Override
	public Optional<String> render(CodeGenerator cg, ReferenceValue model) {
		return Optional.of(model.getValue());
	}
}
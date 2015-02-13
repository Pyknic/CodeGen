/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.lang.models.values.ArrayValue;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.speedment.codegen.Formatting.*;

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.values.NumberValue;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class NumberValueView implements CodeView<NumberValue> {
	@Override
	public Optional<String> render(CodeGenerator cg, NumberValue model) {
		return Optional.of(model.getValue().toString());
	}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.values.EnumValue;
import java.util.Optional;
import com.speedment.util.$;
import static com.speedment.codegen.Formatting.*;

/**
 *
 * @author Emil Forslund
 */
public class EnumValueView implements CodeView<EnumValue> {
	@Override
	public Optional<CharSequence> render(CodeGenerator cg, EnumValue model) {
		return Optional.of(new $(
			cg.on(model.getType()).orElse(EMPTY), DOT,
			model.getValue())
		);
	}
}
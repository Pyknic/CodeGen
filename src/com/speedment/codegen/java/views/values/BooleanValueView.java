/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.values.BooleanValue_;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class BooleanValueView implements CodeView<BooleanValue_> {
	@Override
	public Optional<CharSequence> render(CodeGenerator cg, BooleanValue_ model) {
		return Optional.of(model.getValue().toString());
	}
}
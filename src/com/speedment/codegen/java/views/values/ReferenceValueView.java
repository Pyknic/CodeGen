/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.values.ReferenceValue_;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class ReferenceValueView implements CodeView<ReferenceValue_> {
	@Override
	public Optional<CharSequence> render(CodeGenerator cg, ReferenceValue_ model) {
		return Optional.of(model.getValue());
	}
}
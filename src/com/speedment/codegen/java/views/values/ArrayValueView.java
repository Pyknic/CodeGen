/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.codegen.java.views.values;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.values.ArrayValue;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.VersionEnum;

/**
 *
 * @author Emil Forslund
 */
public class ArrayValueView implements CodeView<ArrayValue> {
	@Override
	public <V extends Enum<V> & VersionEnum> Optional<CharSequence> render(CodeGenerator<V> cg, ArrayValue model) {
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

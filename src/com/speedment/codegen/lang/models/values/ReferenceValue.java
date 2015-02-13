package com.speedment.codegen.lang.models.values;

import com.speedment.codegen.lang.models.Value;

/**
 *
 * @author Emil Forslund
 */
public class ReferenceValue extends Value<String, ReferenceValue> {

	public ReferenceValue(String num) {
		super(num);
	}

	@Override
	public ReferenceValue copy() {
		return new ReferenceValue(getValue());
	}
}
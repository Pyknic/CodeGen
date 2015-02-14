package com.speedment.codegen.lang.models.values;

import com.speedment.codegen.lang.models.Value;

/**
 *
 * @author Emil Forslund
 */
public class NullValue extends Value<Number, NullValue> {

	public NullValue() {
		super(0);
	}

	@Override
	public NullValue copy() {
		return this;
	}
}
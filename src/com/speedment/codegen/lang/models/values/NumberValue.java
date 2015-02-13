package com.speedment.codegen.lang.models.values;

import com.speedment.codegen.lang.models.Value;

/**
 *
 * @author Emil Forslund
 */
public class NumberValue extends Value<Number, NumberValue> {

	public NumberValue(Number num) {
		super(num);
	}

	@Override
	public NumberValue copy() {
		return new NumberValue(getValue());
	}
}
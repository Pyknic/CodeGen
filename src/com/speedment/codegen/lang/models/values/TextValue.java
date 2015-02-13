package com.speedment.codegen.lang.models.values;

import com.speedment.codegen.lang.models.Value;

/**
 *
 * @author Emil Forslund
 */
public class TextValue extends Value<String, TextValue> {

	public TextValue(String num) {
		super(num);
	}

	@Override
	public TextValue copy() {
		return new TextValue(getValue());
	}
}
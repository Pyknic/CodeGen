package com.speedment.codegen.java.models.values;

import com.speedment.codegen.java.models.Value;

/**
 *
 * @author Emil Forslund
 */
public class BooleanValue extends Value<Boolean, BooleanValue> {
	public BooleanValue(Boolean num) { super (num); }
	@Override
	public BooleanValue copy() { return new BooleanValue(getValue()); }
}
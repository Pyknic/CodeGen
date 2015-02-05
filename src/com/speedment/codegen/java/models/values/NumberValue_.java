package com.speedment.codegen.java.models.values;

import com.speedment.codegen.java.models.Value_;

/**
 *
 * @author Emil Forslund
 */
public class NumberValue_ extends Value_<Number, NumberValue_> {
	public NumberValue_(Number num) { super (num); }
	@Override
	public NumberValue_ copy() { return new NumberValue_(getValue()); }
}
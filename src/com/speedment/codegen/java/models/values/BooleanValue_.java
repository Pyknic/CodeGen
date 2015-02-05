package com.speedment.codegen.java.models.values;

import com.speedment.codegen.java.models.Value_;

/**
 *
 * @author Emil Forslund
 */
public class BooleanValue_ extends Value_<Boolean, BooleanValue_> {
	public BooleanValue_(Boolean num) { super (num); }
	@Override
	public BooleanValue_ copy() { return new BooleanValue_(getValue()); }
}
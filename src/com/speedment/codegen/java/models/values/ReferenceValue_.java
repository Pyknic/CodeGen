package com.speedment.codegen.java.models.values;

import com.speedment.codegen.java.models.Value_;

/**
 *
 * @author Emil Forslund
 */
public class ReferenceValue_ extends Value_<String, ReferenceValue_> {
	public ReferenceValue_(String num) { super (num); }
	@Override
	public ReferenceValue_ copy() { return new ReferenceValue_(getValue()); }
}
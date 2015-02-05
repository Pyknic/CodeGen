package com.speedment.codegen.java.models.values;

import com.speedment.codegen.java.models.Value_;

/**
 *
 * @author Emil Forslund
 */
public class TextValue_ extends Value_<String, TextValue_> {
	public TextValue_(String num) { super (num); }
	@Override
	public TextValue_ copy() { return new TextValue_(getValue()); }
}
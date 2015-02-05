package com.speedment.codegen.java.models.values;

import com.speedment.codegen.java.models.Value_;
import com.speedment.util.Copier;
import java.util.List;

/**
 *
 * @author Emil Forslund
 */
public class ArrayValue_ extends Value_<List<Value_>, ArrayValue_> {
	public ArrayValue_(List<Value_> val) { super (val); }
	@Override
	public ArrayValue_ copy() {
		return new ArrayValue_(Copier.copy(getValue()));
	}
}
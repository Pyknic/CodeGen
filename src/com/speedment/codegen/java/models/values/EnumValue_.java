package com.speedment.codegen.java.models.values;

import com.speedment.codegen.java.interfaces.Typeable;
import com.speedment.codegen.java.models.Type_;
import com.speedment.codegen.java.models.Value_;

/**
 *
 * @author Emil Forslund
 */
public class EnumValue_ extends Value_<String, EnumValue_>
		implements Typeable<EnumValue_> {
	
	private Type_ type;
	
	public EnumValue_(Type_ type, String value) { 
		super (value); 
		this.type = type;
	}
	
	private EnumValue_(EnumValue_ prototype) {
		this (
			prototype.type.copy(),
			prototype.getValue()
		);
	}

	@Override
	public EnumValue_ setType(Type_ type) {
		this.type = type;
		return this;
	}

	@Override
	public Type_ getType() {
		return type;
	}
	
	@Override
	public EnumValue_ copy() {
		return new EnumValue_(getType(), getValue());
	}
}
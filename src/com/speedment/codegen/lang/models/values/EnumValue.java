package com.speedment.codegen.lang.models.values;

import com.speedment.codegen.lang.interfaces.Typeable;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.Value;

/**
 *
 * @author Emil Forslund
 */
public class EnumValue extends Value<String, EnumValue>
		implements Typeable<EnumValue> {
	
	private Type type;
	
	public EnumValue(Type type, String value) { 
		super (value); 
		this.type = type;
	}
	
	private EnumValue(EnumValue prototype) {
		this (
			prototype.type.copy(),
			prototype.getValue()
		);
	}

	@Override
	public EnumValue setType(Type type) {
		this.type = type;
		return this;
	}

	@Override
	public Type getType() {
		return type;
	}
	
	@Override
	public EnumValue copy() {
		return new EnumValue(this);
	}
}
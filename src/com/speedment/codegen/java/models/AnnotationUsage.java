package com.speedment.codegen.java.models;

import com.speedment.codegen.java.interfaces.Copyable;
import com.speedment.codegen.java.interfaces.Typeable;
import com.speedment.codegen.java.interfaces.Valuable;
import com.speedment.util.Copier;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationUsage implements
		Copyable<AnnotationUsage>,
		Typeable<AnnotationUsage>,
		Valuable<AnnotationUsage> {
	
	private Type type;
	private Optional<Value> value;
	private final List<Entry<String, Value>> values;
	
	public AnnotationUsage(Type type) {
		this.type	= type;
		this.value	= Optional.empty();
		this.values = new ArrayList<>();
	}
	
	private AnnotationUsage(AnnotationUsage prototype) {
		type   = prototype.type.copy();
		value  = Copier.copy(prototype.value);
		values = Copier.copy(prototype.values, 
			e -> new AbstractMap.SimpleEntry<>(
				e.getKey(), e.getValue().copy()
			)
		);
	}
	
	@Override
	public AnnotationUsage setValue(Value val) {
		value = Optional.of(val);
		return this;
	}
	
	public AnnotationUsage setValue(String key, Value val) {
		values.add(new AbstractMap.SimpleEntry<>(key, val));
		return this;
	}
	
	@Override
	public Optional<Value> getValue() {
		return value;
	}
	
	public List<Entry<String, Value>> getValues() {
		return values;
	}

	@Override
	public AnnotationUsage copy() {
		return new AnnotationUsage(this);
	}

	@Override
	public AnnotationUsage setType(Type type) {
		this.type = type;
		return this;
	}

	@Override
	public Type getType() {
		return type;
	}
	
	public final static class Const extends AnnotationUsage {
		public Const(Type type) { 
			super(type); 
		}

		@Override
		public AnnotationUsage setValue(Value val) {
			return copy().setValue(val);
		}
		
		@Override
		public AnnotationUsage setValue(String key, Value val) {
			return copy().setValue(key, val);
		}

		@Override
		public AnnotationUsage setType(Type type) {
			return copy().setType(type);
		}
	}
}

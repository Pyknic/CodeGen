package com.speedment.codegen.java.models;

import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.java.interfaces.Typeable;
import com.speedment.codegen.java.interfaces.Valuable;
import com.speedment.util.Copier;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Native;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationUsage_ implements CodeModel<AnnotationUsage_>,
		Typeable<AnnotationUsage_>,
		Valuable<AnnotationUsage_> {
	
	private Type_ type;
	private Optional<Value_> value;
	private final List<Entry<String, Value_>> values;
	
	public AnnotationUsage_(Type_ type) {
		this.type	= type;
		this.value	= Optional.empty();
		this.values = new ArrayList<>();
	}
	
	private AnnotationUsage_(AnnotationUsage_ prototype) {
		type   = prototype.type.copy();
		value  = Copier.copy(prototype.value);
		values = Copier.copy(prototype.values, e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().copy()));
	}
	
	@Override
	public AnnotationUsage_ setValue(Value_ val) {
		value = Optional.of(val);
		return this;
	}
	
	public AnnotationUsage_ setValue(CharSequence key, Value_ val) {
		values.add(new AbstractMap.SimpleEntry<>(key.toString(), val));
		return this;
	}
	
	@Override
	public Optional<Value_> getValue() {
		return value;
	}
	
	public List<Entry<String, Value_>> getValues() {
		return values;
	}

	@Override
	public AnnotationUsage_ copy() {
		return new AnnotationUsage_(this);
	}

	@Override
	public AnnotationUsage_ setType(Type_ type) {
		this.type = type;
		return this;
	}

	@Override
	public Type_ getType() {
		return type;
	}
	
	public final static class Const extends AnnotationUsage_ {
		public Const(Type_ type) { 
			super(type); 
		}

		@Override
		public AnnotationUsage_ setValue(Value_ val) {
			return copy().setValue(val);
		}
		
		@Override
		public AnnotationUsage_ setValue(CharSequence key, Value_ val) {
			return copy().setValue(key, val);
		}

		@Override
		public AnnotationUsage_ setType(Type_ type) {
			return copy().setType(type);
		}
	}
	
	public final static AnnotationUsage_
			OVERRIDE	= new Const(new Type_(Override.class)),
			DOCUMENTED	= new Const(new Type_(Documented.class)),
			INHERITED	= new Const(new Type_(Inherited.class)),
			NATIVE		= new Const(new Type_(Native.class)),
			REPEATABLE	= new Const(new Type_(Repeatable.class)),
			RETENTION	= new Const(new Type_(Retention.class)),
			TARGET		= new Const(new Type_(Target.class));
}

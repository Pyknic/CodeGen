package com.speedment.codegen.java.models;

import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.java.models.modifiers.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class InterfaceField implements CodeModel<InterfaceField> {
	private final Field f;
	
	public InterfaceField(Field wrapped) {
		f = wrapped;
	}

	public String getName() {
		return f.getName();
	}
	
	public Type getType() {
		return f.getType();
	}

	public Set<Modifier> getModifiers() {
		return f.getModifiers();
	}

	public Optional<Javadoc> getJavadoc() {
		return f.getJavadoc();
	}

	public Optional<Value> getValue() {
		return f.getValue();
	}

	public List<AnnotationUsage> getAnnotations() {
		return f.getAnnotations();
	}
	
	@Override
	public InterfaceField copy() {
		return new InterfaceField(f);
	}
}
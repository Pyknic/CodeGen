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
public class InterfaceMethod implements CodeModel<InterfaceMethod> {
	private final Method m;
	
	public InterfaceMethod(Method wrapped) {
		this.m = wrapped;
	}
	
	public String getName() {
		return m.getName();
	}

	public Type getType() {
		return m.getType();
	}

	public List<Field> getParams() {
		return m.getParams();
	}

	public List<String> getCode() {
		return m.getCode();
	}

	public Set<Modifier> getModifiers() {
		return m.getModifiers();
	}

	public Optional<Javadoc> getJavadoc() {
		return m.getJavadoc();
	}

	public List<AnnotationUsage> getAnnotations() {
		return m.getAnnotations();
	}

	@Override
	public InterfaceMethod copy() {
		return new InterfaceMethod(m);
	}
}
package com.speedment.codegen.java.models;

import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.java.interfaces.Annotable;
import com.speedment.codegen.java.interfaces.Dependable;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.interfaces.Fieldable;
import com.speedment.codegen.java.interfaces.Modifiable;
import com.speedment.codegen.java.interfaces.Nameable;
import com.speedment.codegen.java.models.modifiers.Modifier;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Annotation implements CodeModel<Annotation>, 
		Nameable<Annotation>,
		Documentable<Annotation>,
		Fieldable<Annotation>,
		Dependable<Annotation>,
		Modifiable<Annotation>,
		Annotable<Annotation> {
	
	private CharSequence name;
	private Optional<Javadoc> javadoc;
	private final List<AnnotationUsage> annotations;
	private final List<Field> fields;
	private final List<Import> dependencies;
	private final EnumSet<Modifier> modifiers;

	public Annotation(CharSequence name) {
		this.name			= name;
		this.javadoc		= Optional.empty();
		this.annotations	= new ArrayList<>();
		this.fields			= new ArrayList<>();
		this.dependencies	= new ArrayList<>();
		this.modifiers		= EnumSet.noneOf(Modifier.class);
	}
	
	public Annotation(Annotation prototype) {
		name			= prototype.name.toString();
		javadoc			= Copier.copy(prototype.javadoc);
		annotations		= Copier.copy(prototype.annotations);
		fields			= Copier.copy(prototype.fields);
		dependencies	= Copier.copy(prototype.dependencies);
		modifiers		= Copier.copy(prototype.modifiers);
	}
	
	public Type toType() {
		return new Type(name);
	}

	@Override
	public Annotation setName(CharSequence name) {
		this.name = name;
		return this;
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	@Override
	public Annotation add(Field field) {
		fields.add(field);
		return this;
	}

	@Override
	public List<Field> getFields() {
		return fields;
	}

	@Override
	public Annotation setJavadoc(Javadoc doc) {
		this.javadoc = Optional.of(doc);
		return this;
	}

	@Override
	public Optional<Javadoc> getJavadoc() {
		return javadoc;
	}

	@Override
	public Annotation add(Import dep) {
		dependencies.add(dep);
		return this;
	}

	@Override
	public List<Import> getDependencies() {
		return dependencies;
	}

	@Override
	public EnumSet<Modifier> getModifiers() {
		return modifiers;
	}
	
	@Override
	public Annotation copy() {
		return new Annotation(this);
	}

	@Override
	public Annotation add(AnnotationUsage annotation) {
		annotations.add(annotation);
		return this;
	}

	@Override
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}
}

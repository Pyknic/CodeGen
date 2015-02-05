package com.speedment.codegen.java.models;

import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.java.interfaces.Annotable;
import com.speedment.codegen.java.interfaces.Dependable;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.interfaces.Fieldable;
import com.speedment.codegen.java.interfaces.Modifiable;
import com.speedment.codegen.java.interfaces.Nameable;
import com.speedment.codegen.java.models.modifiers.Modifier_;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class Annotation_ implements CodeModel<Annotation_>,
		Nameable<Annotation_>, 
		Documentable<Annotation_>,
		Fieldable<Annotation_>,
		Dependable<Annotation_>,
		Modifiable<Annotation_>,
		Annotable<Annotation_> {
	
	private CharSequence name;
	private Optional<Javadoc_> javadoc;
	private final List<AnnotationUsage_> annotations;
	private final List<Field_> fields;
	private final List<Import_> dependencies;
	private final EnumSet<Modifier_> modifiers;

	public Annotation_(CharSequence name) {
		this.name			= name;
		this.javadoc		= Optional.empty();
		this.annotations	= new ArrayList<>();
		this.fields			= new ArrayList<>();
		this.dependencies	= new ArrayList<>();
		this.modifiers		= EnumSet.noneOf(Modifier_.class);
	}
	
	public Annotation_(Annotation_ prototype) {
		name			= prototype.name.toString();
		javadoc			= Copier.copy(prototype.javadoc);
		annotations		= Copier.copy(prototype.annotations);
		fields			= Copier.copy(prototype.fields);
		dependencies	= Copier.copy(prototype.dependencies);
		modifiers		= Copier.copy(prototype.modifiers);
	}
	
	public Type_ toType() {
		return new Type_(name);
	}

	@Override
	public Annotation_ setName(CharSequence name) {
		this.name = name;
		return this;
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	@Override
	public Annotation_ add(Field_ field) {
		fields.add(field);
		return this;
	}

	@Override
	public List<Field_> getFields() {
		return fields;
	}

	@Override
	public Annotation_ setJavadoc(Javadoc_ doc) {
		this.javadoc = Optional.of(doc);
		return this;
	}

	@Override
	public Optional<Javadoc_> getJavadoc() {
		return javadoc;
	}

	@Override
	public Annotation_ add(Import_ dep) {
		dependencies.add(dep);
		return this;
	}

	@Override
	public List<Import_> getDependencies() {
		return dependencies;
	}

	@Override
	public EnumSet<Modifier_> getModifiers() {
		return modifiers;
	}
	
	@Override
	public Annotation_ copy() {
		return new Annotation_(this);
	}

	@Override
	public Annotation_ add(AnnotationUsage_ annotation) {
		annotations.add(annotation);
		return this;
	}

	@Override
	public List<AnnotationUsage_> getAnnotations() {
		return annotations;
	}
}

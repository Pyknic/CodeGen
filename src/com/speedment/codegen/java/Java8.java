/*
 * Copyright 2015 Emil Forslund.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.speedment.codegen.java;

import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.base.VersionEnum;
import com.speedment.codegen.java.models.Annotation;
import com.speedment.codegen.java.models.Class;
import com.speedment.codegen.java.models.Import;
import com.speedment.codegen.java.models.EnumConstant;
import com.speedment.codegen.java.models.Enum;
import com.speedment.codegen.java.models.Field;
import com.speedment.codegen.java.models.Generic;
import com.speedment.codegen.java.models.Interface;
import com.speedment.codegen.java.models.Javadoc;
import com.speedment.codegen.java.models.Method;
import com.speedment.codegen.java.models.modifiers.Modifier;
import com.speedment.codegen.java.models.Type;
import com.speedment.codegen.java.models.values.ArrayValue;
import com.speedment.codegen.java.models.values.BooleanValue;
import com.speedment.codegen.java.models.values.EnumValue;
import com.speedment.codegen.java.models.values.NumberValue;
import com.speedment.codegen.java.models.values.ReferenceValue;
import com.speedment.codegen.java.models.values.TextValue;
import com.speedment.codegen.java.views.AnnotationView;
import com.speedment.codegen.java.views.ClassView;
import com.speedment.codegen.java.views.ImportView;
import com.speedment.codegen.java.views.EnumConstantView;
import com.speedment.codegen.java.views.EnumView;
import com.speedment.codegen.java.views.FieldView;
import com.speedment.codegen.java.views.GenericView;
import com.speedment.codegen.java.views.InterfaceView;
import com.speedment.codegen.java.views.JavadocView;
import com.speedment.codegen.java.views.MethodView;
import com.speedment.codegen.java.views.ModifierView;
import com.speedment.codegen.java.views.TypeView;
import com.speedment.codegen.java.views.values.ArrayValueView;
import com.speedment.codegen.java.views.values.BooleanValueView;
import com.speedment.codegen.java.views.values.EnumValueView;
import com.speedment.codegen.java.views.values.NumberValueView;
import com.speedment.codegen.java.views.values.ReferenceValueView;
import com.speedment.codegen.java.views.values.TextValueView;

/**
 *
 * @author Emil Forslund
 */
public enum Java8 implements VersionEnum {
	CLASS			(Class.class,			ClassView.class), 
	INTERFACE		(Interface.class,		InterfaceView.class), 
	METHOD			(Method.class,			MethodView.class), 
	FIELD			(Field.class,			FieldView.class), 
	TYPE			(Type.class,			TypeView.class),
	MODIFIER		(Modifier.class,		ModifierView.class),
	JAVADOC			(Javadoc.class,		JavadocView.class),
	DEPENDENCY		(Import.class,		ImportView.class),
	GENERIC			(Generic.class,		GenericView.class),
	ENUM			(Enum.class,			EnumView.class),
	ENUM_CONSTANT	(EnumConstant.class,	EnumConstantView.class),
	ANNOTATION		(Annotation.class,		AnnotationView.class),
	
	ARRAY_VALUE		(ArrayValue.class,		ArrayValueView.class),
	BOOLEAN_VALUE	(BooleanValue.class,	BooleanValueView.class),
	ENUM_VALUE		(EnumValue.class,		EnumValueView.class),
	NUMBER_VALUE	(NumberValue.class,	NumberValueView.class),
	REFERENCE_VALUE	(ReferenceValue.class,	ReferenceValueView.class),
	TEXT_VALUE		(TextValue.class,		TextValueView.class);
	
	private final java.lang.Class<? extends CodeModel> model;
	private final java.lang.Class<? extends CodeView> view;
	
	private Java8(
			java.lang.Class<? extends CodeModel> m, 
			java.lang.Class<? extends CodeView> v) {
		model = m;
		view = v;
	}
	
	@Override
	public java.lang.Class<? extends CodeModel> getModelClass() {
		return model;
	}
	
	@Override
	public java.lang.Class<? extends CodeView> getViewClass() {
		return view;
	}
	
	public static Java8 of(java.lang.Class<? extends CodeModel> modelClass) {
		return VersionEnum.of(modelClass, Java8.class);
	}
}
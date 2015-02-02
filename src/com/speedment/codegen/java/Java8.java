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
import com.speedment.codegen.java.models.Class_;
import com.speedment.codegen.java.models.Dependency_;
import com.speedment.codegen.java.models.EnumConstant_;
import com.speedment.codegen.java.models.Enum_;
import com.speedment.codegen.java.models.Field_;
import com.speedment.codegen.java.models.Generic_;
import com.speedment.codegen.java.models.Interface_;
import com.speedment.codegen.java.models.Javadoc_;
import com.speedment.codegen.java.models.Method_;
import com.speedment.codegen.java.models.modifiers.Modifier_;
import com.speedment.codegen.java.models.Type_;
import com.speedment.codegen.java.models.Value_;
import com.speedment.codegen.java.views.ClassView;
import com.speedment.codegen.java.views.DependencyView;
import com.speedment.codegen.java.views.EnumConstantView;
import com.speedment.codegen.java.views.EnumView;
import com.speedment.codegen.java.views.FieldView;
import com.speedment.codegen.java.views.GenericView;
import com.speedment.codegen.java.views.InterfaceView;
import com.speedment.codegen.java.views.JavadocView;
import com.speedment.codegen.java.views.MethodView;
import com.speedment.codegen.java.views.ModifierView;
import com.speedment.codegen.java.views.TypeView;
import com.speedment.codegen.java.views.ValueView;

/**
 *
 * @author Emil Forslund
 */
public enum Java8 implements VersionEnum {
	CLASS			(Class_.class,			ClassView.class), 
	INTERFACE		(Interface_.class,		InterfaceView.class), 
	METHOD			(Method_.class,			MethodView.class), 
	FIELD			(Field_.class,			FieldView.class), 
	TYPE			(Type_.class,			TypeView.class),
	MODIFIER		(Modifier_.class,		ModifierView.class),
	JAVADOC			(Javadoc_.class,		JavadocView.class),
	DEPENDENCY		(Dependency_.class,		DependencyView.class),
	GENERIC			(Generic_.class,		GenericView.class),
	VALUE			(Value_.class,			ValueView.class),
	ENUM			(Enum_.class,			EnumView.class),
	ENUM_CONSTANT	(EnumConstant_.class,	EnumConstantView.class);
	
	private final Class<? extends CodeModel> model;
	private final Class<? extends CodeView> view;
	
	private Java8(Class<? extends CodeModel> m, Class<? extends CodeView> v) {
		model = m;
		view = v;
	}
	
	@Override
	public Class<? extends CodeModel> getModelClass() {
		return model;
	}
	
	@Override
	public Class<? extends CodeView> getViewClass() {
		return view;
	}
	
	public static Java8 of(Class<? extends CodeModel> modelClass) {
		return VersionEnum.of(modelClass, Java8.class);
	}
}
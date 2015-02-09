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

import com.speedment.codegen.java.views.InterfaceMethodView;
import com.speedment.codegen.base.Version;
import com.speedment.codegen.java.models.Annotation;
import com.speedment.codegen.java.models.Class;
import com.speedment.codegen.java.models.Enum;
import com.speedment.codegen.java.models.EnumConstant;
import com.speedment.codegen.java.models.Field;
import com.speedment.codegen.java.models.Generic;
import com.speedment.codegen.java.models.Import;
import com.speedment.codegen.java.models.Interface;
import com.speedment.codegen.java.models.InterfaceField;
import com.speedment.codegen.java.models.InterfaceMethod;
import com.speedment.codegen.java.models.Javadoc;
import com.speedment.codegen.java.models.JavadocTag;
import com.speedment.codegen.java.models.Method;
import com.speedment.codegen.java.models.Type;
import com.speedment.codegen.java.models.modifiers.Modifier;
import com.speedment.codegen.java.models.values.ArrayValue;
import com.speedment.codegen.java.models.values.BooleanValue;
import com.speedment.codegen.java.models.values.EnumValue;
import com.speedment.codegen.java.models.values.NumberValue;
import com.speedment.codegen.java.models.values.ReferenceValue;
import com.speedment.codegen.java.models.values.TextValue;
import com.speedment.codegen.java.views.AnnotationView;
import com.speedment.codegen.java.views.ClassView;
import com.speedment.codegen.java.views.EnumConstantView;
import com.speedment.codegen.java.views.EnumView;
import com.speedment.codegen.java.views.FieldView;
import com.speedment.codegen.java.views.GenericView;
import com.speedment.codegen.java.views.ImportView;
import com.speedment.codegen.java.views.InterfaceFieldView;
import com.speedment.codegen.java.views.InterfaceView;
import com.speedment.codegen.java.views.JavadocTagView;
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
public class Java8 extends Version<Java8> {
    public Java8() {
        install(Class.class, ClassView.class);
        install(Interface.class, InterfaceView.class);
        install(Method.class, MethodView.class);
        install(Field.class, FieldView.class);
		install(Type.class, TypeView.class);
		install(Modifier.class, ModifierView.class);
		install(Javadoc.class, JavadocView.class);
		install(JavadocTag.class, JavadocTagView.class);
		install(Import.class, ImportView.class);
		install(Generic.class, GenericView.class);
		install(Enum.class, EnumView.class);
		install(EnumConstant.class, EnumConstantView.class);
		install(Annotation.class, AnnotationView.class);
		install(ArrayValue.class, ArrayValueView.class);
		install(BooleanValue.class, BooleanValueView.class);
		install(EnumValue.class, EnumValueView.class);
		install(NumberValue.class, NumberValueView.class);
		install(ReferenceValue.class, ReferenceValueView.class);
		install(TextValue.class, TextValueView.class);
		install(InterfaceMethod.class, InterfaceMethodView.class);
		install(InterfaceField.class, InterfaceFieldView.class);
    }
}
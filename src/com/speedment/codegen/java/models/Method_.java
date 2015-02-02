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
package com.speedment.codegen.java.models;

import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.interfaces.Nameable;
import com.speedment.codegen.java.interfaces.Typeable;
import com.speedment.codegen.java.models.modifiers.MethodModifier;
import com.speedment.codegen.java.models.modifiers.Modifier_;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class Method_ implements CodeModel<Method_>, 
		Nameable<Method_>, 
		Typeable<Method_>, 
		Documentable<Method_>,
		MethodModifier<Method_> {
	
	private CharSequence name;
	private Type_ type;
	private Optional<Javadoc_> javadoc = Optional.empty();
	private final List<Field_> params;
	private final List<CharSequence> code;
	private final Set<Modifier_> modifiers = EnumSet.noneOf(Modifier_.class);
	
	public Method_(CharSequence name, Type_ type) {
		this.name	= name;
		this.type	= type;
		this.params = new ArrayList<>();
		this.code	= new ArrayList<>();
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	@Override
	public Method_ setName(CharSequence name) {
		this.name = name;
		return this;
	}

	@Override
	public Type_ getType() {
		return type;
	}

	@Override
	public Method_ setType(Type_ type) {
		this.type = type;
		return this;
	}

	public List<Field_> getParams() {
		return params;
	}

	public Method_ add(Field_ param) {
		params.add(param);
		return this;
	}

	public List<CharSequence> getCode() {
		return code;
	}

	public Method_ add(CharSequence codeLine) {
		code.add(codeLine);
		return this;
	}

	@Override
	public Method_ clone() {
		Method_ clone = new Method_(name.toString(), type);
		Collections.copy(clone.params, params);
		Collections.copy(clone.code, code);
		return clone;
	}

	@Override
	public Set<Modifier_> getModifiers() {
		return modifiers;
	}

	@Override
	public Method_ setJavadoc(Javadoc_ doc) {
		javadoc = Optional.of(doc);
		return this;
	}

	@Override
	public Optional<Javadoc_> getJavadoc() {
		return javadoc;
	}
}
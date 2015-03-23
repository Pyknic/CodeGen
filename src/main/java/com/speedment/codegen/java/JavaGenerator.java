/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.codegen.java;

import com.speedment.codegen.base.DefaultDependencyManager;
import com.speedment.codegen.base.Installer;
import com.speedment.codegen.base.DefaultGenerator;

/**
 *
 * @author Emil Forslund
 */
public class JavaGenerator extends DefaultGenerator {
	private final static String[] types = new String[] {
		"void", "byte", "short", "char", "int", "long", "float", 
		"double", "boolean"
	};
    
    public JavaGenerator() {
        this(new JavaInstaller());
    }
	
	public JavaGenerator(Installer... installers) {
		super(new DefaultDependencyManager("java.lang", types), installers);
	}
}

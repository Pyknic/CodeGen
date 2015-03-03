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
package com.speedment.codegen.lang.models.implementation;

import com.speedment.codegen.lang.models.Interface;

/**
 *
 * @author Emil Forslund
 */
public class InterfaceImpl extends ClassOrInterfaceImpl<Interface> implements Interface {

    public InterfaceImpl(String name) {
		super (name);
    }
	
	protected InterfaceImpl(Interface prototype) {
		super (prototype);
    }

	@Override
	public InterfaceImpl copy() {
		return new InterfaceImpl(this);
	}
}
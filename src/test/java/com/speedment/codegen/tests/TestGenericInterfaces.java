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
package com.speedment.codegen.tests;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.java.JavaGenerator;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Generic;
import com.speedment.codegen.lang.models.Interface;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.constants.DefaultType;
import com.speedment.codegen.lang.models.implementation.FieldImpl;
import com.speedment.codegen.lang.models.implementation.GenericImpl;
import com.speedment.codegen.lang.models.implementation.InterfaceImpl;
import com.speedment.codegen.lang.models.implementation.MethodImpl;
import com.speedment.codegen.lang.models.implementation.TypeImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Emil Forslund
 */
public class TestGenericInterfaces {
    
    private CodeGenerator cg;
    private Interface model;
    
    public TestGenericInterfaces() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Interface.setSupplier(() -> new InterfaceImpl(null));
        Type.setSupplier(() -> new TypeImpl(null, null));
        Generic.setSupplier(() -> new GenericImpl());
        Method.setSupplier(() -> new MethodImpl(null, null));
        Field.setSupplier(() -> new FieldImpl(null, null));
    }
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        final Generic t = Generic.of().setLowerBound("T");
        final Type self = Type.of("org.example.Nameable").add(t);
        
        if (!t.asType().isPresent()) {
            System.err.println("Lower bound is missing.");
        }
        
        model = Interface.of("Nameable")
            .add(t.copy().add(self))
            .add(Method.of("getName", DefaultType.STRING))
            .add(Method.of("setName", t.asType().get()).add(Field.of("name", DefaultType.STRING)));
        
        cg = new JavaGenerator();
    }
    
    @After
    public void tearDown() {}

    @Test
    public void testRender() {
        System.out.println("*** Begin TestGenericInterfaces: testRender() ***");
        System.out.println(cg.on(model).get());
        System.out.println("*** End TestGenericInterfaces: testRender() ***");
    }
}

/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
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
package com.speedment.internal.codegen;

import com.speedment.codegen.Generator;
import com.speedment.codegen.model.Field;
import com.speedment.codegen.model.Generic;
import com.speedment.codegen.model.Interface;
import com.speedment.codegen.model.Method;
import com.speedment.codegen.model.Type;
import com.speedment.internal.codegen.java.JavaGenerator;
import com.speedment.internal.codegen.model.FieldImpl;
import com.speedment.internal.codegen.model.GenericImpl;
import com.speedment.internal.codegen.model.InterfaceImpl;
import com.speedment.internal.codegen.model.MethodImpl;
import com.speedment.internal.codegen.model.TypeImpl;
import com.speedment.internal.codegen.model.constant.DefaultType;
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
    
    private Generator cg;
    private Interface model;
    
    public TestGenericInterfaces() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Interface.setMapper(InterfaceImpl::new);
        Type.setMapper(TypeImpl::new);
        Generic.setMapper(GenericImpl::new);
        Method.setMapper(MethodImpl::new);
        Field.setMapper(FieldImpl::new);
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

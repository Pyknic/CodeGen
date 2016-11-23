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
package com.speedment.common.codegen.internal;

import com.speedment.common.codegen.Generator;
import com.speedment.common.codegen.constant.SimpleType;
import com.speedment.common.codegen.internal.java.JavaGenerator;
import com.speedment.common.codegen.model.Field;
import com.speedment.common.codegen.model.Generic;
import com.speedment.common.codegen.model.Interface;
import com.speedment.common.codegen.model.Method;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Emil Forslund
 */
public class TestGenericInterfaces {
    
    private Generator cg;
    private Interface model;
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        
        model = Interface.of("Nameable")
            .add(Generic.of("T"))
            .add(Method.of("getName", String.class))
            .add(Method.of("setName", SimpleType.create("T"))
                .add(Field.of("name", String.class))
            );
        
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

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
package com.speedment.codegen.base;

import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emil Forslund
 */
public class DefaultRenderStackTest {
    
    public DefaultRenderStackTest() {}

    /**
     * Test of class DefaultRenderStack.
     */
    @Test
    public void test() {
        
        String s1 = "a";
        String s2 = "bc";
        String s3 = "cde";
        String s4 = "fghi";
        String s5 = "jklmn";
        Integer b1 = 1337;
        Integer b2 = 2674;
        
        final DefaultRenderStack stack = new DefaultRenderStack();
        stack.push(s1);
        
        System.out.println("--- Making sure pushing works.");
        Optional<String> top1 = stack.fromTop(String.class).findFirst();
        Optional<String> bottom1 = stack.fromBottom(String.class).findFirst();
        assertTrue("Is top present?", top1.isPresent());
        assertTrue("Is bottom present?", bottom1.isPresent());
        assertEquals("Is top correct?", s1, top1.get() );
        assertEquals("Is bottom correct?", s1, bottom1.get());
        
        stack.push(s2);
        stack.push(s3);
        
        System.out.println("--- Making sure pushing multiple times works.");
        Optional<String> top2 = stack.fromTop(String.class).findFirst();
        Optional<String> bottom2 = stack.fromBottom(String.class).findFirst();
        assertTrue("Is top present?", top2.isPresent());
        assertTrue("Is bottom present?", bottom2.isPresent());
        assertEquals("Is top correct?", s3, top2.get());
        assertEquals("Is bottom correct?", s1, bottom2.get());
        
        stack.pop();
        stack.push(b1);
        stack.push(s4);
        stack.push(b2);
        stack.push(s5);
        
        System.out.println("--- Making sure pushing and poping works with different types.");
        Optional<Integer> top3 = stack.fromTop(Integer.class).findFirst();
        Optional<Integer> bottom3 = stack.fromBottom(Integer.class).findFirst();
        assertTrue("Is top present?", top3.isPresent());
        assertTrue("Is bottom present?", bottom3.isPresent());
        assertEquals("Is top correct?", b2, top3.get());
        assertEquals("Is bottom correct?", b1, bottom3.get());
    }
}

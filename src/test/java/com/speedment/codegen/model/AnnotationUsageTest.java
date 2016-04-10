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
package com.speedment.codegen.model;

import com.speedment.internal.codegen.model.AnnotationUsageImpl;
import com.speedment.internal.codegen.model.constant.DefaultType;
import com.speedment.internal.codegen.model.value.NumberValue;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationUsageTest {
	private final static Number[] TEST_VALUES = new Number[] {
		Integer.MAX_VALUE,
		Integer.MIN_VALUE,
		Double.MAX_VALUE,
		Double.MIN_VALUE,
		Long.MAX_VALUE,
		Long.MIN_VALUE,
		0,
		1,
		-12345,
		-0.123456789,
		0.123456789,
		0xabce,
		0b01100011100
	};
	
	private final static String[] TEST_LABELS = new String[] {
		"", " ", "ad87dfjdjLKSFfdsfdfsdfsdfsdfSFjerjkJHJKFHDj45kj645j6kllFHDJFHJas8f7dsd7a8f", 
		"^_1#4\\87328(}?+/$", "0", "ÀӗѧѬɸ♥ḁﺺ", "qqqqqqqqqqqqq", "000000",
		"false", "true", "fAlse", "tr*e", ""
	};

	public AnnotationUsageTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
        AnnotationUsage.setSupplier(type -> new AnnotationUsageImpl(type));
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of set method, of class AnnotationUsage.
	 */
	@Test
	public void testSet_Value() {
		System.out.println("setValue(Value)");
		final AnnotationUsage instance = AnnotationUsage.of(DefaultType.DOUBLE);
		
		for (final Number n : TEST_VALUES) {
			assertNotNull("Make sure Optional 'value' is not null before set.", instance.getValue());
			instance.set(new NumberValue(n));
			assertNotNull("Make sure Optional 'value' is not null after set.", instance.getValue());
			assertTrue("Make sure Optional have a value once on is set.", instance.getValue().isPresent());
			assertEquals("Make sure value is correct.", instance.getValue().get().getValue(), n);
		}
	}

	/**
	 * Test of set method, of class AnnotationUsage.
	 */
	@Test
	public void testPut_String_Value() {
		System.out.println("setValue(String, Value)");

		for (int i = 0; i < TEST_VALUES.length; i++) {
			final AnnotationUsage instance = AnnotationUsage.of(DefaultType.DOUBLE);
			assertNotNull("Make sure values are not null", instance.getValues());
			assertEquals("Make sure values are empty", instance.getValues().size(), 0);
		
			for (int j = 0; j <= i; j++) {
				instance.put(TEST_LABELS[j], new NumberValue(TEST_VALUES[j]));
			}
			
			final List<Map.Entry<String, Value<?>>> result = instance.getValues();
			
			int counter = 0;
			
			for (Map.Entry<String, Value<?>> e : result) {
				assertNotNull("Make sure no entries are null.", e);
				assertNotNull("Make sure no entry keys are null.", e.getKey());
				assertNotNull("Make sure no entry values are null.", e.getValue());
				assertTrue("Make sure keys are in the same order (" + counter + ").", e.getKey().equals(TEST_LABELS[counter]));
				assertTrue("Make sure values are in the same order.", e.getValue().getValue().equals(TEST_VALUES[counter]));
				counter++;
			}
			
			assertEquals("Make sure the number of values are correct (" + i + ").", counter, i + 1);
		}
	}

	/**
	 * Test of copy method, of class AnnotationUsage.
	 */
	@Test
	public void testCopy() {
		System.out.println("copy");
		
		for (Number n : TEST_VALUES) {
			final AnnotationUsage instance = AnnotationUsage.of(DefaultType.DOUBLE);
			instance.set(new NumberValue(n));
			
			for (int i = 0; i < TEST_VALUES.length; i++) {
				instance.put(TEST_LABELS[i], new NumberValue(TEST_VALUES[i]));
			}
			
			final AnnotationUsage instance2 = instance.copy();
			assertEquals("Make sure type is the same.", instance.getType(), instance2.getType());
			assertTrue("Make sure value is the same.", instance.getValue().equals(instance2.getValue()));
			
			final List<Map.Entry<String, Value<?>>> result = instance.getValues();
			final List<Map.Entry<String, Value<?>>> result2 = instance2.getValues();
			
			for (int i = 0; i < result.size(); i++) {
				assertEquals("Make sure value keys are the same.", result.get(i).getKey(), result2.get(i).getKey());
				assertEquals("Make sure value values are the same.", result.get(i).getValue().getValue(), result2.get(i).getValue().getValue());
			}
		}
	}
}

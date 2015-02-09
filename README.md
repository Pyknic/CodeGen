# CodeGen
A model-view based code generator written in Java. It is completely object oriented to encourage reuse and increase testability. If you have a lot of code that depends on the structure of a database, a file system or any other source of information, generate the code instead!

# Example
```java
System.out.println(new JavaGenerator().on(
	new Class("org.example.BasicExample")
		.add(Default.GENERATED)
		.public_()
		.add(
			new Field("BASIC_MESSAGE", Default.STRING)
			.public_().final_().static_()
			.setValue(new TextValue("Hello, world!"))
		)
		.add(
			new Method("main", Default.VOID)
			.setJavadoc(new Javadoc(
				"This is a vary basic example of\n" +
				"the capabilities of the Code Generator."
			))
			.public_().static_()
			.add(new Field("params", Default.STRING.setArrayDimension(1)))
			.add(
				"System.out.println(BASIC_MESSAGE);"
			)
		)
	).call(new AutoJavadoc()).get()
);
```
	
## Result
```java
package org.example;

/**
 * @author Your Name
 */
@Generated
public class BasicExample {
	public final static String BASIC_MESSAGE;
	
	/**
	 * This is a vary basic example of
	 * the capabilities of the Code Generator.
	 * @param params
	 */
	public static void main(String[] params) {
		System.out.println(BASIC_MESSAGE);
	}
}
```

## Languages
Currently only the java language is supported, but the language-dependent code is contained in a single package so that more languages can be supported in the future. Most of the java package can probably be reused if the language in question is similair in syntax.

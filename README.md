# CodeGen
A model-view based code generator written in Java. It is completely object oriented to encourage reuse and increase testability. If you have a lot of code that depends on the structure of a database, a file system or any other source of information, generate the code instead!

## Download
To use CodeGen in your projects, add the following dependency to your `pom.xml`-file:
```xml
<dependency>
    <groupId>com.github.pyknic</groupId>
    <artifactId>codegen</artifactId>
    <version>2.4.3</version>
</dependency>
```

## Example
```java
System.out.println(new JavaGenerator().on(
    File.of("org/example/BasicExample.java")
        .add(Class.of("BasicExample")
            .add(GENERATED)
            .public_()
            .add(
                Field.of("BASIC_MESSAGE", String.class)
                .public_().final_().static_()
                .set(Value.ofText("Hello, world!"))
            )
            .add(
                Method.of("main", void.class)
                .set(Javadoc.of(
                    "This is a vary basic example of " +
                    "the capabilities of the Code Generator."
                ))
                .public_().static_()
                .add(Field.of("params", String[].class))
                .add(
                    "System.out.println(BASIC_MESSAGE);"
                )
            )
        ).call(new AutoJavadoc<>())
    ).get()
);
```
	
### Result
```java
/**
 * Write some documentation here.
 */
package org.example;

public class BasicExample {
    public final static String BASIC_MESSAGE = "Hello, world!";
    
    /**
     * This is a vary basic example of the capabilities of the 
     * Code Generator.
     */
    public static void main(String[] params) {
        System.out.println(BASIC_MESSAGE);
    }
}
```

## Languages
Currently only the java language is supported, but the language-dependent code is contained in a single package so that more languages can be supported in the future. Most of the java package can probably be reused if the language in question is similair in syntax.

## License
Copyright 2016 Emil Forslund

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

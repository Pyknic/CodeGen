package com.speedment.codegen.java.controller;

import com.speedment.codegen.base.CodeController;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.models.Class;
import com.speedment.codegen.java.models.Javadoc;

/**
 *
 * @author Duncan
 */
public class AutoJavadoc implements CodeController<Class> {
 
    @Override
    public void accept(Class model) {
		generateJavadocFor(model);
		model.getMethods().forEach(m -> generateJavadocFor(m));
    }
    
    private void generateJavadocFor(Documentable m) {
		if (!m.getJavadoc().isPresent()) {
			m.setJavadoc(new Javadoc("Write some documentation here."));
		}
    }
}

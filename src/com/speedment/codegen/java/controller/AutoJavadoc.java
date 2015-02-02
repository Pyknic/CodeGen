package com.speedment.codegen.java.controller;

import com.speedment.codegen.base.CodeController;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.models.Class_;
import com.speedment.codegen.java.models.Javadoc_;

/**
 *
 * @author Duncan
 */
public class AutoJavadoc implements CodeController<Class_> {
 
    @Override
    public void accept(Class_ model) {
		generateJavadocFor(model);
		model.getMethods().forEach(m -> generateJavadocFor(m));
    }
    
    private void generateJavadocFor(Documentable m) {
		if (!m.getJavadoc().isPresent()) {
			m.setJavadoc(new Javadoc_("Write some documentation here."));
		}
    }
}

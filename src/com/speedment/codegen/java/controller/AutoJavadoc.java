package com.speedment.codegen.java.controller;

import static com.speedment.codegen.Formatting.SE;
import static com.speedment.codegen.Formatting.SS;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.interfaces.Generable;
import com.speedment.codegen.java.models.Class;
import com.speedment.codegen.java.models.Javadoc;
import com.speedment.codegen.java.models.Method;
import com.speedment.codegen.java.models.constants.Default;
import java.util.function.Consumer;

/**
 *
 * @author Duncan
 */
public class AutoJavadoc implements Consumer<Class> {
 
    @Override
    public void accept(Class model) {
		generateJavadocFor(model);
		model.getMethods().forEach(m -> 
			paramsForMethod(
				generateJavadocFor(m)
			)
		);
    }
    
    private static <T extends Documentable & Generable> T generateJavadocFor(T m) {
		if (!m.getJavadoc().isPresent()) {
			final Javadoc doc = new Javadoc("Write some documentation here.");
			paramsForGenerable(doc, m);
			m.setJavadoc(doc);
		}
		
		return m;
    }
	
	private static Generable<?> paramsForGenerable(Javadoc doc, Generable<?> gen) {
		gen.getGenerics().forEach(g -> 
			g.getLowerBound().ifPresent(t -> doc.add(
				Default.PARAM.setValue(SS + t + SE)
			))
		);
		return gen;
	}
	
	private static Method paramsForMethod(Method m) {
		m.getJavadoc().ifPresent(doc -> {
			m.getParams().forEach(p -> 
				doc.add(Default.PARAM.setValue(p.getName()))
			);

			if (!Default.isVoid(m.getType())) {
				doc.add(Default.RETURN);
			}
		});
		return m;
	}
}

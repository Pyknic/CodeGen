package com.speedment.codegen.java.interfaces;

import com.speedment.codegen.java.models.AnnotationUsage;
import java.util.List;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public interface Annotable<T extends Annotable> {
	T add(final AnnotationUsage annotation);
	List<AnnotationUsage> getAnnotations();
}
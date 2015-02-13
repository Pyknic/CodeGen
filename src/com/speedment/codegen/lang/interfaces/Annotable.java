package com.speedment.codegen.lang.interfaces;

import com.speedment.codegen.lang.models.AnnotationUsage;
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
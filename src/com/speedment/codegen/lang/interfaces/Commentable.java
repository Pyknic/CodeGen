package com.speedment.codegen.lang.interfaces;

import java.util.Optional;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public interface Commentable<T extends Commentable<T>> {
	Optional<String> getComment();
	T setComment(String comment);
}

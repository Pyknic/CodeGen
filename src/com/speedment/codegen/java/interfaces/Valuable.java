package com.speedment.codegen.java.interfaces;

import com.speedment.codegen.java.models.Value_;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public interface Valuable<T extends Valuable<T>> {
	T setValue(final Value_ val);
	Optional<Value_> getValue();
}
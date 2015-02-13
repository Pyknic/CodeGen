package com.speedment.codegen.lang.interfaces;

import com.speedment.codegen.lang.models.Value;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public interface Valuable<T extends Valuable<T>> {
	T setValue(final Value val);
	Optional<Value> getValue();
}
package com.speedment.util;

import com.speedment.codegen.lang.interfaces.Copyable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 *
 * @author Emil Forslund
 */
public abstract class Copier {
	public static <T extends Copyable<T>> Optional<T> copy(Optional<T> prototype) {
		return Copier.copy(prototype, c -> c.copy());
	}
	
	public static <T> Optional<T> copy(Optional<T> prototype, Function<T, T> copier) {
		if (prototype.isPresent()) {
			return Optional.of(
				copier.apply(prototype.get())
			);
		} else {
			return Optional.empty();
		}
	}
	
	public static <T extends Copyable<T>> List<T> copy(List<T> prototype) {
		return Copier.copy(prototype, c -> c.copy());
	}
	
	public static <T> List<T> copy(List<T> prototype, Function<T, T> copier) {
		return copy(prototype, copier, new ArrayList<>());
	}
	
	public static <T extends Copyable<T>> Set<T> copy(Set<T> prototype) {
		return Copier.copy(prototype, c -> c.copy());
	}

	public static <T> Set<T> copy(Set<T> prototype, Function<T, T> copier) {
		return copy(prototype, copier, new HashSet<>());
	}
	
	public static <T, L extends Collection<T>> L copy(Collection<T> prototype, Function<T, T> copier, L empty) {
		prototype.forEach(e -> empty.add(copier.apply(e)));
		return empty;
	}
}
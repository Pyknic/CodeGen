package com.speedment.codegen.base;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * @author Emil Forslund
 */
public class DefaultInstaller implements Installer {
	private final Set<Map.Entry<Class<?>, Class<? extends CodeView>>> modelToView;
	
	public DefaultInstaller() {
		modelToView = new HashSet<>();
	}

	@Override
	public <M, V extends CodeView<M>> void install(Class<M> model, Class<V> view) {
		modelToView.add(new SimpleImmutableEntry<>(model, view));
	}

	@Override
	public Optional<CodeView> withOne(Class<?> model) {
		for (final Map.Entry<Class<?>, Class<? extends CodeView>> e : modelToView) {
			if (e.getKey().isAssignableFrom(model)) {
				return Optional.of(Installer.create(e.getValue()));
			}
		}
		return Optional.empty();
	}
	
	@Override
	public Stream<CodeView> withAll(Class<?> model) {
		final Stream.Builder<CodeView> stream = Stream.builder();
		modelToView.stream()
			.filter(e -> e.getKey().isAssignableFrom(model))
			.forEach((e) -> {
				stream.add(Installer.create(e.getValue()));
			}
		);
		return stream.build();
	}
}
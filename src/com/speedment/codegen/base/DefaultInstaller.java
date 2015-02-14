package com.speedment.codegen.base;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author Emil Forslund
 */
public class DefaultInstaller implements Installer {
	private final Map<Class<?>, Class<? extends CodeView>> modelToView;
	
	public DefaultInstaller() {
		modelToView = new HashMap<>();
	}

	@Override
	public <M, V extends CodeView<M>> void install(Class<M> model, Class<V> view) {
		modelToView.put(model, view);
	}

	@Override
	public Optional<CodeView> withOne(Class<?> model) {
		for (final Map.Entry<Class<?>, Class<? extends CodeView>> e : modelToView.entrySet()) {
			if (e.getKey().isAssignableFrom(model)) {
				return Optional.of(Installer.create(e.getValue()));
			}
		}
		return Optional.empty();
	}
	
	@Override
	public Stream<CodeView> withAll(Class<?> model) {
		final Stream.Builder<CodeView> stream = Stream.builder();
		modelToView.entrySet().stream()
			.filter(e -> e.getKey().isAssignableFrom(model))
			.forEach((e) -> {
				stream.add(Installer.create(e.getValue()));
			}
		);
		return stream.build();
	}
}
package com.speedment.codegen.base;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A generator that can have multiple installers. If the same model is associated
 * with multiple views, only one will be called in the on-methods that return
 * a String, but all of them will be executed in the on-methods that takes a
 * consumer.
 * 
 * @author Emil Forslund
 */
public class MultiGenerator implements CodeGenerator {
	private final DependencyManager mgr;
	private final List<Installer> installers;
	
	public MultiGenerator(Installer... installers) {
		this(new DefaultDependencyManager(), installers);
	}
	
	public MultiGenerator(DependencyManager mgr, Installer... installers) {
		this.installers = Arrays.asList(installers);
		this.mgr = mgr;
	}
	
	@Override
	public DependencyManager getDependencyMgr() {
		return mgr;
	}

	@Override
	public Optional<String> on(Object model) {
		for (Installer i : installers) {
			final Optional<CodeView> view = i.withOne(model.getClass());
			if (view.isPresent()) {
				return view.get().render(this, model);
			}
		}
		
		return Optional.empty();
	}

	@Override
	public void on(Object model, Consumer<String> consumer) {
		installers.stream()
			.flatMap(i -> i.withAll(model.getClass()))
			.forEach(v -> v.render(this, model).ifPresent(consumer));
	}
}
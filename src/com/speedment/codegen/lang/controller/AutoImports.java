package com.speedment.codegen.lang.controller;

import com.speedment.codegen.base.DependencyManager;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Import;
import com.speedment.codegen.lang.models.Type;
import java.util.Collection;
import java.util.function.Consumer;

/**
 *
 * @author Duncan
 */
public class AutoImports implements Consumer<Class> {
	private final DependencyManager mgr;
	
	public AutoImports(DependencyManager mgr) {
		this.mgr = mgr;
	}
 
    @Override
    public void accept(Class model) {
		model.getSuperType().ifPresent(st -> add(model, st));
		addAll(model, model.getInterfaces());
		model.getFields().forEach(f -> add(model, f.getType()));
		model.getMethods().forEach(m -> {
			add(model, m.getType());
			m.getParams().forEach(p -> add(model, p.getType()));
		});
    }
	
	public void add(Class model, Type type) {
		if (!model.getName().equals(type.getName())) {
			if (!mgr.isIgnored(type.getName())) {
				if (!model.getDependencies().stream()
				.anyMatch(imp -> imp.getType().getName().equals(type.getName()))) {
					model.add(new Import(new Type(type.getName())));
				}
			}
		}
	}
	
	public void addAll(Class model, Collection<Type> types) {
		types.forEach(t -> add(model, t));
	}
}

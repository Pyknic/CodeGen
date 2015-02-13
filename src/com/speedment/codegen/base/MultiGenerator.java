package com.speedment.codegen.base;

/**
 *
 * @author Emil Forslund
 */
public class MultiGenerator extends DefaultCodeGenerator {
	public MultiGenerator(Installer... installers) {
		super(combine(installers), new DefaultDependencyManager());
	}
	
	/**
	 * Creates a new <code>DefaultInstaller</code> with all the
	 * installments of all the specified installers.
	 * @param installers The installers to combine.
	 * @return A new <code>DefaultInstaller</code>.
	 */
	private static Installer combine(Installer... installers) {
		final Installer result = new DefaultInstaller();
		
		for (Installer i : installers) {
			i.getInstallments().forEach(
				(m, v) -> result.install(m, v)
			);
		}
		
		return result;
	}
}

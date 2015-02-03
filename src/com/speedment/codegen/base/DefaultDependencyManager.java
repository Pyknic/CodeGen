/*
 * Copyright 2015 Emil Forslund.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.speedment.codegen.base;

import com.speedment.util.$;
import static com.speedment.codegen.Formatting.DOT;
import static com.speedment.codegen.Formatting.shortName;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Emil Forslund
 */
public class DefaultDependencyManager implements DependencyManager {
	private final Set<String> dependencies = new HashSet<>();
	private final Set<String> ignorePackages;
	
	/**
	 * Initalises the DependencyManager.
	 */
	public DefaultDependencyManager() {
		ignorePackages = new HashSet<>();
	}
	
	/**
	 * Initalises the DependencyManager.
	 * @param ignoredPackage A package that should be on the ignore list.
	 */
	public DefaultDependencyManager(CharSequence ignoredPackage) {
		ignorePackages = new HashSet<>();
		ignorePackages.add(ignoredPackage.toString());
	}
	
	/**
	 * Initalises the DependencyManager.
	 * @param ignoredPackage A package that should be on the ignore list.
	 * @param ignoredPackages More packages that should be on the ignore list.
	 */
	public DefaultDependencyManager(CharSequence ignoredPackage, CharSequence... ignoredPackages) {
		ignorePackages = Arrays.stream(ignoredPackages)
			.map(CharSequence::toString)
			.collect(Collectors.toSet());
		ignorePackages.add(ignoredPackage.toString());
	}
	
	/**
	 * Registers the specified resource name as a dependency and returns true
	 * if it was already registered or false if the information was new.
	 * @param fullname The full name (including path) of the dependency.
	 * @return True if the dependency had already been registered.
	 */
	@Override
	public boolean isAlreadyDependentOf(CharSequence fullname) {
		return dependencies.contains(fullname.toString());
	}
	
	/**
	 * Returns true if a dependency (other than this) ending with the name part 
	 * of the specified <code>CharSequence</code> has already been registered. 
	 * Note that this doesn't register any new dependencies.
	 * @param fullname The full name (including package).
	 * @return True if any already registered dependency ends with that name.
	 */
	@Override
	public boolean isNameTaken(CharSequence fullname) {
		final String search = new $(DOT, shortName(fullname)).toString();
		return dependencies.stream().anyMatch(d -> !d.equals(fullname) && d.endsWith(search));
	}
	
	/**
	 * Adds the specified dependency to the list of dependencies. This will <b>not</b>
	 * look to see if the name is already taken. This should be done using the
	 * <code>isNameTaken()</code> if multiple dependencies with the same name is
	 * prohibited.
	 * @param fullname The full name of the dependency (including package).
	 */
	@Override
	public void declareDependency(CharSequence fullname) {
		dependencies.add(fullname.toString());
	}
	
	/**
	 * Adds the specified package to the ignore list. This is the opposite as
	 * calling <code>acceptPackage</code>.
	 * @param packageName The full name of the package.
	 */
	@Override
	public void ignorePackage(CharSequence packageName) {
		ignorePackages.add(packageName.toString());
	}
	
	/**
	 * Removes the specified package from the ignore list. This is the opposite 
	 * as calling <code>ignorePackage</code>.
	 * @param packageName The full name of the package.
	 */
	@Override
	public void acceptPackage(CharSequence packageName) {
		ignorePackages.removeIf(p -> packageName.toString().startsWith(p));
	}
	
	/**
	 * Returns true if the specified class belongs to a package that is on the
	 * ignore list.
	 * @param fullname The full name of a package or a class.
	 * @return True if it should be ignored as a dependency.
	 */
	@Override
	public boolean isIgnored(CharSequence fullname) {
		return ignorePackages.stream().anyMatch(
			p -> fullname.toString().startsWith(p)
		);
	}
}

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

/**
 *
 * @author Emil Forslund
 */
public interface DependencyManager {
	/**
	 * Registers the specified resource name as a dependency and returns true
	 * if it was already registered or false if the information was new.
	 * @param fullname The full name (including path) of the dependency.
	 * @return True if the dependency had already been registered.
	 */
	boolean isAlreadyDependentOf(CharSequence fullname);
	
	/**
	 * Returns true if a dependency (other than this) ending with the name part 
	 * of the specified <code>CharSequence</code> has already been registered. 
	 * Note that this doesn't register any new dependencies.
	 * @param fullname The full name (including package).
	 * @return True if any already registered dependency ends with that name.
	 */
	boolean isNameTaken(CharSequence fullname);
	
	/**
	 * Adds the specified dependency to the list of dependencies. This will <b>not</b>
	 * look to see if the name is already taken. This should be done using the
	 * <code>isNameTaken()</code> if multiple dependencies with the same name is
	 * prohibited.
	 * @param fullname The full name of the dependency (including package).
	 */
	void declareDependency(CharSequence fullname);
	
	/**
	 * Adds the specified package to the ignore list. This is the opposite as
	 * calling <code>acceptPackage</code>.
	 * @param packageName The full name of the package.
	 */
	void ignorePackage(CharSequence packageName);
	
	/**
	 * Removes the specified package from the ignore list. This is the opposite 
	 * as calling <code>ignorePackage</code>.
	 * @param packageName The full name of the package.
	 */
	void acceptPackage(CharSequence packageName);
	
	/**
	 * Returns true if the specified class belongs to a package that is on the
	 * ignore list.
	 * @param fullname The full name of a package or a class.
	 * @return True if it should be ignored as a dependency.
	 */
	boolean isIgnored(CharSequence fullname);
}

/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.codegen.projects;

import java.nio.file.Path;
import java.util.Objects;

/**
 *
 * @author Emil Forslund
 */
public class TextFile implements Comparable {
	private final Path path;
	private final String content;

	public TextFile(Path path, String content) {
		this.path = path;
		this.content = content;
	}

	public Path getPath() {
		return path;
	}

	public String getContent() {
		return content;
	}

	@Override
	public int compareTo(Object o) {
		if (o != null && o.getClass().equals(getClass())) {
			return path.compareTo(((TextFile) o).path);
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 11 * hash + Objects.hashCode(this.path);
		hash = 11 * hash + Objects.hashCode(this.content);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		}
		
		final TextFile other = (TextFile) obj;
		return path.equals(other.path) && content.equals(other.content);
	}
}
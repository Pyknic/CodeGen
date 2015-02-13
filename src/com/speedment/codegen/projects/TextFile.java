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
package com.speedment.codegen.projects;

import java.nio.file.Path;

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
}
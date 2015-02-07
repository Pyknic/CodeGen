package com.speedment.codegen.projects;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.base.Version;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Emil Forslund
 * @param <M> The Class-model used in this version.
 * @param <V> The current version to use.
 */
public class FileManager<M extends CodeModel<M>, V extends Version<V>> {
	private final Set<TextFile> files = new TreeSet<>();
	private final CodeGenerator<V> cg;
	
	public FileManager(CodeGenerator<V> cg) {
		this.cg = cg;
	}
	
	public FileManager<M, V> add(M model, Path relativePath) {
		files.add(new TextFile(relativePath, cg.on(model).get()));
		return this;
	}
	
	public void writeToDisc(Path baseDir) {
		files.forEach(f -> {
			try {
				Files.write(
					baseDir.resolve(f.getPath()),
					f.getContent().getBytes()
				);
			} catch (IOException ex) {
				Logger.getLogger(FileManager.class.getName())
					  .log(Level.SEVERE, null, ex);
			}
		});
	}
}

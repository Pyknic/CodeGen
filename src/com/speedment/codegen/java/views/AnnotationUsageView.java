package com.speedment.codegen.java.views;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.AnnotationUsage_;
import java.util.Optional;
import com.speedment.util.$;
import static com.speedment.codegen.Formatting.*;
import com.speedment.codegen.base.VersionEnum;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Emil Forslund
 */
public class AnnotationUsageView implements CodeView<AnnotationUsage_> {
	private final static CharSequence 
		PSTART = " (", 
		EQUALS = " = ";

	@Override
	public <V extends Enum<V> & VersionEnum> Optional<CharSequence> render(CodeGenerator<V> cg, AnnotationUsage_ model) {
		return Optional.of(new $(
			AT, cg.on(model.getType()), 
			model.getValues().stream()
				.map(e -> new $(e.getKey(), EQUALS, cg.on(e.getValue())))
				.collect(
					CodeCombiner.joinIfNotEmpty(
						cnl(),
						PSTART,
						PE
					)
				),
			ifelse(
				cg.on(model.getValue()), 
				c -> c,
				EMPTY
			)
		));
	}
	
}

/*
 * Copyright 2015 Duncan.
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
package com.speedment.codegen.java.views;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.Value_;
import java.util.Optional;
import static com.speedment.codegen.Formatting.*;
import com.speedment.util.$;

/**
 *
 * @author Duncan
 */
public class ValueView implements CodeView<Value_> {

	@Override
	public Optional<CharSequence> render(CodeGenerator cg, Value_ model) {
		if (model instanceof Value_.Text) {
			return Optional.of(new $(H, model.getValue().toString(), H));
		} else {
			return Optional.of(model.getValue().toString());
		}
	}
	
}

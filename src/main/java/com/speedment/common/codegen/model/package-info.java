/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
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
/**
 * Models for typical object-oriented language building blocks are located in 
 * this package. The ambition of codegen is to separate model, view and 
 * controller logic into different classes. This package represents the model
 * part of that trio.
 * <p>
 * The interfaces in this package does not share a common ancestor. The reason
 * for this is that any class should qualify as a model as long as a 
 * corresponding view is installed in the active
 * {@link com.speedment.common.codegen.Generator}.
 * <p>
 * This package is part of the API. Modifications to classes here should only
 * (if ever) be done in major releases.
 */
package com.speedment.common.codegen.model;
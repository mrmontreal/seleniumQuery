/*
 * Copyright (c) 2017 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass;

public abstract class CssFunctionalPseudoClassCondition implements CssPseudoClassCondition {

    private final SqCssFunctionalPseudoClassArgument argument;

    public CssFunctionalPseudoClassCondition(String argument) {
        if (argument == null) {
            throw new CssFunctionalPseudoClassHasNoArgumentsException();
        }
        this.argument = new SqCssFunctionalPseudoClassArgument(argument);
    }

    public SqCssFunctionalPseudoClassArgument getArgument() {
        return argument;
    }

}

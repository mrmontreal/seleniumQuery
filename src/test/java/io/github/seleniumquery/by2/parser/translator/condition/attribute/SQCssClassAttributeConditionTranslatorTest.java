/*
 * Copyright (c) 2015 seleniumQuery authors
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

package io.github.seleniumquery.by2.parser.translator.condition.attribute;

import io.github.seleniumquery.by2.csstree.condition.attribute.SQCssClassAttributeCondition;
import org.junit.Test;

import static io.github.seleniumquery.by2.parser.translator.condition.attribute.TranslatorsTestUtils.parseAndAssertFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssClassAttributeConditionTranslatorTest {

    @Test
    public void translate__should_translate_regular_classes() {
        assertSelectorIsCompiledToClassName(".abc", "abc");
    }

    @Test
    public void translate__should_translate_escaped_classes() {
        assertSelectorIsCompiledToClassName(".x\\+y", "x+y");
        assertSelectorIsCompiledToClassName(".x\\2b y", "x+y");
        assertSelectorIsCompiledToClassName(".x\\00002by", "x+y");
        assertSelectorIsCompiledToClassName(".\\0000E9fg", "éfg");
        assertSelectorIsCompiledToClassName(".\\3A \\`\\(", ":`(");
        assertSelectorIsCompiledToClassName(".\\31 a2b3c", "1a2b3c");

        assertSelectorIsCompiledToClassName(".\\\"a\\\"b\\\"c\\\"", "\"a\"b\"c\"");
        assertSelectorIsCompiledToClassName(".\\\"", "\"");
        assertSelectorIsCompiledToClassName(".♥", "♥");
        assertSelectorIsCompiledToClassName(".©", "©");
//        assertSelectorIsCompiledToClassName(".“‘’”", "“‘’”");
//        assertSelectorIsCompiledToClassName(".☺☃", "☺☃");
//        assertSelectorIsCompiledToClassName(".⌘⌥", "⌘⌥");
//        assertSelectorIsCompiledToClassName(".𝄞♪♩♫♬", "𝄞♪♩♫♬");
//        assertSelectorIsCompiledToClassName(".💩", "💩");
        assertSelectorIsCompiledToClassName(".\\?", "?");
        assertSelectorIsCompiledToClassName(".\\@", "@");
        assertSelectorIsCompiledToClassName(".\\.", ".");
        assertSelectorIsCompiledToClassName(".\\3A \\)", ":)");
        assertSelectorIsCompiledToClassName(".\\3A \\`\\(", ":`(");
        assertSelectorIsCompiledToClassName(".\\31 23", "123");
        assertSelectorIsCompiledToClassName(".\\31 a2b3c", "1a2b3c");
        assertSelectorIsCompiledToClassName(".\\<p\\>", "<p>");
        assertSelectorIsCompiledToClassName(".\\<\\>\\<\\<\\<\\>\\>\\<\\>", "<><<<>><>");
        assertSelectorIsCompiledToClassName(".\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\[\\>\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\>\\+\\<\\<\\<\\<\\-\\]\\>\\+\\+\\.\\>\\+\\.\\+\\+\\+\\+\\+\\+\\+\\.\\.\\+\\+\\+\\.\\>\\+\\+\\.\\<\\<\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\.\\>\\.\\+\\+\\+\\.\\-\\-\\-\\-\\-\\-\\.\\-\\-\\-\\-\\-\\-\\-\\-\\.\\>\\+\\.\\>\\.", "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
        assertSelectorIsCompiledToClassName(".\\#", "#");
        assertSelectorIsCompiledToClassName(".\\#\\#", "##");
        assertSelectorIsCompiledToClassName(".\\#\\.\\#\\.\\#", "#.#.#");
        assertSelectorIsCompiledToClassName(".\\_", "_");
        assertSelectorIsCompiledToClassName(".\\{\\}", "{}");
        assertSelectorIsCompiledToClassName(".\\#fake\\-id", "#fake-id");
        assertSelectorIsCompiledToClassName(".foo\\.bar", "foo.bar");
        assertSelectorIsCompiledToClassName(".\\3A hover", ":hover");
        assertSelectorIsCompiledToClassName(".\\3A hover\\3A focus\\3A active", ":hover:focus:active");
        assertSelectorIsCompiledToClassName(".\\[attr\\=value\\]", "[attr=value]");
        assertSelectorIsCompiledToClassName(".f\\/o\\/o", "f/o/o");
        assertSelectorIsCompiledToClassName(".f\\\\o\\\\o", "f\\o\\o");
        assertSelectorIsCompiledToClassName(".f\\*o\\*o", "f*o*o");
        assertSelectorIsCompiledToClassName(".f\\!o\\!o", "f!o!o");
        assertSelectorIsCompiledToClassName(".f\\'o\\'o", "f'o'o");
        assertSelectorIsCompiledToClassName(".f\\~o\\~o", "f~o~o");
        assertSelectorIsCompiledToClassName(".f\\+o\\+o", "f+o+o");
    }

    private void assertSelectorIsCompiledToClassName(String actualSelector, String expectedClassName) {
        // given
        // selector arg
        // when
        SQCssClassAttributeCondition cssCondition = parseAndAssertFirstCssCondition(actualSelector, SQCssClassAttributeCondition.class);
        // then
        assertThat(cssCondition.getClassName(), is(expectedClassName));
    }

}
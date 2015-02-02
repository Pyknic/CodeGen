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
package com.speedment.codegen;

import com.speedment.util.$;
import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author Duncan
 */
public class Formatting {

    /**
     * Returns the specified text but with the first character lowercase.
     *
     * @param input The text.
     * @return The resulting text.
     */
    public static CharSequence lcfirst(CharSequence input) {
        return withFirst(input, (first) -> String.valueOf(Character.toLowerCase(first)));
    }

    /**
     * Returns the specified text but with the first character uppercase.
     *
     * @param input The text.
     * @return The resulting text.
     */
    public static CharSequence ucfirst(CharSequence input) {
        return withFirst(input, (first) -> String.valueOf(Character.toUpperCase(first)));
    }

    /**
     * Does something with the first character in the specified CharSequence.
     *
     * @param input The CharSequence.
     * @param callback The something.
     * @return The new CharSequence.
     */
    public static CharSequence withFirst(CharSequence input, Function<Character, CharSequence> callback) {
        if (input == null) {
            return null;
        } else if (input.length() == 0) {
            return EMPTY;
        } else {
            return String.join(EMPTY,
                    callback.apply(input.charAt(0)),
                    input.subSequence(1, input.length())
            );
        }
    }

    /**
     * Surrounds the specified text with brackets.
     *
     * @param text The text to surround.
     * @return The text with a '{' before and a '}' afterwards.
     */
    public static CharSequence tightBrackets(CharSequence text) {
        return new $(BS, text, BE);
    }

    /**
     * Surrounds the specified text with brackets and put the content on a
     * separate line.
     *
     * @param text The text to surround.
     * @return The text with a '{\n' before and a '\n}' afterwards.
     */
    public static CharSequence looseBrackets(CharSequence text) {
        return new $(BS, nl, text, nl, BE);
    }

    /**
     * Indents the specified text, surrounds it with brackets and put the
     * content on a separate line.
     *
     * @param text The text to surround.
     * @return The text with a '{\n' before and a '\n}' afterwards.
     */
    public static CharSequence looseBracketsIndent(CharSequence text) {
        return new $(BS, nl, indent(text), nl, BE);
    }

    /**
     * Indents one level after each new-line-character as defined by
     * <code>nl()</code>.
     *
     * @param text The text to indent.
     * @return The indented text.
     */
    public static CharSequence indent(CharSequence text) {
        return new $(tab, text.toString().replaceAll("\\r?\\n", nltab.toString()));
    }

    /**
     * Returns the new-line-character as defined in <code>nl(String)</code>.
     *
     * @return The new-line character.
     */
    public static CharSequence nl() {
        return nl;
    }

    /**
     * Sets which new-line-character to use. Should be set before any code
     * generation happens for indentation to work as expected.
     *
     * @param nl The new character to use.
     */
    public static void nl(CharSequence nl) {
        Formatting.nl = nl;
        Formatting.dnl = new $(nl, nl);
        Formatting.nltab = new $(nl, tab);
        Formatting.scnl = new $(SC, nl);
        Formatting.scdnl = new $(SC, dnl);
    }

    /**
     * Returns a semicolon followed by a new line character as defined by the
     * <code>nl(String)</code> function.
     *
     * @return A semicolon (;) followed by a new line.
     */
    public static CharSequence scnl() {
        return scnl;
    }

    /**
     * Returns a semicolon followed by two new lines character as defined by the
     * <code>nl(String)</code> function.
     *
     * @return A semicolon (;) followed by two new new lines.
     */
    public static CharSequence scdnl() {
        return scdnl;
    }

    /**
     * Returns the new-line-character as defined in <code>nl(String)</code>.
     *
     * @return The new-line character.
     */
    public static CharSequence dnl() {
        return dnl;
    }

    /**
     * Returns the current tab character. This can be defined by using
     * <code>tab(String)</code>.
     *
     * @return The current tab character.
     */
    public static CharSequence tab() {
        return tab;
    }

    /**
     * Sets the tab character to use when generating code. This should only be
     * called before any indentation occurs to prevent errors.
     *
     * @param tab The new tab character.
     */
    public static void tab(String tab) {
        Formatting.tab = tab;
        Formatting.nltab = new $(nl, tab);
    }

    /**
     * Returns the 'name' part of a long name. This is everything after the last
     * dot.
     *
     * @param longName The long name.
     * @return The name part.
     */
    public static CharSequence shortName(CharSequence longName) {
        final String longNameS = longName.toString();
        if (longNameS.contains(DOT_STRING)) {
            return longNameS.substring(longNameS.lastIndexOf(DOT_STRING) + 1);
        } else {
            return longName;
        }
    }

    /**
     * Returns the 'package' part of a long name. This is everything before the
     * last dot.
     *
     * @param longName The long name.
     * @return The package part.
     */
    public static Optional<CharSequence> packageName(CharSequence longName) {
        final String longNameS = longName.toString();
		if (longNameS.contains(DOT_STRING)) {
			return Optional.of(longNameS.substring(0,
					longNameS.lastIndexOf(DOT_STRING)
			));
		} else {
			return Optional.empty();
		}
    }

    private static CharSequence nl = "\n",
            dnl = "\n\n",
            tab = "\t",
            nltab = "\n\t",
            scnl = ";\n",
            scdnl = ";\n\n";

    public final static String DOT_STRING = ".";

    public final static CharSequence BS = "{",
            BE = "}",
            PS = "(",
            PE = ")",
            AS = "[",
            AE = "]",
            SS = "<",
            SE = ">",
            SPACE = " ",
            EMPTY = "",
            COMMA = ",",
            COMMA_SPACE = ", ",
            SC = ";",
            DOT = ".",
            AT = "@",
			AND = "&",
			STAR = "*",
			SLASH = "/";
}

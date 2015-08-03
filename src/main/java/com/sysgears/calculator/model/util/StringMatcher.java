package com.sysgears.calculator.model.util;

import java.util.regex.Pattern;

/**
 * The <code>StringMatcher</code> class provides methods for formatting the strings, the numbers.
 */
public class StringMatcher {

    /**
     * Returns true if string has a substring that matches the specified pattern.
     *
     * @param expression      the original string
     * @param pattern         the regular expression
     * @param caseInsensitive if true - case-insensitive
     * @return true if the substring is present in the <code>str</code>
     */
    public static boolean findSubstring(final String expression,
                                        final String pattern,
                                        final boolean caseInsensitive) {

        return Pattern.compile((caseInsensitive ? "(?i)" : "(?-i)") + pattern).matcher(expression).find();
    }

    private StringMatcher() {
    }
}
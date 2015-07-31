package com.sysgears.calculator.model.converter;

import java.util.regex.Pattern;

/**
 * The <code>StringConverter</code> class provides methods for formatting the strings, the numbers.
 */
public class StringConverter {

    /**
     * Removes all spaces from the string.
     *
     * @param expression the expression to remove spaces
     * @return the string without spaces
     */
    public static String removeSpaces(final String expression) {
        return expression.replaceAll(" ", "");
    }

    /**
     * Returns true if string has a substring that matches the specified pattern.
     *
     * @param expression      the string
     * @param pattern         the regular expression
     * @param caseInsensitive if true - case-insensitive
     * @return true if the substring is present in the <code>str</code>
     */
    public static boolean findString(final String expression,
                                     final String pattern,
                                     final boolean caseInsensitive) {

        return Pattern.compile((caseInsensitive ? "(?i)" : "(?-i)") + pattern).matcher(expression).find();
    }

    private StringConverter() {
    }
}
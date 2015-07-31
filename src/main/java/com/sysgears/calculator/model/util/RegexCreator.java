package com.sysgears.calculator.model.util;

/**
 * The <code>RegexCreator</code> class provides functionality to build regular expressions.
 */
public class RegexCreator {

    /**
     * Generates the regex pattern based on the <code>expression</code>.
     * The number of spaces between words is unlimited.
     *
     * @param expression a string
     * @return a regular expression
     */
    public static String buildRegex(final String expression) {
        return "^(\\\\s)*" + expression.replaceAll(" ", "(\\\\s)") + "(\\\\s)*$";
    }
}
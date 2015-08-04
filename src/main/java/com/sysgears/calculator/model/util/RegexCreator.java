package com.sysgears.calculator.model.util;

/**
 * The <code>RegexCreator</code> class provides functionality to build regular expressions.
 */
public class RegexCreator {

    /**
     * Returns the regular expression built on the <code>expression</code>,
     * in which whitespaces are replaced by "\s+".
     *
     * @param expression the original expression
     * @return the regex with whitespaces
     */
    public static String createWithSpaces(final String expression) {
        return expression.replaceAll(" ", "(\\\\s+)");
    }

    /**
     * Returns the regular expression built on the <code>expression</code>,
     * which should be in the beginning of the line.
     *
     * @param expression the original expression
     * @return the regex, which should be in the beginning of the line.
     */
    public static String createAtBeginning(final String expression) {
        return "^(\\s)*" + expression;
    }

    /**
     * Returns the regular expression built on the <code>expression</code>,
     * which should be in the end of the line.
     *
     * @param expression the original expression
     * @return the regex, which should be in the end of the line.
     */
    public static String createAtEnd(final String expression) {
        return expression + "(\\s)*$";
    }

    private RegexCreator() {
    }
}
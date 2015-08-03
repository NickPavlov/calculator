package com.sysgears.calculator.model.parser.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;

/**
 * The <code>MathConverter</code> class provides functionality to convert mathematical expressions.
 */
public class MathConverter {

    /**
     * Appends '+' at the start of the <code>expression</code> if the first char not equal to '-' and '+'.
     *
     * @param expression the expression
     * @return the expression with the plus sign at the start
     */
    public static String addPlus(final String expression) {
        return ((expression.charAt(0) != '-') & (expression.charAt(0) != '+')) ? '+' + expression : expression;
    }

    /**
     * If the number is negative, places it in brackets.
     * Otherwise returns the original string.
     *
     * @param number the number in the string
     * @return the number in the brackets if it's negative
     */
    public static String addBrackets(final String number) {
        return (number.charAt(0) == '-') ? '(' + number + ')' : number;
    }

    /**
     * Removes extra mathematical signs for the <code>expression</code>.
     * <p>Example: "-+" = "-"; "+-" = "-"; "--" = "+"; "++" = "+".<p>
     *
     * @return the expression without extra signs
     */
    public static String removeExtraSigns(final String expression) {
        return "";
    }

    /**
     * Removes extra brackets in the <code>expression</code>.
     *
     * @param expression the expression to remove extra brackets
     * @return the expression without extra brackets
     */
    public static String removeExtraBrackets(final String expression) {
        final Brackets[] brackets = Brackets.values();
        final int lastIndex = brackets.length - 1;

        return "";
    }

    private MathConverter() {
    }
}
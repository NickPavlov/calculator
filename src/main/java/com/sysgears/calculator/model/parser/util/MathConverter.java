package com.sysgears.calculator.model.parser.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;

/**
 * The <code>MathConverter</code> class provides functionality to convert mathematical expressions.
 */
public class MathConverter {

    /**
     * If the number is negative, places it in brackets.
     * Otherwise returns the original string.
     *
     * @param number a number in the string
     * @return a number in the brackets if it's negative
     */
    public static String addBrackets(final String number) {
        return (number.charAt(0) == '-') ? "(" + number + ")" : number;
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


        for (int index = 0; index < lastIndex; ++index) {

        }

        return "";
    }

    private MathConverter() {
    }
}
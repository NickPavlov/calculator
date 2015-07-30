package com.sysgears.calculator.model.parser.util;

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

    private MathConverter() {
    }
}
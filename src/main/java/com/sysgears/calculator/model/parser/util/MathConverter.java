package com.sysgears.calculator.model.parser.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.util.strings.StringConverter;

/**
 * The <code>MathConverter</code> class provides functionality to convert mathematical expressions.
 */
public class MathConverter {

    /**
     * Inverts all mathematical signs. Equivalent to multiplying by -1.
     *
     * @param expression the original expression
     * @return the expression with inverted signs
     */
    public static String invertSigns(final String expression) {
        char[] chars = expression.toCharArray();
        if (chars[0] == '-' ) {
            chars[0] = '+';
        } else if (chars[0] == '+' ) {
            chars[0] = '-';
        }
        for (int i = 1; i < chars.length - 1; ++i) {
            if (Character.isDigit(chars[i - 1]) && Character.isDigit(chars[i + 1])) {
                if (chars[i] == '-') {
                    chars[i] = '+';
                } else if (chars[i] == '+') {
                    chars[i] = '-';
                }
            }
        }

        return new String(chars);
    }

    /**
     * Appends the plus sign at the start of the <code>expression</code>.
     * In case if first sign is already present - returns original expression.
     *
     * @param expression the original expression
     * @return the expression with the plus at the start
     */
    public static String appendPlus(final String expression) {
        return ((expression.charAt(0) == '+') || (expression.charAt(0) == '-'))
                ? expression
                : '+' + expression;
    }

    /**
     * Removes empty brackets in the <code>expression</code>.
     *
     * @param expression the original expression
     * @return the expression without empty brackets
     */
    public static String removeEmptyBrackets(final String expression) {
        final String result = expression
                .replaceAll(Brackets.OPENING_BRACKETS + Brackets.CLOSING_BRACKETS, "");

        return result.equals(expression) ? result : removeEmptyBrackets(result);
    }

    /**
     * Removes extra mathematical signs in the <code>expression</code>.
     *
     * @param expression the original expression
     * @return the expression without extra mathematical signs
     */
    public static String removeExtraSigns(final String expression) {
        String result = expression.replaceAll("\\+\\-", "-")
                .replaceAll("\\-\\+", "-").replaceAll("\\-\\-", "+").replaceAll("\\+\\+", "+");

        return result.equals(expression) ? result : removeExtraSigns(result);
    }

    /**
     * Removes the first plus, if it is present in the <code>expression</code>.
     *
     * @param expression the original expression
     * @return the expression without first plus.
     */
    public static String removeFirstPlus(final String expression) {
        return (expression.charAt(0) == '+')
                ? StringConverter.removeCharAt(expression, 0)
                : expression;
    }

    private MathConverter() {
    }
}
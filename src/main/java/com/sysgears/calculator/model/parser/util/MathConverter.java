package com.sysgears.calculator.model.parser.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operations.util.Type;
import com.sysgears.calculator.model.util.strings.StringConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>MathConverter</code> class provides functionality to convert mathematical expressions.
 */
public class MathConverter {

    /**
     * Inverts all mathematical signs. Equivalent to multiplying by -1.
     * The <code>expression</code> should not contain the "\u0000" character.
     *
     * @param expression the original expression
     * @return the expression with inverted signs
     */
    public static String invertSigns(final String expression) {
        return expression.replace('-', '\u0000').replace('+', '-').replace('\u0000', '+');
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
     * Removes extra brackets in the <code>expression</code>.
     *
     * @param expression the original expression
     * @return the expression without extra brackets
     */
    public static String removeExtraBrackets(final String expression) {
        final String operand = Type.OPERAND;
        final StringBuilder pattern = new StringBuilder();
        pattern.append("(?<=").append(Brackets.OPENING_BRACKETS).append(")(");
        for (Brackets brackets : Brackets.values()) {
            pattern.append("\\").append(brackets.getOpeningBracket())
                    .append(operand).append("\\").append(brackets.getClosingBracket())
                    .append("|");
        }
        pattern.append(")(?=").append(Brackets.CLOSING_BRACKETS).append(")");
        final Matcher matcher = Pattern.compile(pattern.toString()).matcher(expression);
        String result = expression;
        if (matcher.find()) {
            result = result.substring(0, matcher.start() - 1) + result.substring(matcher.start(), matcher.end())
                    + result.substring(matcher.end() + 1, result.length());
        }

        return result.equals(expression) ? result : removeExtraBrackets(result);
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
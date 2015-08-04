package com.sysgears.calculator.model.parser.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operations.util.Operands;
import com.sysgears.calculator.model.util.strings.StringConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>MathConverter</code> class provides functionality to convert mathematical expressions.
 */
public class MathConverter {

    /**
     * Appends the plus sign at the start of the <code>expression</code>.
     *
     * @param expression the original expression
     * @return the expression with the plus at the start
     */
    public static String addPlus(final String expression) {
        return "+" + expression;
    }

    /**
     * In case the <code>expression</code> is negative, places it in brackets.
     * Otherwise, returns the original expression.
     *
     * @param expression the original expression
     * @return the expression placed in brackets
     */
    public static String addBrackets(final String expression) {
        return (expression.charAt(0) == '-') ? "(" + expression + ")" : expression;
    }

    /**
     * Removes empty brackets in the <code>expression</code>.
     *
     * @param expression the original expression
     * @return the expression without empty brackets
     */
    public static String removeEmptyBrackets(final String expression) {
        final String result = expression
                .replaceAll(Brackets.generateOpeningPattern() + Brackets.generateClosingPattern(), "");

        return result.equals(expression) ? result : removeEmptyBrackets(result);
    }

    /**
     * Removes extra brackets in the <code>expression</code>.
     *
     * @param expression the original expression
     * @return the expression without extra brackets
     */
    public static String removeExtraBrackets(final String expression) {
        final String realNumber = Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN;
        final StringBuilder pattern = new StringBuilder();
        pattern.append("(?<=").append(Brackets.generateOpeningPattern()).append(")(");
        for (Brackets brackets : Brackets.values()) {
            pattern.append("\\").append(brackets.getOpeningBracket())
                    .append(realNumber).append("\\").append(brackets.getClosingBracket())
                    .append("|");
        }
        pattern.append(")(?=").append(Brackets.generateClosingPattern()).append(")");
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

    /**
     * Removes brackets, if between them a number.
     *
     * @param expression the original expression
     * @return the expression without brackets at the beginning and the end
     */
    public static String removeBrackets(final String expression) {
        final String pattern = Brackets.generateOpeningPattern()
                + Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN + Brackets.generateClosingPattern();
        final Matcher matcher = Pattern.compile(pattern).matcher(expression);
        String result = expression;
        String number;
        if (matcher.find()) {
            number = StringConverter.removeCharAt(matcher.group(), 0);
            number = StringConverter.removeCharAt(number, number.length() - 1);
            result = result.substring(0, matcher.start()) + number + result.substring(matcher.end());
        }

        return result.equals(expression)
                ? result
                : removeBrackets(result);
    }

    private MathConverter() {
    }
}
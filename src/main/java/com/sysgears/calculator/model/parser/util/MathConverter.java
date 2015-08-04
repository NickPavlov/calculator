package com.sysgears.calculator.model.parser.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operations.util.Operands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>MathConverter</code> class provides functionality to convert mathematical expressions.
 */
public class MathConverter {

    /**
     * In case the <code>expression</code> is negative, places it in brackets.
     * Otherwise, returns the original expression.
     *
     * @param expression the original expression
     * @return the expression placed in brackets
     */
    public static String addBrackets(final String expression) {
        return (expression.charAt(0) == '-') ? '(' + expression + ')' : expression;
    }

    /**
     * Removes empty brackets in the <code>expression</code>.
     *
     * @param expression the expression to remove empty brackets
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
     * @param expression the expression to remove extra brackets
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
     * @param expression the expression to remove extra mathematical signs
     * @return the expression without extra mathematical signs
     */
    public static String removeExtraSigns(final String expression) {
        String result = expression.replaceAll("\\+\\-", "-")
                .replaceAll("\\-\\+", "-").replaceAll("\\-\\-", "+").replaceAll("\\+\\+", "+");

        return result.equals(expression) ? result : removeExtraSigns(result);
    }

    private MathConverter() {
    }
}
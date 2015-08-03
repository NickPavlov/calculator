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
     * Places the <code>expression</code> in brackets.
     *
     * @param expression the original expression
     * @return the expression placed in brackets
     */
    public static String addBrackets(final String expression) {
        return (expression.charAt(0) == '-') ? '(' + expression + ')' : expression;
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
            result = result.substring(0, matcher.start()) + result.substring(matcher.start() + 1, matcher.end() - 1)
                    + result.substring(matcher.end(), result.length());
        }

        return result.equals(expression) ? result : removeExtraBrackets(result);
    }

    private MathConverter() {
    }
}
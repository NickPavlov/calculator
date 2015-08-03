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
     * Appends '+' at the start of the <code>expression</code> if the first char not equal to '-' and '+'.
     *
     * @param expression the expression
     * @return the expression with the plus sign at the start
     */
    /*
    public static String addPlus(final String expression) {
        return ((expression.charAt(0) != '-') & (expression.charAt(0) != '+')) ? '+' + expression : expression;
    }
    */

    /**
     * Removes extra brackets in the <code>expression</code>.
     *
     * @param expression the expression to remove extra brackets
     * @return the expression without extra brackets
     */
    public static String removeExtraBrackets(final String expression) {
        final String openingBrackets = Brackets.generateOpeningPattern();
        final String closingBrackets = Brackets.generateClosingPattern();
        final String realNumber = Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN;

        final String generalPattern = "(?<=" + openingBrackets + ")"
                + openingBrackets + realNumber + closingBrackets
                + "(?=" + closingBrackets + ")";

        final Matcher matcher = Pattern.compile(generalPattern).matcher(expression);

        String result = expression;
        while (matcher.find()) {
            result = result.substring(0, matcher.start()) + result.substring(matcher.start() + 1, matcher.end())
                    + result.substring(matcher.end() + 1, result.length());
        }

        return result.equals(expression) ? result : removeExtraBrackets(result);
    }

    private MathConverter() {
    }
}
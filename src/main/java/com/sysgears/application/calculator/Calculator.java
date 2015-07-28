package com.sysgears.application.calculator;

import com.sysgears.application.calculator.brackets.Brackets;
import com.sysgears.application.calculator.operations.Operations;
import com.sysgears.application.calculator.util.*;
import com.sysgears.application.converter.Converter;

import java.util.regex.Matcher;

/**
 * The <code>Calculator</code> class provides methods to calculate mathematical expression in string.
 * Operations that are calculated in the Calculator are described in
 * <code>com.sysgears.application.calculator.operations.Operations</code>.
 */
public class Calculator implements ICalculator {

    /**
     * Accuracy of calculation.
     */
    private final String accuracy;

    /**
     * Maximum priority value
     */
    private final int maxPriority;

    /**
     * Constructs Calculator object with user's calculation accuracy.
     *
     * @param accuracy accuracy of calculation
     */
    public Calculator(final String accuracy) {
        this.accuracy = accuracy;
        this.maxPriority = Operations.getMaxPriorityValue();
    }

    /**
     * Constructs Calculator object with default calculation accuracy: 9 decimal places.
     */
    public Calculator() {
        this("#.#########");
    }

    /**
     * Calculates mathematical expression in the string.
     *
     * @param expression string, mathematical expression
     * @return string with value
     * @throws IllegalArgumentException when input argument is not correct
     */
    public String calculate(final String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Expression can't be null.");
        }

        return performAll(Converter.removeSpaces(expression));
    }

    /**
     * Performs all the mathematical operations of the
     * <code>com.sysgears.application.calculator.operations.Operations</code>.
     *
     * @param expression mathematical expression
     * @return string with performed operations
     */
    private String performAll(final String expression) {
        String result = expression;
        for (Brackets brackets : Brackets.values()) {
            result = findBrackets(brackets, result);
        }
        for (int priority = 0; priority <= maxPriority; ++priority) {
            for (Operations operation : Operations.values()) {
                if (operation.getPriority() == priority) {
                    result = perform(operation, result);
                }
            }
        }
        if (!result.equals(expression)) {
            result = performAll(result);
        }

        return result;
    }

    /**
     * Performs all operations of the specific type in the string.
     *
     * @param operation  type of the operation to perform
     * @param expression string with mathematical expression
     * @return string with performed operations of the specific type
     */
    private String perform(final Operations operation, final String expression) {

        final Matcher matcher = Converter.findSubstring(expression, operation.getSearchParameter());
        String result = expression;

        if (matcher.find()) {
            String[] operands = matcher.group().split(operation.getSplitType());
            double value;

            if (!operands[0].isEmpty()) {
                value = operation.evaluate(Double.parseDouble(operands[0]), Double.parseDouble(operands[1]));
            } else {
                value = operation.evaluate(0, Double.parseDouble(operands[1]));
            }

            result = perform(operation, expression.substring(0, matcher.start())
                    + enableSign(Converter.round(value, accuracy))
                    + expression.substring(matcher.end(), expression.length()));
        }

        return result;
    }

    /**
     * Finds and replaces a substring that matches the pattern.
     *
     * @param replacement replacement pattern
     * @param arg         string
     * @return modified string, otherwise - the original string
     */
    private String findBrackets(final Brackets replacement, final String arg) {
        final Matcher matcher = Converter.findSubstring(arg, replacement.getRegex());

        String result = arg;
        if (matcher.find()) {
            result = findBrackets(replacement, arg.substring(0, matcher.start())
                    + matcher.group(0).substring(1, matcher.group(0).length() - 1)
                    + arg.substring(matcher.end(), arg.length()));
        }

        return result;

        /*
        return (matcher.find()) ? findBrackets(replacement, arg.substring(0, matcher.start())
                + matcher.group(0).substring(1, matcher.group(0).length() - 1)
                + arg.substring(matcher.end(), arg.length())) : arg;
        */
    }

    /**
     * Returns number with enabled sign.
     *
     * @param arg a string of the number
     * @return a string of the number with enabled sign
     */
    private String enableSign(final String arg) {
        return (arg.charAt(0) != '+' & arg.charAt(0) != '-') ? '+' + arg : arg;
    }
}
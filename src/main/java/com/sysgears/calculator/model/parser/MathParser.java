package com.sysgears.calculator.model.parser;

import com.sysgears.calculator.model.converter.Converter;
import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operands.Operands;
import com.sysgears.calculator.model.parser.operations.Operations;
import com.sysgears.calculator.model.parser.operations.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>MathParser</code> class provides methods to parse mathematical expression in string.
 * Operations that are calculated in the MathParser are described in
 * <code>com.sysgears.calculator.model.parser.operations.Operations</code>.
 */
public class MathParser implements IMathParser {

    /**
     * Accuracy of calculation.
     */
    private final String accuracy;

    /**
     * Lowest priority index.
     */
    private final int lowestPriorityIndex;

    /**
     * Constructs MathParser object with user's calculation accuracy.
     *
     * @param accuracy accuracy of calculation
     */
    public MathParser(final String accuracy) {
        this.accuracy = accuracy;
        this.lowestPriorityIndex = Priority.getLowestPriority();
    }

    /**
     * Constructs MathParser object with default calculation accuracy: 9 decimal places.
     */
    public MathParser() {
        this("#.#########");
    }

    /**
     * Parses mathematical expression in the string.
     *
     * @param expression string, mathematical expression
     * @return string with value
     * @throws IllegalArgumentException when input argument is not correct
     */
    public String parse(final String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Expression can't be null.");
        }

        return performAll(Converter.removeSpaces(expression));
    }

    /**
     * Performs all the mathematical operations of the
     * <code>com.sysgears.calculator.model.parser.operations.Operations</code>.
     *
     * @param expression mathematical expression
     * @return string with performed operations
     */
    private String performAll(final String expression) {
        String result = expression;
        //System.out.println(result);
        /*
        for (Brackets brackets : Brackets.values()) {
            result = findBrackets(brackets, result);
        }
        */
        for (int priority = 0; priority <= lowestPriorityIndex; ++priority) {
            for (Operations operation : Operations.values()) {
                if (operation.getPriority() == priority) {
                    result = perform(operation, result);
                    //System.out.println(result);
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
        final Matcher matcher = Converter.findSubstring(expression, operation.getRegex());
        String result = expression;
        if (matcher.find()) {
            //String[] operands = matcher.group().split(operation.getSplitPattern());

            Matcher matcherExpression = Pattern.compile(Operands.SIGN + Operands.REAL_NUMBER).matcher(matcher.group());
            List<String> listOfOperands = new ArrayList<String>();
            while (matcherExpression.find()) {
                listOfOperands.add(matcherExpression.group());
            }

            double value = 0;

            switch (listOfOperands.size()) {
                case 1:
                    value = operation.evaluate(0, Double.parseDouble(listOfOperands.get(0)));
                    break;
                case 2:
                    value = operation.evaluate(Double.parseDouble(listOfOperands.get(0)),
                            Double.parseDouble(listOfOperands.get(1)));
                    break;
            }

            /*
            if (!operands[0].isEmpty()) {
                value = operation.evaluate(Double.parseDouble(operands[0]), Double.parseDouble(operands[1]));
            } else {
                value = operation.evaluate(0, Double.parseDouble(operands[1]));
            }
            */
            result = perform(operation, expression.substring(0, matcher.start())
                    //+ addBrackets(Converter.round(value, accuracy))
                    + addPlus(Converter.round(value, accuracy))
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
        final Matcher matcher = Converter.findSubstring(arg, replacement.getPattern());

        String result = arg;
        if (matcher.find()) {
            result = findBrackets(replacement, arg.substring(0, matcher.start())
                    + matcher.group(0).substring(1, matcher.group(0).length() - 1)
                    + arg.substring(matcher.end(), arg.length()));
        }

        return result;
    }

    /**
     *
     *
     * @param arg a string of the number
     * @return a string of the number with enabled sign
     */
    private String addPlus(final String arg) {
        return (arg.charAt(0) != '+' & arg.charAt(0) != '-') ? '+' + arg : arg;
    }

    /**
     * Returns expression in brackets.
     *
     * @param expression
     * @return
     */
    private String addBrackets(final String expression) {
        return "+(" + expression + ")";
    }
}
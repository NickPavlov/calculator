package com.sysgears.calculator.model.parser;

import com.sysgears.calculator.model.converter.StringConverter;
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

        return performAll(StringConverter.removeSpaces(expression));
    }

    /**
     * Performs all mathematical operations specified in
     * <code>com.sysgears.calculator.model.parser.operations.Operations</code>.
     *
     * @param expression a mathematical expression
     * @return a string with performed operations
     */
    private String performAll(final String expression) {
        String result = expression;
        //System.out.println(result);

        int temp;
        Brackets brackets = null;
        int openingBracket = expression.length();
        int closingBracket = 0;

        System.out.println(result.matches(Operands.SIGN + Operands.REAL_NUMBER));
        String resultCopy = "0";
        while (resultCopy.matches(Operands.SIGN + Operands.REAL_NUMBER)) {
            for (Brackets b : Brackets.values()) {
                temp = findOpeningBracket(expression, openingBracket, b);
                temp = temp == -1 ? openingBracket : temp;
                if (temp < openingBracket) {
                    openingBracket = temp;
                    brackets = b;
                }
            }
            System.out.println("Opening:" + openingBracket);
            closingBracket = findClosingBracket(expression, closingBracket, brackets);
            System.out.println("closing:" + closingBracket);

            resultCopy = expression.substring(openingBracket + 1, closingBracket);
            System.out.println("result: " + resultCopy);
            --openingBracket;
            ++closingBracket;

        }


/*
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
*/
        return result;
    }

    /**
     * Performs all operations of the specific type in the string.
     *
     * @param operation  the type of an operation to perform
     * @param expression a mathematical expression
     * @return string with performed operations of the specific type
     */
    private String perform(final Operations operation, final String expression) {
        Matcher expressionMatcher = StringConverter.findSubstring(expression, operation.getOperationPattern());
        String result = expression;

        if (expressionMatcher.find()) {
            Matcher operandsMatcher = Pattern.compile(Operands.SIGN + Operands.REAL_NUMBER)
                    .matcher(expressionMatcher.group());
            List<String> operands = new ArrayList<String>();
            while (operandsMatcher.find()) {
                operands.add(operandsMatcher.group());
            }

            System.out.println("Expression: " + expressionMatcher.group());
            System.out.println("Operands: " + operands);

            double value = 0;
            switch (operands.size()) {
                case 1:
                    value = operation.evaluate(0, Double.parseDouble(operands.get(0)));
                    break;
                case 2:
                    value = operation.evaluate(Double.parseDouble(operands.get(0)),
                            Double.parseDouble(operands.get(1)));
                    break;
            }

            System.out.println("Value: " + value);

            result = expression.substring(0, expressionMatcher.start())
                    //+ addPlus(StringConverter.round(value, accuracy))
                    + StringConverter.round(value, accuracy)
                    + expression.substring(expressionMatcher.end(), expression.length());

            //temporary
            //result = perform(operation, result);
        }

        return result;
    }

    /**
     * Returns the <code>number</code> with the plus sign.
     * If sign is already present return the originals string.
     *
     * @param number a number
     * @return a number with the plus sign
     */
    private String addPlus(final String number) {
        //return (number.charAt(0) != '+' & number.charAt(0) != '-') ? '+' + number : number;
        return (number.charAt(0) != '+' & number.charAt(0) != '-') ? '+' + number : "(" + number + ")";
    }


    //temporary
    private static int findOpeningBracket(final String expression, final int fromIndex, Brackets brackets) {
        return expression.lastIndexOf(brackets.getOpeningBracket(), fromIndex);
    }

    private static int findClosingBracket(final String expression, final int formIndex, Brackets brackets) {
        return expression.indexOf(brackets.getClosingBracket(), formIndex);
    }

    public static void main(String[] args) {
        String expression = "(1+(5+5))*2-1";
        System.out.println(expression);
        new MathParser().performAll(expression);
    }
}
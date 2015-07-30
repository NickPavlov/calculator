package com.sysgears.calculator.model.parser;

import com.sysgears.calculator.model.converter.StringConverter;
import com.sysgears.calculator.model.parser.operands.Operands;
import com.sysgears.calculator.model.parser.operations.Operations;
import com.sysgears.calculator.model.parser.operations.Priority;
import com.sysgears.calculator.model.parser.util.MathConverter;

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
     * The regular expression for the real number.
     */
    private static final String numberPattern = Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN;

    /**
     * The numeric literal.
     */
    private static final String numericLiteral = "0";

    /**
     * An accuracy of calculation.
     */
    private final String accuracy;

    /**
     * A lowest priority index.
     */
    private final int lowestPriorityIndex;

    /**
     * Constructs the <code>MathParser</code> object with the user's calculation accuracy.
     *
     * @param accuracy the accuracy of calculation
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
     * Parses a mathematical expression in the string.
     *
     * @param expression the mathematical expression
     * @return the value as the string
     * @throws IllegalArgumentException when the input argument is not correct
     */
    public String parse(final String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Expression can't be null.");
        }

        return parseAllOperations(parseAllBrackets(StringConverter.removeSpaces(expression)));
    }

    /**
     * Parses all mathematical operations specified in
     * <code>com.sysgears.calculator.model.parser.operations.Operations</code>
     * in the separate brackets.
     *
     * @param expression the mathematical expression
     * @return the mathematical expression with parsed operations in the brackets
     */
    private String parseAllBrackets(final String expression) {

        /*
         Temporary.
         It must take into account all types of brackets specified in Operations, not only round brackets.
        */
        final String bracketsPattern = "(?<=\\()([0-9\\Q^/+-*\\E]?(\\([\\+-]?\\d+(\\.\\d+)?\\))?)+(?=\\))";

        final Matcher matcher = Pattern.compile(bracketsPattern).matcher(expression);
        String result = expression;
        String temp = numericLiteral;
        int openingBracketPos = 0;
        int closingBracketPos = 0;
        while (matcher.find() && temp.matches(numberPattern)) {
            temp = matcher.group();
            openingBracketPos = matcher.start();
            closingBracketPos = matcher.end();
        }
        if (!numericLiteral.equals(temp)) {
            result = expression.substring(0, openingBracketPos)
                    + parseAllOperations(temp) + expression.substring(closingBracketPos, expression.length());
        }

        return result.equals(expression) ? result : parseAllBrackets(result);
    }

    /**
     * Parses all mathematical operations specified in
     * <code>com.sysgears.calculator.model.parser.operations.Operations</code>.
     *
     * @param expression the mathematical expression
     * @return the string with performed operations
     */
    private String parseAllOperations(final String expression) {
        String result = expression;

        for (int priority = 0; priority <= lowestPriorityIndex; ++priority) {
            for (Operations operation : Operations.values()) {
                if (operation.getPriority() == priority) {
                    result = perform(operation, result);
                }
            }
        }

        return result.equals(expression) ? result : parseAllOperations(result);
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
            Matcher operandsMatcher = Pattern.compile(Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN)
                    .matcher(expressionMatcher.group());
            List<String> operands = new ArrayList<String>();
            while (operandsMatcher.find()) {
                operands.add(operandsMatcher.group());
            }
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
            result = perform(operation, expression.substring(0, expressionMatcher.start())
                    + MathConverter.addBrackets(StringConverter.round(value, accuracy))
                    + expression.substring(expressionMatcher.end(), expression.length()));
        }

        return result;
    }
}
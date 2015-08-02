package com.sysgears.calculator.model.parser;

import com.sysgears.calculator.model.converter.StringConverter;
import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operands.Operands;
import com.sysgears.calculator.model.parser.operations.Operations;
import com.sysgears.calculator.model.parser.operations.Priority;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>MathParser</code> class provides methods to getCommand mathematical expression in string.
 * Operations that are calculated in the MathParser are described in
 * <code>com.sysgears.calculator.model.parser.operations.Operations</code>.
 */
public class MathParser implements IMathParser {

    /**
     * The default accuracy of calculation.
     */
    public static final String DEFAULT_ACCURACY = "#.#########";

    /**
     * The regular expression for the real number.
     */
    private static final String numberPattern = Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN;

    /**
     * The numeric literal.
     */
    private static final String numericLiteral = "0";

    /**
     * The number formatter.
     */
    private final DecimalFormat formatter;

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
        this.formatter = new DecimalFormat(accuracy);
        this.lowestPriorityIndex = Priority.getLowestPriority();
    }

    /**
     * Constructs MathParser object with default calculation accuracy: 9 decimal places.
     */
    public MathParser() {
        this(DEFAULT_ACCURACY);
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

        String withoutSpaces = StringConverter.removeSpaces(expression);

        return parseBrackets(withoutSpaces, withoutSpaces.length());
    }

    /**
     * Parses all mathematical operations specified in
     * <code>com.sysgears.calculator.model.parser.operations.Operations</code>
     * in the separate brackets.
     *
     * @param expression the mathematical expression
     * @return the mathematical expression with parsed operations in the brackets
     */
    private String parseBrackets(final String expression, final int openingBracketIndex) {
        final Matcher matcher = Pattern.compile(Brackets.generateOpeningPattern())
                .matcher(expression.substring(0, openingBracketIndex));

        String result = expression;
        if (matcher.find()) {
            int start = matcher.start();
            //looks for the last opening bracket.
            while (matcher.find()) {
                start = matcher.start();
            }
            //System.out.println("start: " + start);
            String openingBracket = "" + result.charAt(start);
            String closingBracket = Brackets.getClosingPair(openingBracket);
            //System.out.println("openingBracket: " + openingBracket);
            //System.out.println("closingBracket: " + closingBracket);
            //System.out.println("\n\n");

            int openingBracketsCount = 1;
            int closingBracketsCount = 0;
            int index = start;
            while (openingBracketsCount != closingBracketsCount) {
                ++index;
                //System.out.println("result.charAt(index): " + result.charAt(index));
                if (openingBracket.equals("" + result.charAt(index))) {
                    ++openingBracketsCount;
                }
                if (closingBracket.equals("" + result.charAt(index))) {
                    ++closingBracketsCount;
                }
            }
            /*
            System.out.println("openingBracketsCount" + openingBracketsCount);
            System.out.println("closingBracketsCount" + closingBracketsCount);
            String beforeBrackets = result.substring(0, start);
            String inBrackets = result.substring(start, index);
            String afterBrackets = result.substring(index, result.length());

            System.out.println("beforeBrackets: " + beforeBrackets);
            System.out.println("inBrackets: " + inBrackets);
            System.out.println("afterBrackets: " + afterBrackets);
            */

            result = result.substring(0, start)
                    + parseOperations(result.substring(start, index))
                    + result.substring(index, result.length());

            /*
            System.out.println();
            System.out.println("result: " + result);
            System.out.println();
            */
            result = parseBrackets(result, start);
        }


        return parseOperations(result);
    }

    /**
     * Parses all mathematical operations specified in
     * <code>com.sysgears.calculator.model.parser.operations.Operations</code>.
     *
     * @param expression the mathematical expression
     * @return the string with performed operations
     */
    private String parseOperations(final String expression) {
        String result = expression;
        for (int priority = 0; priority <= lowestPriorityIndex; ++priority) {
            for (Operations operation : Operations.values()) {
                if (operation.getPriority() == priority) {
                    result = parseOperation(operation, result);
                }
            }
        }

        return result.equals(expression) ? result : parseOperations(result);
    }

    /**
     * Parses all operations of the specific type.
     *
     * @param operation  the type of an operation to parseOperation
     * @param expression the mathematical expression
     * @return the string with performed operations of the specific type
     */
    private String parseOperation(final Operations operation, final String expression) {
        final Matcher expressionMatcher = Pattern.compile(operation.getOperationPattern()).matcher(expression);
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
                case 0:
                    value = operation.calculate(0, 0);
                    break;
                case 1:
                    value = operation.calculate(0, Double.parseDouble(operands.get(0)));
                    break;
                case 2:
                    value = operation.calculate(Double.parseDouble(operands.get(0)),
                            Double.parseDouble(operands.get(1)));
                    break;
            }
            result = parseOperation(operation, expression.substring(0, expressionMatcher.start())
                    //+ MathConverter.addBrackets(NumericalConverter.format(value, accuracy))
                    + formatter.format(value)
                    + expression.substring(expressionMatcher.end(), expression.length()));
        }

        return result;
    }
}
package com.sysgears.calculator.model.parser;

import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operations.Operations;
import com.sysgears.calculator.model.parser.operations.util.Priority;
import com.sysgears.calculator.model.parser.operations.util.Type;
import com.sysgears.calculator.model.parser.util.MathConverter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

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
     * The number formatter.
     */
    private final DecimalFormat formatter;

    /**
     * A lowest priority index.
     */
    private final int lowestPriorityIndex;

    /**
     * Creates the <code>MathParser</code> object with the user's calculation accuracy.
     *
     * @param accuracy the accuracy of calculation
     */
    public MathParser(final String accuracy) {
        this.formatter = new DecimalFormat(accuracy);
        this.lowestPriorityIndex = Priority.getLowestPriority();
    }

    /**
     * Creates the <code>MathParser</code> object with default calculation accuracy in 9 decimal places.
     */
    public MathParser() {
        this(DEFAULT_ACCURACY);
    }

    /**
     * Parses the mathematical expression in the string.
     *
     * @param expression the mathematical expression
     * @return the parsed value as the string
     * @throws IllegalArgumentException when the input argument is null
     */
    public String parse(final String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Expression can't be null.");
        }
        String result = expression.replaceAll(" ", "");
        if (result.isEmpty()) {
            result = "0";
        } else {
            /*  Before parsing.  */
            result = MathConverter.removeEmptyBrackets(MathConverter.removeExtraSigns(result));
            /*  After parsing.  */
            result = MathConverter.removeExtraSigns(parseBrackets(result, result.length()));
            result = MathConverter.removeFirstPlus(result);
        }

        return result;
    }

    /**
     * Parses all mathematical operations specified in
     * <code>com.sysgears.calculator.model.parser.operations.Operations</code>
     * in the separate brackets.
     *
     * @param expression           the mathematical expression
     * @param openingBracketBorder the index to which you want to search for an opening bracket
     * @return the mathematical expression with parsed operations in the brackets
     */
    private String parseBrackets(final String expression, final int openingBracketBorder) {
        final Matcher matcher = Brackets.OPENING_PATTERN.matcher(expression.substring(0, openingBracketBorder));
        String result = expression;
        if (matcher.find()) {
            int start = matcher.start();
            while (matcher.find()) {
                start = matcher.start();
            }
            final char closingBracket = Brackets.getClosingPair(result.charAt(start));
            int index = start + 1;
            while ((index < result.length()) && (result.charAt(index) != closingBracket)) {
                ++index;
            }
            String beforeBrackets = result.substring(0, start);
            String value = parseOperations(result.substring(start + 1, index++));
            if ((value.charAt(0) == '-')) {
                if (beforeBrackets.isEmpty() || (beforeBrackets.charAt(start - 1) == '-')
                        || (beforeBrackets.charAt(start - 1) == '+')
                        || beforeBrackets.charAt(start - 1) == '(') {
                    value = '+' + value;
                }
            }
            result = parseBrackets(beforeBrackets + value + result.substring(index), start);
        }

        return MathConverter.removeExtraSigns(parseOperations(result));
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
        boolean inverted = false;
        if (result.charAt(0) == '-') {
            result = MathConverter.invertSigns(result);
            inverted = true;
        }
        for (int priority = 0; priority <= lowestPriorityIndex; ++priority) {
            for (Operations operation : Operations.values()) {
                if (operation.getPriority().getIndex() == priority) {
                    result = parseOperation(operation, result);
                }
            }
        }
        if (inverted) {
            result = MathConverter.invertSigns(MathConverter.appendPlus(result));
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
        final Matcher expressionMatcher = operation.getOperationPattern().matcher(expression);
        String result = MathConverter.removeExtraBrackets(expression);
        if (expressionMatcher.find()) {
            Matcher operandsMatcher = Type.OPERAND_PATTERN.matcher(expressionMatcher.group());
            List<Double> operands = new ArrayList<Double>();
            while (operandsMatcher.find()) {
                operands.add(Double.parseDouble(operandsMatcher.group()));
            }
            result = parseOperation(operation, expression.substring(0, expressionMatcher.start())
                    + formatter.format(operation.calculate(operands))
                    + expression.substring(expressionMatcher.end()));
        }

        return result;
    }
}
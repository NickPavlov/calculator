package com.sysgears.calculator.model.parser;

import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operations.Operations;
import com.sysgears.calculator.model.parser.operations.util.Priority;
import com.sysgears.calculator.model.parser.util.MathConverter;

import java.text.DecimalFormat;
import java.util.regex.Matcher;

import static java.lang.Character.isDigit;

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
            result = MathConverter.removeEmptyBrackets(MathConverter.removeExtraSigns(result));
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
                        || Brackets.OPENING_PATTERN.matcher(beforeBrackets.charAt(start - 1) + "").find()) {
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
        System.out.println("expression: "+expression);
        int operatorFirstIndex = expression.indexOf(operation.getOperator());
        if (operatorFirstIndex != -1) {
            int afterOperatorIndex = operatorFirstIndex + operation.getOperator().length();
            String before = expression.substring(0, operatorFirstIndex);
            String after = expression.substring(afterOperatorIndex);

            //System.out.println(before);
            //System.out.println(after);

            //first operand
            String firstOperand = "";
            int index = before.length() - 1;
            if (index == 0) {
                firstOperand = before.charAt(0) + "";
            } else {
                while ((index > 0)
                        && ((before.charAt(index) == '-' && !isDigit(before.charAt(index - 1))) ||
                        isDigit(before.charAt(index)) || isDot(before.charAt(index)))) {
                    firstOperand = before.charAt(index) + firstOperand;
                    --index;
                }
            }

            System.out.println(firstOperand);


            //second operand
            String secondOperand = after.charAt(0) + "";

            index = 1;
            while ((index < after.length() - 1)
                    && (isDigit(after.charAt(index)) || isDot(after.charAt(index)))) {
                secondOperand = secondOperand + after.charAt(index);
                index++;
            }

            System.out.println(secondOperand);


/*
            switch (operation.getType()) {
                case BINARY:

                    break;
                case UNARY:

                    break;
                case CONSTANT:

                    break;
            }
*/
        }

        return "";
    }

    /**
     * Returns true is <code>character</code> is a dot.
     *
     * @param character the character to check
     * @return true if <code>character</code> is a dot
     */
    private boolean isDot(final char character) {
        return character == '.';
    }

    /**
     * Returns true is <code>character</code> is a minus.
     *
     * @param character the character to check
     * @return true if <code>character</code> is a dot
     */
    private boolean isMinus(final char character) {
        return character == '-';
    }

    public static void main(String[] args) {
        System.out.println(new MathParser().parseOperation(Operations.MULTIPLY, "1-2-2.0*-3.0-4"));
    }
}
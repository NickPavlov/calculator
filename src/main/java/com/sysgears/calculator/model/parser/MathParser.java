package com.sysgears.calculator.model.parser;

import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operations.Operations;
import com.sysgears.calculator.model.parser.operations.util.Operands;
import com.sysgears.calculator.model.parser.operations.util.Priority;
import com.sysgears.calculator.model.parser.util.MathConverter;

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
     * The regular expression for the operand.
     */
    private static final String OPERAND_PATTERN = "(?<![\\d\\)" + Brackets.generateClosingPattern() + "])"
            + Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN;

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

        String result = expression.replaceAll(" ", "");

        return parseBrackets(result, result.length());
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
        final Matcher matcher = Pattern.compile(Brackets.generateOpeningPattern())
                .matcher(expression.substring(0, openingBracketBorder));
        String result = expression;
        if (matcher.find()) {
            int start = matcher.start();
            while (matcher.find()) {
                start = matcher.start();
            }
            char openingBracket = result.charAt(start);
            char closingBracket = Brackets.getClosingPair(openingBracket);
            int openingBracketsCount = 1;
            int closingBracketsCount = 0;
            int index = start + 1;
            while ((index < result.length()) && (openingBracketsCount != closingBracketsCount)) {
                if (openingBracket == result.charAt(index)) {
                    ++openingBracketsCount;
                } else if (closingBracket == result.charAt(index)) {
                    ++closingBracketsCount;
                }
                ++index;
            }
            /*
            if (openingBracketsCount != closingBracketsCount) {
                throw new ParseException("Closing bracket not found.", index - 1);
            }
            */
            result = parseBrackets(result.substring(0, start) + parseOperations(result.substring(start, index - 1))
                    + result.substring(index - 1, result.length()), start);
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
                if (operation.getPriority().getIndex() == priority) {
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
        String result = MathConverter.removeExtraBrackets(expression);
        if (expressionMatcher.find()) {
            Matcher operandsMatcher = Pattern.compile(OPERAND_PATTERN).matcher(expressionMatcher.group());
            List<Double> operands = new ArrayList<Double>();
            while (operandsMatcher.find()) {
                operands.add(Double.parseDouble(operandsMatcher.group()));
            }
            result = parseOperation(operation, expression.substring(0, expressionMatcher.start())
                    + MathConverter.addBrackets(formatter.format(operation.calculate(operands)))
                    + expression.substring(expressionMatcher.end(), expression.length()));
        }

        return result;
    }
}
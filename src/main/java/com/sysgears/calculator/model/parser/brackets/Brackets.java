package com.sysgears.calculator.model.parser.brackets;

import com.sysgears.calculator.model.parser.operands.Operands;
import com.sysgears.calculator.model.parser.operations.Operations;

/**
 * The <code>Brackets</code> class provides a set of brackets.
 */
public enum Brackets {

    ROUND_BRACKETS("(", ")"),
    SQUARE_BRACKETS("[", "]"),
    CURLY_BRACKETS("{", "}"),
    ANGLE_BRACKETS("<", ">");

    /**
     * Opening bracket.
     */
    private String openingBracket;

    /**
     * Closing bracket.
     */
    private String closingBracket;

    /**
     * Generates the regular expression for all type of brackets specified in
     * <code>com.sysgears.calculator.model.parser.brackets.Brackets</code>.
     *
     * @return the regular expression for the all type of brackets
     */
    public static String generatePattern() {
        final Brackets[] brackets = Brackets.values();
        final int lastIndex = brackets.length - 1;

        final StringBuilder numberPattern = new StringBuilder();

        numberPattern.append("(");
        for (int index = 0; index < lastIndex; ++index) {
            numberPattern.append(generateNumberPattern(brackets[index], "|"));
        }
        numberPattern.append(generateNumberPattern(brackets[lastIndex], ")?"));

        StringBuilder bracketsPattern = new StringBuilder();
        bracketsPattern.append("(");
        for (int index = 0; index < lastIndex; ++index) {
            bracketsPattern.append(generateExpressionPattern(brackets[index], numberPattern.toString(), "|"));
        }
        bracketsPattern.append(generateExpressionPattern(brackets[lastIndex], numberPattern.toString(), ")"));

        return bracketsPattern.toString();

        //Valid.
        //"(?<=\\()([0-9\\Q^/+-*\\E]?(\\([\\+-]?\\d+(\\.\\d+)?\\))?)+(?=\\))"
    }

    /**
     * Generates the regular expression for all opening brackets.
     *
     * @return the regular expression for all opening brackets
     */
    public static String generateOpeningPattern() {
        StringBuilder openingBrackets = new StringBuilder();
        openingBrackets.append("[");
        for (Brackets brackets : Brackets.values()) {
            openingBrackets.append("\\")
                    .append(brackets.getOpeningBracket());
        }
        openingBrackets.append("]");

        return openingBrackets.toString();
    }

    /**
     * Generates the regular expression for all closing brackets.
     *
     * @return the regular expression for all closing brackets
     */
    public static String generateClosingPattern() {
        StringBuilder closingBrackets = new StringBuilder();
        closingBrackets.append("[");
        for (Brackets brackets : Brackets.values()) {
            closingBrackets.append("\\")
                    .append(brackets.getClosingBracket());
        }
        closingBrackets.append("]");

        return closingBrackets.toString();
    }

    private static String generateExpressionPattern(final Brackets brackets,
                                                    final String numberPattern,
                                                    final String afterExpression) {

        return "(?<=\\" + brackets.openingBracket + ")"
                + "([0-9]?"
                + Operations.generateOperatorsPattern()
                + "?"
                + numberPattern
                + ")+"
                + "(?=\\" + brackets.closingBracket
                + ")"
                + afterExpression;
    }

    /**
     * Generates the regular expression for the number in the specific type of brackets.
     *
     * @param brackets        the type of brackets
     * @param afterExpression the expression that will be added at the end
     * @return the regular expression for the number in the brackets
     */
    private static String generateNumberPattern(final Brackets brackets, final String afterExpression) {
        return "\\" + (brackets.openingBracket)
                + Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN
                + "\\" +(brackets.closingBracket)
                + afterExpression;
    }

    /**
     * @param openingBracket the opening bracket
     * @param closingBracket the closing bracket
     */
    Brackets(final String openingBracket, final String closingBracket) {
        this.openingBracket = openingBracket;
        this.closingBracket = closingBracket;
    }

    /**
     * Returns the opening bracket.
     *
     * @return the opening brackets
     */
    public String getOpeningBracket() {
        return openingBracket;
    }

    /**
     * Returns the closing bracket.
     *
     * @return the closing bracket
     */
    public String getClosingBracket() {
        return closingBracket;
    }


    public static void main(String[] args) {
        System.out.println(generateNumberPattern(ROUND_BRACKETS, "|"));
    }
}
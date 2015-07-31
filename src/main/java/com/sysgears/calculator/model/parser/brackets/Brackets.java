package com.sysgears.calculator.model.parser.brackets;

import com.sysgears.calculator.model.parser.util.MathRegexCreator;

/**
 * The <code>Brackets</code> class provides a set of brackets.
 */
public enum Brackets {

    ROUND_BRACKETS("(", ")"),
    SQUARE_BRACKETS("[", "]"),
    CURLY_BRACKETS("{", "}"),
    ANGLE_BRACKETS("<", ">"),
    CUSTOM_BRACKETS("#", "$");

    /**
     * The opening bracket.
     */
    private String openingBracket;

    /**
     * The closing bracket.
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
            numberPattern.append(MathRegexCreator.generateNumberPattern(brackets[index]))
                    .append("|");
        }
        numberPattern.append(MathRegexCreator.generateNumberPattern(brackets[lastIndex]))
                .append(")?");

        StringBuilder bracketsPattern = new StringBuilder();
        bracketsPattern.append("(");
        for (int index = 0; index < lastIndex; ++index) {
            bracketsPattern.append(MathRegexCreator.generateBracketsPattern(brackets[index], numberPattern.toString()))
                    .append("|");
        }
        bracketsPattern.append(MathRegexCreator.generateBracketsPattern(brackets[lastIndex], numberPattern.toString()))
                .append(")");

        return bracketsPattern.toString();
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
}
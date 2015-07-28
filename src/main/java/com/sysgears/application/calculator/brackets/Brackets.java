package com.sysgears.application.calculator.brackets;

/**
 * Replacement class contains regex patterns for substring search and methods for their modification.
 */
public enum Brackets {

    ROUNDBRACKETS("(", ")"),
    SQUAREBRACKETS("[", "]"),
    CURLYBRACKETS("{", "}"),
    ANGLEBRACKETS("<", ">");

    /**
     * Operand.
     */
    private static final String OPERAND = "[\\+\\-]?[\\d]+([\\.][\\d]+)?";

    /**
     * Regular expression pattern.
     */
    private final String regex;

    /**
     * @param firstBracket  opening bracket
     * @param secondBracket closing bracket
     */
    private Brackets(final String firstBracket, final String secondBracket) {
        this.regex = "\\" + firstBracket + OPERAND + "\\" + secondBracket;
    }

    /**
     * Returns regular expression filter.
     *
     * @return regular expression
     */
    public String getRegex() {
        return regex;
    }
}

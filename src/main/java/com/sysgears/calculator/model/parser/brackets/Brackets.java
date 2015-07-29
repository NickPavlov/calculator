package com.sysgears.calculator.model.parser.brackets;

/**
 * The <code>Brackets</code> class provides a set of brackets.
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
     * Opening bracket.
     */
    private String openingBracket;

    /**
     * Closing bracket.
     */
    private String closingBracket;

    /**
     * Regular expression pattern.
     */
    private final String regex;

    /**
     * @param openingBracket  opening bracket
     * @param closingBracket closing bracket
     */
    private Brackets(final String openingBracket, final String closingBracket) {
        this.openingBracket = openingBracket;
        this.closingBracket = closingBracket;
        this.regex = "\\" + openingBracket + OPERAND + "\\" + closingBracket;
    }

    /**
     * Returns an opening bracket.
     *
     * @return opening brackets
     */
    public String getOpeningBracket() {
        return openingBracket;
    }

    /**
     * Returns a closing bracket.
     *
     * @return closing bracket
     */
    public String getClosingBracket() {
        return closingBracket;
    }

    /**
     * Returns regular expression filter.
     *
     * @return regular expression
     */
    public String getPattern() {
        return regex;
    }
}
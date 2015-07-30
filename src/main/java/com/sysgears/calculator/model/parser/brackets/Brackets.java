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
     * Opening bracket.
     */
    private String openingBracket;

    /**
     * Closing bracket.
     */
    private String closingBracket;

    /**
     * @param openingBracket an opening bracket
     * @param closingBracket a closing bracket
     */
    private Brackets(final String openingBracket, final String closingBracket) {
        this.openingBracket = openingBracket;
        this.closingBracket = closingBracket;
    }

    /**
     * Returns an opening bracket.
     *
     * @return an opening brackets
     */
    public String getOpeningBracket() {
        return openingBracket;
    }

    /**
     * Returns a closing bracket.
     *
     * @return a closing bracket
     */
    public String getClosingBracket() {
        return closingBracket;
    }
}
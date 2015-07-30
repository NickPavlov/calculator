package com.sysgears.calculator.model.parser.brackets;

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
     * Generates the regular expression for all opening brackets.
     *
     * @return the regular expression for all opening brackets
     */
    public static String generateOpeningBrackets() {
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
    public static String generateClosingBrackets() {
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
     * @param openingBracket an opening bracket
     * @param closingBracket a closing bracket
     */
    Brackets(final String openingBracket, final String closingBracket) {
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
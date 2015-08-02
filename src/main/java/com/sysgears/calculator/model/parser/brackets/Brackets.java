package com.sysgears.calculator.model.parser.brackets;

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
     * Returns a closing bracket which corresponds to a given opening bracket.
     *
     * @param openingBracket the opening bracket
     * @return the closing bracket
     */
    public static String getClosingPair(final String openingBracket) {
        String closingBracket = "";
        for (Brackets brackets : Brackets.values()) {
             if (brackets.openingBracket.equals(openingBracket)) {
                 closingBracket = brackets.closingBracket;
                 break;
             }
         }

        return closingBracket;
    }

    /**
     * Generates the regular expression for all opening brackets.
     *
     * @return the regular expression for all opening brackets
     */
    public static String generateOpeningPattern() {
        final StringBuilder openingBrackets = new StringBuilder();
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
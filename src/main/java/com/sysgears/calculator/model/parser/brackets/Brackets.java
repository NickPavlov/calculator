package com.sysgears.calculator.model.parser.brackets;

/**
 * The <code>Brackets</code> class provides a set of brackets.
 */
public enum Brackets {

    ROUND_BRACKETS('(', ')'),
    SQUARE_BRACKETS('[', ']'),
    CURLY_BRACKETS('{', '}'),
    ANGLE_BRACKETS('<', '>');
    /**
     * The opening bracket.
     */
    private char openingBracket;

    /**
     * The closing bracket.
     */
    private char closingBracket;

    /**
     * Returns a closing bracket which corresponds to a given opening bracket.
     * If there is no closing bracket - returns '\u0000'.
     *
     * @param openingBracket the opening bracket
     * @return the closing bracket
     */
    public static char getClosingPair(final char openingBracket) {
        char closingBracket = '\u0000';
        for (Brackets brackets : Brackets.values()) {
            if (brackets.openingBracket == openingBracket) {
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
        final StringBuilder openingBrackets = new StringBuilder().append("[");
        for (Brackets brackets : Brackets.values()) {
            openingBrackets.append("\\").append(brackets.getOpeningBracket());
        }

        return openingBrackets.append("]").toString();
    }

    /**
     * Generates the regular expression for all closing brackets.
     *
     * @return the regular expression for all closing brackets
     */
    public static String generateClosingPattern() {
        final StringBuilder closingBrackets = new StringBuilder().append("[");
        for (Brackets brackets : Brackets.values()) {
            closingBrackets.append("\\").append(brackets.getClosingBracket());
        }

        return closingBrackets.append("]").toString();
    }

    /**
     * @param openingBracket the opening bracket
     * @param closingBracket the closing bracket
     */
    Brackets(final char openingBracket, final char closingBracket) {
        this.openingBracket = openingBracket;
        this.closingBracket = closingBracket;
    }

    /**
     * Returns the opening bracket.
     *
     * @return the opening brackets
     */
    public char getOpeningBracket() {
        return openingBracket;
    }

    /**
     * Returns the closing bracket.
     *
     * @return the closing bracket
     */
    public char getClosingBracket() {
        return closingBracket;
    }
}
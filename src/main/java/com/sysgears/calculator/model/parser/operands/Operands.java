package com.sysgears.calculator.model.parser.operands;

import com.sysgears.calculator.model.parser.brackets.Brackets;

/**
 * The <code>Operands</code> class provides a set of operands.
 */
public enum Operands {

    FIRST {
        public String getPattern(final boolean enableSign) {
            StringBuilder closingBrackets = new StringBuilder();
            closingBrackets.append("[");
            for (Brackets brackets : Brackets.values()) {
                closingBrackets.append("\\")
                        .append(brackets.getClosingBracket());
            }
            closingBrackets.append("]");

            return "(" + generateWithBrackets(SIGN, REAL_NUMBER)
                    + (enableSign ? SIGN : "") + REAL_NUMBER + "(?!" + closingBrackets.toString() + "))";
        }
    },

    SECOND {
        public String getPattern(final boolean enableSign) {
            StringBuilder closingBrackets = new StringBuilder();
            closingBrackets.append("[");
            for (Brackets brackets : Brackets.values()) {

                closingBrackets.append("\\")
                        .append(brackets.getClosingBracket());
            }
            closingBrackets.append("]");

            return "(" + generateWithBrackets(SIGN, REAL_NUMBER)
                    + "(?!" + closingBrackets.toString() + ")" + (enableSign ? SIGN : "") + REAL_NUMBER + ")";
        }
    };

    /**
     * Regex pattern for the mathematical sign.
     */
    public static final String SIGN = "(?<!\\d+)[\\+-]?";

    /**
     * Regex pattern for the real number.
     */
    public static final String REAL_NUMBER = "\\d+(\\.\\d+)?";

    /**
     * Generates part of the operand with brackets.
     *
     * @param signPattern   a pattern for the mathematical sign
     * @param numberPattern a pattern for the number
     * @return a part with brackets
     */
    private static String generateWithBrackets(final String signPattern, final String numberPattern) {
        StringBuilder pattern = new StringBuilder();
        for (Brackets brackets : Brackets.values()) {
            pattern.append("\\")
                    .append(brackets.getOpeningBracket())
                    .append(SIGN)
                    .append(REAL_NUMBER)
                    .append("\\")
                    .append(brackets.getClosingBracket())
                    .append("|");
        }

        return pattern.toString();
    }

    /**
     * Returns a regex pattern for the operand.
     * <code>enableSign</code> parameter determines
     * whether to take account of the mathematical sign
     * of the operand if it is not taken into brackets.
     *
     * @param enableSign boolean
     * @return regex pattern for operand
     */
    public abstract String getPattern(final boolean enableSign);
}
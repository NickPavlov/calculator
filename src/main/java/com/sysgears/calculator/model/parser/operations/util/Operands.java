package com.sysgears.calculator.model.parser.operations.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;

/**
 * The <code>Operands</code> class provides a set of operands.
 */
public enum Operands {

    /**
     * The first operand.
     */
    FIRST {
        public String getPattern(final boolean enableSign) {
            return "(" + generateWithBrackets(SIGN_PATTERN, NUMBER_PATTERN)
                    + (enableSign ? SIGN_PATTERN : "") + NUMBER_PATTERN
                    + "(?!" + Brackets.generateClosingPattern() + "))";
        }
    },

    /**
     * The second operand.
     */
    SECOND {
        public String getPattern(final boolean enableSign) {
            return "(" + generateWithBrackets(SIGN_PATTERN, NUMBER_PATTERN)
                    + "(?!" + Brackets.generateOpeningPattern() + ")"
                    + (enableSign ? SIGN_PATTERN : "") + NUMBER_PATTERN + ")";
        }
    };

    /**
     * The regex pattern for a mathematical sign.
     */
    public static final String SIGN_PATTERN = "[\\+-]?";

    /**
     * The regex pattern for a number.
     */
    public static final String NUMBER_PATTERN = "\\d+(\\.\\d+)?";

    /**
     * Generates the part of the operand with brackets.
     *
     * @param signPattern   the pattern for the mathematical sign
     * @param numberPattern the pattern for the number
     * @return the part with brackets
     */
    private static String generateWithBrackets(final String signPattern, final String numberPattern) {
        StringBuilder pattern = new StringBuilder();
        for (Brackets brackets : Brackets.values()) {
            pattern.append("\\")
                    .append(brackets.getOpeningBracket())
                    .append(signPattern)
                    .append(numberPattern)
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
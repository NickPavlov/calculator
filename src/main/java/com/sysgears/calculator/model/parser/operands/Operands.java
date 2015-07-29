package com.sysgears.calculator.model.parser.operands;

import com.sysgears.calculator.model.parser.brackets.Brackets;

/**
 * The <code>Operands</code> class provides a set of operands.
 */
public enum Operands {

    FIRST{
        public String getPattern() {
            String pattern;
            for (Brackets brackets : Brackets.values()) {
                //brackets.getOpeningBracket()
                //brackets.getClosingBracket()
            }

            return "";
        }
    },

    SECOND{
        public String getPattern() {
            return "";
        }
    };

    /**
     * Regex pattern for mathematical sign.
     */
    private static final String SIGN = "[\\+-]?";

    /**
     * Regex pattern for real number.
     */
    private static final String REAL_NUMBER = "\\d+(\\.\\d+)?";

    /**
     * Generates a regex pattern for the first operand.
     *
     * @param brackets type of brackets
     * @return first operand pattern
     */
    private static String generateFirstOperand(final Brackets brackets) {
        return "";
    }

    /**
     * Generates a regex pattern for the second operand.
     *
     * @param brackets type of brackets
     * @return second operand pattern
     */
    private static String generateSecondOperand(final Brackets brackets) {
        return "";
    }

    public abstract String getPattern();
}
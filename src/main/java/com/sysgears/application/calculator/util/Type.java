package com.sysgears.application.calculator.util;

/**
 * The Type class provides a set of operation's types.
 */
public enum Type {

    BINARY {
        public String getSearchPattern(final String operator) {
            return "[\\+\\-]?" + OPERAND + "\\" + operator + "[\\+\\-]?" + OPERAND;
        }

        public String getSplitPattern(final String operator) {
            return "(?<=\\d)\\" + operator;
        }
    },

    UNARY {
        public String getSearchPattern(final String operator) {
            return operator + "[\\+\\-]?" + OPERAND;
        }

        public String getSplitPattern(final String operator) {
            return operator;
        }
    };

    /**
     * Operand pattern.
     */
    public final static String OPERAND = "([\\d]+([\\.][\\d]+)?)";

    /**
     * Must be overridden.
     *
     * @param operator string
     *
     * @return string
     */
    public abstract String getSearchPattern(final String operator);

    /**
     * Must be overridden.
     *
     * @param operator string
     *
     * @return string
     */
    public abstract String getSplitPattern(final String operator);
}

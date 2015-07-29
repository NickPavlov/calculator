package com.sysgears.application.parser.operations;

import java.util.regex.Pattern;

/**
 * The <code>Type</code> class provides a set of operation types.
 */
public enum Type {

    BINARY {
        public String getSearchPattern(final String operator) {
            return OPERAND + Pattern.quote(operator) + OPERAND;
        }

        public String getSplitPattern(final String operator) {
            return "(?<=\\d)" + Pattern.quote(operator);
        }
    },

    UNARY {
        public String getSearchPattern(final String operator) {
            return operator + OPERAND;
        }

        public String getSplitPattern(final String operator) {
            return operator;
        }
    };

    /**
     * Operand pattern.
     */
    public final static String OPERAND = "[\\+\\-]?([\\d]+([\\.][\\d]+)?)";

    /**
     * Should return a regex pattern to parse a single operation.
     *
     * @param operator string
     * @return string
     */
    public abstract String getSearchPattern(final String operator);

    /**
     * Should return a regex pattern to split a single operation by operator.
     *
     * @param operator string
     * @return string
     */
    public abstract String getSplitPattern(final String operator);
}
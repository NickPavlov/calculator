package com.sysgears.calculator.model.parser.operations.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;

import java.util.regex.Pattern;

/**
 * The <code>Type</code> class provides a set of operation types.
 */
public enum Type {

    /**
     * The binary operation.
     * If the operands are not in brackets, the mathematical signs are taken into account.
     */
    BINARY {
        public String getPattern(final String operator) {
            return OPERAND + Pattern.quote(operator) + OPERAND;
        }
    },

    /**
     * The unary operation.
     */
    UNARY {
        public String getPattern(final String operator) {
            return operator + OPERAND;
        }
    },

    /**
     * The constant.
     */
    CONSTANT {
        public String getPattern(final String operator) {
            return operator;
        }
    };

    /**
     * The regular expression for the operand.
     */
    public static final String OPERAND = "(?<![\\d\\)" + Brackets.OPENING_BRACKETS + "])" + "[\\+-]?\\d+(\\.\\d+)?";

    /**
     * Should return a regex pattern to getCommand a single operation.
     *
     * @param operator an operator
     * @return operation regex pattern
     */
    public abstract String getPattern(final String operator);
}
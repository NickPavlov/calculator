package com.sysgears.calculator.model.parser.operations;

import com.sysgears.calculator.model.parser.operands.Operands;

import java.util.regex.Pattern;

/**
 * The <code>Type</code> class provides a set of operation types.
 */
public enum Type {

    /**
     * Binary operation.
     * If the first operand is not in brackets, the mathematical sign is ignored.
     */
    BINARY_FIRST_WITHOUT_SIGN {
        public String getPattern(final String operator) {
            return generateBinaryPattern(operator, false, true);
        }
    },

    /**
     * Binary operation.
     * If the operands are not in brackets, the mathematical signs are taken into account.
     */
    BINARY {
        public String getPattern(final String operator) {
            return generateBinaryPattern(operator, true, true);
        }
    },

    UNARY {
        public String getPattern(final String operator) {
            return operator + Operands.SECOND.getPattern(true);
        }
    },

    CONSTANT {
        public String getPattern(final String operator) {
            return operator;
        }
    };

    /**
     * Generates the pattern for a binary operation.
     *
     * @param operator          the operator
     * @param firstOperandSign  if true, mathematical sign of the first operand is taken into account
     * @param secondOperandSign if true, mathematical sign of the second operand is taken into account
     * @return the pattern for a binary operation
     */
    private static String generateBinaryPattern(final String operator,
                                                final boolean firstOperandSign,
                                                final boolean secondOperandSign) {

        return Operands.FIRST.getPattern(firstOperandSign)
                + Pattern.quote(operator) + Operands.SECOND.getPattern(secondOperandSign);
    }

    /**
     * Should return a regex pattern to getCommand a single operation.
     *
     * @param operator an operator
     * @return operation regex pattern
     */
    public abstract String getPattern(final String operator);
}
package com.sysgears.calculator.model.parser.operations;

import java.util.regex.Pattern;

/**
 * The <code>Operations</code> class provides a basic set of operators required to operate the parser.
 */
public enum Operations {

    /**
     * The top priority operations.
     * Constants.
     */
    PI("PI", Priority.TOP, Type.CONSTANT) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return Math.PI;
        }
    },

    E("E", Priority.TOP, Type.CONSTANT) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return Math.E;
        }
    },

    /**
     * The high priority operations.
     */
    MODULO("mod", Priority.HIGH, Type.BINARY) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return firstOperand % secondOperand;
        }
    },

    SIN("sin", Priority.HIGH, Type.UNARY) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return Math.sin(secondOperand * Math.PI / 180);
        }
    },

    COS("cos", Priority.HIGH, Type.UNARY) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return Math.cos(secondOperand * Math.PI / 180);
        }
    },

    ABS("abs", Priority.HIGH, Type.UNARY) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return Math.abs(secondOperand);
        }
    },


    /**
     * The medium priority operations.
     */
    POWER("^", Priority.MEDIUM, Type.BINARY_FIRST_WITHOUT_SIGN) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return Math.pow(firstOperand, secondOperand);
        }
    },

    DIVISION("/", Priority.MEDIUM, Type.BINARY) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return firstOperand / secondOperand;
        }
    },

    MULTIPLY("*", Priority.MEDIUM, Type.BINARY) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return firstOperand * secondOperand;
        }
    },


    /**
     * The low priority operations.
     */

    ADD("+", Priority.LOW, Type.BINARY) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return firstOperand + secondOperand;
        }
    },

    SUBTRACT("-", Priority.LOW, Type.BINARY) {
        public double calculate(final double firstOperand, final double secondOperand) {
            return firstOperand - secondOperand;
        }
    };

    /**
     * Operator character.
     */
    private final String operator;

    /**
     * Operation priority.
     */
    private final int priority;

    /**
     * Operation regular expression.
     */
    private final String operationPattern;

    /**
     * Generates the regular expression pattern for all operators.
     *
     * @return the regular expression pattern for all operators
     */
    public static String generateOperatorsPattern() {
        final Operations[] operations = Operations.values();
        final int lastIndex = operations.length - 1;

        final StringBuilder operatorsPattern = new StringBuilder();
        operatorsPattern.append("(");
        for (int index = 0; index < lastIndex; ++index) {
            operatorsPattern.append(Pattern.quote(operations[index].getOperator())).append("|");
        }
        operatorsPattern.append(Pattern.quote(operations[lastIndex].getOperator())).append(")");

        return operatorsPattern.toString();
    }

    /**
     * @param operator the operator
     * @param priority the operation priority
     * @param type     the operation type
     */
    Operations(final String operator, final Priority priority, final Type type) {
        this.operator = operator;
        this.priority = priority.getIndex();
        this.operationPattern = type.getPattern(operator);
    }

    /**
     * Returns priority index of the operation.
     *
     * @return priority index
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns operator character.
     *
     * @return string
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Returns search filter for current operator.
     *
     * @return string
     */
    public String getOperationPattern() {
        return operationPattern;
    }

    /**
     * Should return the result of the operation.
     * Method is abstract and need to be overridden.
     *
     * @param firstOperand  The first operand
     * @param secondOperand The second operand
     * @return the calculated value
     */
    public abstract double calculate(final double firstOperand, final double secondOperand);
}
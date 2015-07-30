package com.sysgears.calculator.model.parser.operations;

/**
 * The <code>Operations</code> class provides a basic set of operators required to operate the parser.
 */
public enum Operations {

    MODULO("mod", Priority.TOP, Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand % secondOperand;
        }
    },

    SIN("sin", Priority.TOP, Type.UNARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return Math.sin(secondOperand * Math.PI / 180);
        }
    },

    COS("cos", Priority.TOP, Type.UNARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return Math.cos(secondOperand * Math.PI / 180);
        }
    },

    ABS("abs", Priority.TOP, Type.UNARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return Math.abs(secondOperand);
        }
    },

    POWER("^", Priority.TOP, Type.BINARY_FIRST_WITHOUT_SIGN) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return Math.pow(firstOperand, secondOperand);
        }
    },

    DIVISION("/", Priority.MEDIUM, Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand / secondOperand;
        }
    },

    MULTIPLY("*", Priority.MEDIUM, Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand * secondOperand;
        }
    },

    ADD("+", Priority.LOW, Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand + secondOperand;
        }
    },

    SUBTRACT("-", Priority.LOW, Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand - secondOperand;
        }
    },

    ;

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
     * @param operator operator string expression
     * @param priority operation priority
     * @param type     operation type
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
     * @return string
     */
    public abstract double evaluate(final double firstOperand, final double secondOperand);
}
package com.sysgears.application.parser.operations;

/**
 * The <code>Operations</code> class provides a basic set of operators required to operate the parser.
 */
public enum Operations {

    MODULO("mod", Priority.TOP, Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand % secondOperand;
        }
    },

    POWER("^", Priority.TOP, Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return Math.pow(firstOperand, secondOperand);
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

    SUBTRACT("-", Priority.LOW, Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand - secondOperand;
        }
    },

    ADD("+", Priority.LOW, Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand + secondOperand;
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
     * Search filter for a particular operator in a string and its operands.
     */
    private final String searchFilter;

    /**
     * Split pattern.
     */
    private final String splitPattern;

    /**
     * @param operator operator string expression
     * @param priority operation priority
     * @param type     operation type
     */
    private Operations(final String operator, final Priority priority, final Type type) {
        this.operator = operator;
        this.priority = priority.getIndex();
        this.searchFilter = type.getSearchPattern(operator);
        this.splitPattern = type.getSplitPattern(operator);
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
    public String getSearchParameter() {
        return searchFilter;
    }

    /**
     * Returns regex to split operator and operands.
     *
     * @return string
     */
    public String getSplitPattern() {
        return splitPattern;
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
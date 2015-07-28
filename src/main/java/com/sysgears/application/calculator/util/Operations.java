package com.sysgears.application.calculator.util;

/**
 * The <code>Operations</code> class provides a basic set of operators required to operate the calculator.
 */
public enum Operations {

    SIN(0, "sin", Type.UNARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return Math.sin(secondOperand * Math.PI / 180);
        }
    },

    COS(0, "cos", Type.UNARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return Math.cos(secondOperand * Math.PI / 180);
        }
    },

    ABS(0, "abs", Type.UNARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return Math.abs(secondOperand);
        }
    },

    DIVISION(1, "/", Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand / secondOperand;
        }
    },

    MULTIPLY(1, "*", Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand * secondOperand;
        }
    },

    SUBTRACT(2, "-", Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand - secondOperand;
        }
    },

    ADD(2, "+", Type.BINARY) {
        public double evaluate(final double firstOperand, final double secondOperand) {
            return firstOperand + secondOperand;
        }
    };

    /**
     * Operator character.
     */
    private final String operatorChar;

    /**
     * Operations priority.
     */
    private final int priority;

    /**
     * Search filter for a particular operator in a string and its operands.
     */
    private final String searchFilter;

    /**
     * Split pattern.
     */
    private final String splitType;

    /**
     * @param priority     operation priority
     * @param operatorChar operator character
     */
    private Operations(final int priority, final String operatorChar, final Type type) {
        this.priority = priority;
        this.operatorChar = operatorChar;
        this.searchFilter = type.getSearchPattern(operatorChar);
        this.splitType = type.getSplitPattern(operatorChar);
    }

    /**
     * Returns max priority of the operation.
     *
     * @return int
     */
    public static int getMaxPriorityValue() {
        int maxPriorityValue = 0;
        for (Operations o : Operations.values()) {
            if (o.getPriority() > maxPriorityValue) {
                maxPriorityValue = o.getPriority();
            }
        }

        return maxPriorityValue;
    }

    /**
     * Returns priority of the operation.
     *
     * @return int
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns operator character.
     *
     * @return string
     */
    public String getOperatorChar() {
        return operatorChar;
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
    public String getSplitType() {
        return splitType;
    }

    /**
     * Should return the result of the operation.
     * Method is abstract and need to be overridden.
     *
     * @param firstOperand The first operand
     * @param secondOperand The second operand
     *
     * @return string
     */
    public abstract double evaluate(final double firstOperand, final double secondOperand);
}
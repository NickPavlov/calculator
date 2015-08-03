package com.sysgears.calculator.model.parser.operations;

import com.sysgears.calculator.model.parser.operations.util.Priority;
import com.sysgears.calculator.model.parser.operations.util.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>Operations</code> class provides a basic set of operators required to operate the parser.
 */
public enum Operations {

    /**
     * The top priority operations.
     * Constants.
     */
    PI("PI", Priority.TOP, Type.CONSTANT) {
        //public double calculate(final double firstOperand, final double secondOperand) {
        public double calculate(final List<Double> operands) {
            return Math.PI;
        }
    },

    E("E", Priority.TOP, Type.CONSTANT) {
        public double calculate(final List<Double> operands) {
            return Math.E;
        }
    },


    /**
     * The high priority operations.
     */
    SIN("sin", Priority.HIGH, Type.UNARY) {
        public double calculate(final List<Double> operands) {
            return Math.sin(operands.get(0) * Math.PI / 180);
        }
    },

    COS("cos", Priority.HIGH, Type.UNARY) {
        public double calculate(final List<Double> operands) {
            return Math.cos(operands.get(0) * Math.PI / 180);
        }
    },

    COSH("cosh", Priority.HIGH, Type.UNARY) {
        public double calculate(final List<Double> operands) {
            return Math.cosh(operands.get(0) * Math.PI / 180);
        }
    },


    ATAN("atan", Priority.HIGH, Type.UNARY) {
        public double calculate(final List<Double> operands) {
            return Math.atan(operands.get(0) * Math.PI / 180);
        }
    },

    EXP("exp", Priority.HIGH, Type.UNARY) {
        public double calculate(final List<Double> operands) {
            return Math.exp(operands.get(0));
        }
    },

    ABS("abs", Priority.HIGH, Type.UNARY) {
        public double calculate(final List<Double> operands) {
            return Math.abs(operands.get(0));
        }
    },

    MODULO("mod", Priority.HIGH, Type.BINARY) {
        public double calculate(final List<Double> operands) {
            return operands.get(0) % operands.get(1);
        }
    },


    /**
     * The medium priority operations.
     */
    POWER("^", Priority.MEDIUM, Type.BINARY_FIRST_WITHOUT_SIGN) {
        public double calculate(final List<Double> operands) {
            return Math.pow(operands.get(0), operands.get(1));
        }
    },

    DIVISION("/", Priority.MEDIUM, Type.BINARY) {
        public double calculate(final List<Double> operands) {
            return operands.get(0) / operands.get(1);
        }
    },

    MULTIPLY("*", Priority.MEDIUM, Type.BINARY) {
        public double calculate(final List<Double> operands) {
            return operands.get(0) * operands.get(1);
        }
    },


    /**
     * The low priority operations.
     */

    ADD("+", Priority.LOW, Type.BINARY) {
        public double calculate(final List<Double> operands) {
            return operands.get(0) + operands.get(1);
        }
    },

    SUBTRACT("-", Priority.LOW, Type.BINARY) {
        public double calculate(final List<Double> operands) {
            return operands.get(0) - operands.get(1);
        }
    };

    /**
     * Operator character.
     */
    private final String operator;

    /**
     * Operation priority.
     */
    private final Priority priority;

    /**
     * Operation regular expression.
     */
    private final String operationPattern;

    /**
     * Returns the list of operations.
     *
     * @return the list of operations
     */
    public static List<String> getOperationsList() {
        final List<String> listOfCommands = new ArrayList<String>();
        for (Operations operation : Operations.values()) {
            listOfCommands.add("Name: " + operation + "; Operator: "
                    + operation.getOperator() + "; Priority: " + operation.getPriority() + ";");
        }

        return null;
    }

    /**
     * @param operator the operator
     * @param priority the operation priority
     * @param type     the operation type
     */
    Operations(final String operator, final Priority priority, final Type type) {
        this.operator = operator;
        this.priority = priority;
        this.operationPattern = type.getPattern(operator);
    }

    /**
     * Returns priority index of the operation.
     *
     * @return priority index
     */
    public Priority getPriority() {
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
     *
     * @param operands the list of operands
     * @return the calculated value
     */
    public abstract double calculate(final List<Double> operands);
}
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
        public double calculate(final List<Double> operands) {
            return Math.PI;
        }
    },

    E("E", Priority.TOP, Type.CONSTANT) {
        public double calculate(final List<Double> operands) {
            return Math.E;
        }
    },

    GOLDEN_SECTION("FI", Priority.TOP, Type.CONSTANT) {
        public double calculate(final List<Double> operands) {
            return 1.618033988;
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

    SINH("sinh", Priority.HIGH, Type.UNARY) {
        public double calculate(final List<Double> operands) {
            return Math.sinh(operands.get(0) * Math.PI / 180);
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

    /**
     * The medium priority operations.
     */
    POWER("^", Priority.MEDIUM, Type.BINARY) {
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
    SUBTRACT("-", Priority.LOW, Type.BINARY) {
        public double calculate(final List<Double> operands) {
            return operands.get(0) - operands.get(1);
        }
    },

    ADD("+", Priority.LOW, Type.BINARY) {
        public double calculate(final List<Double> operands) {
            return operands.get(0) + operands.get(1);
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
     * The operation type.
     */
    private final Type type;

    /**
     * Returns the list of operations.
     *
     * @return the list of operations
     */
    public static List<Operations> getOperations(final Type type) {
        final List<Operations> listOfOperations = new ArrayList<Operations>();
        for (Operations operation : Operations.values()) {
            if (operation.type == type) {
                listOfOperations.add(operation);
            }
        }

        return listOfOperations;
    }

    /**
     * @param operator the operator
     * @param priority the operation priority
     * @param type     the operation type
     */
    Operations(final String operator, final Priority priority, final Type type) {
        this.operator = operator;
        this.priority = priority;
        this.type = type;
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
     * Returns the operation type.
     *
     * @return the operation type
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns a string representation of the operation.
     *
     * @return a string
     */
    @Override
    public String toString() {
        return this.name() + ": operator='"
                + operator + '\'' + ", priority='" + priority.name() + '\'' + ", type='" + type.name() + "\'";
    }

    /**
     * Should return the result of the operation.
     *
     * @param operands the list of operands
     * @return the calculated value
     */
    public abstract double calculate(final List<Double> operands);
}
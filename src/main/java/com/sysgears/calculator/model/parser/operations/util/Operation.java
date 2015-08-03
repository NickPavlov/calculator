package com.sysgears.calculator.model.parser.operations.util;

/**
 * The <code>Operation</code> class provides functionality to work with operations.
 */
public class Operation {

    /**
     * The operation name.
     */
    private final String name;

    /**
     * The operator.
     */
    private final String operator;

    /**
     * The operation priority.
     */
    private final Priority priority;

    /**
     * Creates the <code>Operation</code> object, specified by name, operator and priority.
     *
     * @param name the operation name
     * @param operator the operator
     * @param priority the operation priority
     */
    public Operation(final String name, final String operator, final Priority priority) {
        this.name = name;
        this.operator = operator;
        this.priority = priority;
    }

    /**
     * Returns the operation name.
     *
     * @return the operation name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the operator.
     *
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Returns the operation priority.
     *
     * @return the operation priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns a string representation of the operation.
     *
     * @return a string
     */
    @Override
    public String toString() {
        return name + " { operator='" + operator + '\'' + ", priority='" + priority + '\'' +'}';
    }
}
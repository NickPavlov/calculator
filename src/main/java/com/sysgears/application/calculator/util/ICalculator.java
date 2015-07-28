package com.sysgears.application.calculator.util;

/**
 * The <code>ICalculator</code> interface defines the behavior of a calculator object.
 */
public interface ICalculator {

    /**
     * Should return the result of an operation given in <code>expression</code>.
     *
     * @param expression expression to calculate
     * @return result of an operation
     */
    public String calculate(final String expression);
}
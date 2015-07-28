package com.sysgears.application.calculator.util;

/**
 * ICalculator interface is the type of using calculator.
 */
public interface ICalculator {

    /**
     * Must be implemented.
     *
     * @param expression string
     *
     * @return string
     */
    public String calculate(final String expression);
}

package com.sysgears.calculator.model.parser;

/**
 * The <code>IMathParser</code> interface defines the behavior of a parser object.
 */
public interface IMathParser {

    /**
     * Should return the result of the mathematical expressions in <code>expression</code>.
     *
     * @param expression the mathematical expression to getCommand
     * @return parsed mathematical expression
     */
    public String parse(final String expression);
}
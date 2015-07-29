package com.sysgears.calculator.model.parser.util;

/**
 * The <code>IMathParser</code> interface defines the behavior of a parser object.
 */
public interface IMathParser {

    /**
     * Should return the result of an operation given in <code>expression</code>.
     *
     * @param expression expression to parse
     * @return result of an operation
     */
    public String parse(final String expression);
}
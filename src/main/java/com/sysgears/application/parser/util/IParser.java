package com.sysgears.application.parser.util;

/**
 * The <code>IParser</code> interface defines the behavior of a parser object.
 */
public interface IParser {

    /**
     * Should return the result of an operation given in <code>expression</code>.
     *
     * @param expression expression to parse
     * @return result of an operation
     */
    public String parse(final String expression);
}
package com.sysgears.calculator.model.parser.operations.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;

/**
 * The <code>Type</code> class provides a set of operation types.
 */
public enum Type {

    /**
     * The binary operation.
     */
    BINARY,

    /**
     * The unary operation.
     */
    UNARY,

    /**
     * The constant.
     */
    CONSTANT;

    /**
     * The regular expression for the operand.
     */
    public static final String OPERAND = "(?<![\\d"+ Brackets.OPENING_BRACKETS + "])" + "[\\+-]?\\d+(\\.\\d+)?";
}
package com.sysgears.calculator.model.converter;

import java.text.DecimalFormat;

/**
 * The <code>NumericalConverter</code> class provides the functionality to convert numbers.
 */
public class NumericalConverter {

    /**
     * Returns the number rounded up to formatType decimal places.
     *
     * @param number a double value
     * @return a rounded value as a string
     */
    public static String round(final double number, final String formatType) {
        return new DecimalFormat(formatType).format(number);
    }

    private NumericalConverter() {
    }
}
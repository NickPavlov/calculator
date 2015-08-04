package com.sysgears.calculator.model.util.strings;

/**
 * The <code>StringConverter</code> class provides functionality to convert strings.
 */
public class StringConverter {

    /**
     * Replaces the character at the specified position by the <code>replacement</code>;
     *
     * @param index       the index of the character that will be removed
     * @param string      the original string
     * @param replacement the replacement
     * @return the string without the character at the <code>index</code>
     */
    public static String replaceCharAt(final String string, final char replacement, final int index) {
        return (string.length() <= 1)
                ? "" + replacement
                : string.substring(0, index) + replacement + string.substring(index + 1);
    }
}
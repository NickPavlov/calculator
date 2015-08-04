package com.sysgears.calculator.model.util.strings;

/**
 * The <code>StringConverter</code> class provides functionality to convert strings.
 */
public class StringConverter {

    /**
     * Removes the character at the specified position.
     *
     * @param index       the index of the character that will be removed
     * @param string      the original string
     * @return the string without the character at the <code>index</code>
     */
    public static String removeCharAt(final String string, final int index) {
        return (string.length() <= 1)
                ? ""
                : string.substring(0, index) + string.substring(index + 1);
    }

    private StringConverter() {
    }
}
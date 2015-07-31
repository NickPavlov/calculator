package com.sysgears.calculator.model.converter.strings;

import java.util.Collection;

/**
 * The <code>PrettyStrings</code> class provides methods to generate strings.
 */
public class PrettyStrings {

    /**
     * Returns the separator with title.
     *
     * @param title         the separator title
     * @param separatorChar the symbol of which will be drawn up the separator
     * @return generated separator
     */
    public static String createSeparator(final String title, final char separatorChar, final int width) {
        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < Math.round(width - title.length()) / 2 - 1; ++i) {
            separator.append("-");
        }

        return separator + title + separator;
    }

    /**
     * Creates a string, based on the elements of the <code>collection</code>.
     *
     * @param collection a collection to print
     * @param before     a string which will be printed before each element
     * @param after      a string which will be printed after each element
     * @return a string
     */
    public static String createString(final Collection collection, final String before, final String after) {
        String result = "";
        for (Object value : collection) {
            result += before + value + after;
        }

        return result;
    }

    private PrettyStrings() {
    }
}
package com.sysgears.application.converter;

import java.util.Collection;

/**
 * The <code>PrettyText</code> class provides methods for the conversion of list items into the string.
 */
public class PrettyText {

    /**
     * Return string with a specified count of spaces.
     *
     * @param count number of spaces
     * @return string with spaces
     */
    public static String generateSpaces(final int count) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            spaces.append(" ");
        }

        return spaces.toString();
    }

    /**
     * Returns command line separator with title.
     *
     * @param title string
     * @return string
     */
    public static String createSeparator(final String title, final int width) {
        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < Math.round(width - title.length()) / 2 - 1; ++i) {
            separator.append("-");
        }

        return separator + title + separator;
    }

    /**
     * Creates a string, based on the elements of the <code>collection</code>.
     *
     * @param collection collection to print
     * @param before     a string which will be printed before each element
     * @param after      a string which will be printed after each element
     * @return string
     */
    public static String createString(final Collection collection, final String before, final String after) {
        String result = "";
        for (Object value : collection) {
            result += before + value + after;
        }

        return result;
    }

    private PrettyText() {
    }
}
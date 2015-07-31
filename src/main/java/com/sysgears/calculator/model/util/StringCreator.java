package com.sysgears.calculator.model.util;

import java.util.Collection;

/**
 * The <code>StringCreator</code> class provides methods to generate strings.
 */
public class StringCreator {

    /**
     * Creates a string based on elements of the <code>collection</code>.
     *
     * @param collection the collection to print
     * @param before     the string which will be printed before each element
     * @param after      the string which will be printed after each element
     * @return a string
     */
    public static String createFromCollection(final Collection collection, final String before, final String after) {
        String result = "";
        for (Object value : collection) {
            result += before + value + after;
        }

        return result;
    }

    private StringCreator() {
    }
}
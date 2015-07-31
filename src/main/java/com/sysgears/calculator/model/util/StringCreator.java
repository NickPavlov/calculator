package com.sysgears.calculator.model.util;

import java.util.Collection;

/**
 * The <code>StringCreator</code> class provides methods to generate strings.
 */
public class StringCreator {

    /**
     * Creates the string representation of elements of the <code>collection</code>.
     *
     * @param collection the collection to print
     * @param before     the string which will be printed before the each element
     * @param after      the string which will be printed after the each element
     * @return the string representation of elements
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
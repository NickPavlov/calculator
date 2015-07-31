package com.sysgears.calculator.model.converter.strings;

import java.util.Collection;

/**
 * The <code>StringCreator</code> class provides methods to generate strings.
 */
public class StringCreator {

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

    private StringCreator() {
    }
}
package com.sysgears.calculator.model.util.strings;

import java.util.Collection;

/**
 * The <code>StringCreator</code> class provides methods to create strings.
 */
public class StringCreator {

    /**
     * Creates the string representation of the <code>collection</code>.
     *
     * @param collection the collection to print
     * @param before     the string which will be printed before the each element
     * @param after      the string which will be printed after the each element
     * @return the string representation of the collection
     */
    public static String createFromCollection(final Collection collection, final String before, final String after) {
        StringBuilder result = new StringBuilder();
        for (Object object : collection) {
            result.append(before).append(object).append(after);
        }

        return result.toString();
    }

    private StringCreator() {
    }
}
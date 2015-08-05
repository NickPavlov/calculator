package com.sysgears.calculator.model.util.strings;

import java.util.Collection;

/**
 * The <code>StringCreator</code> class provides methods to create strings.
 */
public class StringCreator {

    /**
     * Creates the string representation of the <code>collection</code>.
     *
     * @param collection the collection to create string representation
     * @param before     the string which will be printed before the each element
     * @param after      the string which will be printed after the each element
     * @return the string representation of the collection
     */
    public static String createFromCollection(final Collection collection,
                                              final String before,
                                              final String after) {

        StringBuilder result = new StringBuilder();
        for (Object object : collection) {
            result.append(before).append(object).append(after);
        }

        return result.toString();
    }

    /**
     * Creates the string representation of the <code>collection</code>.
     * Each element will be represented as "(*) element\n".
     *
     * @param collection the collection to create string representation
     * @return the string representation of the collection
     */
    public static String createFromCollection(final Collection collection) {
        return createFromCollection(collection, "(*) ", "\n");
    }

    private StringCreator() {
    }
}
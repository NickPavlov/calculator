package com.sysgears.application.converter;

import java.util.List;

/**
 * The PrettyPrintingList class provides methods for the conversion of list items into the string.
 */
public class PrettyPrintingList {

    /**
     * Creates a string, based on the elements of the list.
     *
     * @param list list to print
     * @param separator a string which separates the elements of the list
     * @return string
     */
    public static String createString(final List list, final String separator) {
        String result = "";
        for (Object value : list) {
            result += value + separator;
        }

        return result;
    }
}
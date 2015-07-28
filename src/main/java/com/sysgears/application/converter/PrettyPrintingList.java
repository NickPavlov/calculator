package com.sysgears.application.converter;

import java.util.List;

/**
 * The PrettyPrintingList class provides methods for the conversion of list items into the string.
 */
public class PrettyPrintingList {

    /**
     * List which need to be printed.
     */
    private final List list;

    /**
     * Creates the <code>PrettyPrintingList</code> object.
     *
     * @param list list to print
     */
    public PrettyPrintingList(final List list) {
        this.list = list;
    }

    /**
     * Creates a string, based on the elements of the list.
     *
     * @param separator a string which separates the elements of the list
     * @return string
     */
    public String createString(final String separator) {
        String result = "";
        for (Object value : list) {
            result += value + separator;
        }

        return result;
    }
}
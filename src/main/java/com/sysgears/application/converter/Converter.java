package com.sysgears.application.converter;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>Converter</code> class provides methods for formatting the strings, the numbers.
 * Some methods in this class uses regular expressions to parse strings.
 */
public class Converter {

    /**
     * Returns maximum length.
     *
     * @param list list of strings
     * @return maximum length
     */
    /*
    public static int findMaxLength(final List<String> list) {
        int maxLength = 0;
        for (String string : list) {
            if (string.length() > maxLength) {
                maxLength = string.length();
            }
        }

        return maxLength;
    }
    */

    /**
     * Remove all spaces from the string.
     *
     * @param arg String
     * @return String without spaces
     */
    public static String removeSpaces(final String arg) {
        return arg.replaceAll(" ", "");
    }

    /**
     * Looking for a substring that matches the specified pattern.
     *
     * @param arg   string
     * @param regex regular expression
     * @return Matcher object
     */
    public static Matcher findSubstring(final String arg, final String regex) {
        return Pattern.compile(regex).matcher(arg);
    }

    /**
     * Returns true if string has a substring that matches the specified pattern.
     *
     * @param arg             string
     * @param regex           regular expression
     * @param caseInsensitive case-insensitive
     * @return boolean
     */
    public static boolean findString(final String arg, final String regex, final boolean caseInsensitive) {
        String tmp = caseInsensitive ? "(?i)" : "(?-i)";

        return Pattern.compile(tmp + regex).matcher(arg).find();
    }

    /**
     * Returns a number rounded up to formatType decimal places.
     *
     * @param number double
     * @return string
     */
    public static String round(final double number, final String formatType) {
        return new DecimalFormat(formatType).format(number);
    }

    /**
     * Generates a regex pattern based on a sequence of words.
     * The number of spaces between words is unlimited.
     *
     * @param arg a sequence of words
     * @return regular expression
     */
    public static String buildRegex(final String arg) {
        return "^(\\\\s)*" + arg.replaceAll(" ", "(\\\\s)+") + "(\\\\s)*$";
    }
}
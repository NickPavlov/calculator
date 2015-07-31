package com.sysgears.calculator.model.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>StringConverter</code> class provides methods for formatting the strings, the numbers.
 * Some methods in this class uses regular expressions to parse strings.
 */
public class StringConverter {

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
     * @param arg   a string
     * @param regex a regular expression
     * @return the Matcher object
     */
    public static Matcher findSubstring(final String arg, final String regex) {
        return Pattern.compile(regex).matcher(arg);
    }

    /**
     * Returns true if string has a substring that matches the specified pattern.
     *
     * @param str             a string
     * @param regex           a regular expression
     * @param caseInsensitive if true - case-insensitive
     * @return true if the substring is present in the <code>str</code>
     */
    public static boolean findString(final String str, final String regex, final boolean caseInsensitive) {
        String tmp = caseInsensitive ? "(?i)" : "(?-i)";

        return Pattern.compile(tmp + regex).matcher(str).find();
    }

    /**
     * Generates a regex pattern based on the <code>expression</code>.
     * The number of spaces between words is unlimited.
     *
     * @param expression a string
     * @return a regular expression
     */
    public static String buildRegex(final String expression) {
        return "^(\\\\s)*" + expression.replaceAll(" ", "(\\\\s)+") + "(\\\\s)*$";
    }

    private StringConverter() {
    }
}
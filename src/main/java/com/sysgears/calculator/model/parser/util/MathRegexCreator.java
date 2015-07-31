package com.sysgears.calculator.model.parser.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operands.Operands;
import com.sysgears.calculator.model.parser.operations.Operations;

/**
 * The <code>MathRegexCreator</code> class provides functionality to create
 */
public class MathRegexCreator {

    /**
     * Creates the regular expression for the mathematical expression in brackets.
     *
     * @param brackets      type of brackets
     * @param numberPattern the pattern of the single number in brackets
     * @return the regular expression for the mathematical expression in brackets
     */
    public static String createBracketsPattern(final Brackets brackets,
                                               final String numberPattern) {

        return "(?<=\\" + brackets.getOpeningBracket() + ")"
                + "([0-9]?"
                + Operations.generateOperatorsPattern()
                + "?"
                + numberPattern
                + ")+"
                + "(?=\\" + brackets.getClosingBracket() + ")";
    }

    /**
     * Create the regular expression for the number in the specific type of brackets.
     *
     * @param brackets the type of brackets
     * @return the regular expression for the number in the brackets
     */
    public static String createNumberPattern(final Brackets brackets) {
        return "\\" + (brackets.getOpeningBracket())
                + Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN
                + "\\" + (brackets.getClosingBracket());
    }
}

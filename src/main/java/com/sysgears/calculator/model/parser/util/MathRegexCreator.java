package com.sysgears.calculator.model.parser.util;

import com.sysgears.calculator.model.parser.brackets.Brackets;
import com.sysgears.calculator.model.parser.operands.Operands;
import com.sysgears.calculator.model.parser.operations.Operations;

/**
 * The <code>MathRegexCreator</code> class provides functionality to create
 */
public class MathRegexCreator {

    /**
     * @param brackets
     * @param numberPattern
     * @return
     */
    public static String generateBracketsPattern(final Brackets brackets,
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
     * Generates the regular expression for the number in the specific type of brackets.
     *
     * @param brackets the type of brackets
     * @return the regular expression for the number in the brackets
     */
    public static String generateNumberPattern(final Brackets brackets) {
        return "\\" + (brackets.getOpeningBracket())
                + Operands.SIGN_PATTERN + Operands.NUMBER_PATTERN
                + "\\" + (brackets.getClosingBracket());
    }
}

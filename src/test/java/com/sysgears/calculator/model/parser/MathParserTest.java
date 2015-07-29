package com.sysgears.calculator.model.parser;

import com.sysgears.calculator.model.parser.operands.Operands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathParserTest {

    public static void main(String[] args) {
        String expression = "sin(-5)";
        Matcher matcherExpression = Pattern.compile(Operands.SIGN + Operands.REAL_NUMBER).matcher(expression);
        List<String> listOfOperands = new ArrayList<String>();
        while (matcherExpression.find()) {
            listOfOperands.add(matcherExpression.group());
        }

        System.out.println(listOfOperands);
    }
}
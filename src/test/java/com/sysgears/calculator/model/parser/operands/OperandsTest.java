package com.sysgears.calculator.model.parser.operands;

public class OperandsTest {

    public static void main(String[] args) {
        System.out.println(Operands.FIRST.getPattern(false));
        System.out.println(Operands.SECOND.getPattern(true));
    }
}
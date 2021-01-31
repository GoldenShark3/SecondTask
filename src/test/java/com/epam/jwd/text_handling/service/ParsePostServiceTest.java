package com.epam.jwd.text_handling.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParsePostServiceTest {
    private ParsePostService parsePostService = ParsePostService.getInstance();

    @Test
    public void infixToPostfix() {
        String expression = "3+2*4";
        List<String> expectedPostfixExpression = new ArrayList<>();

        expectedPostfixExpression.add("3");
        expectedPostfixExpression.add("2");
        expectedPostfixExpression.add("4");
        expectedPostfixExpression.add("*");
        expectedPostfixExpression.add("+");

        assertEquals(expectedPostfixExpression, parsePostService.infixToPostfix(expression));

    }
}
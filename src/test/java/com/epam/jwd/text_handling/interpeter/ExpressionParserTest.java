package com.epam.jwd.text_handling.interpeter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExpressionParserTest {
    private ExpressionParser expressionParser = ExpressionParser.INSTANCE;

    @Test
    public void calculateExpression_receiveValueOfExpression_always() {
        String expression = "13<<2";
        int expectedResult = 52;
        int actualResult = expressionParser.calculateExpression(expression);

        assertEquals(expectedResult, actualResult);
    }
}
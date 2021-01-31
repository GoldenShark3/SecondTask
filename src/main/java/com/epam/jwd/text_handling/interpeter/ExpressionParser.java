package com.epam.jwd.text_handling.interpeter;

import com.epam.jwd.text_handling.service.ParsePostService;
import java.util.List;

public class ExpressionParser {
    private final ParsePostService PARSE_TO_POSTFIX_SERVICE = ParsePostService.getInstance();
    private final Context context = Context.getInstance();
    public static final ExpressionParser INSTANCE = new ExpressionParser();

    private ExpressionParser(){
    }

    private MathExpression parseToken(String token) {
        MathExpression expression;
        MathExpression leftNumber;
        MathExpression rightNumber;

        switch (token) {
            case "+":
                rightNumber = context.popValue();
                leftNumber = context.popValue();
                expression = MathExpression.plus(leftNumber, rightNumber);
                break;
            case "-":
                rightNumber = context.popValue();
                leftNumber = context.popValue();
                expression =  MathExpression.minus(leftNumber, rightNumber);
                break;
            case "*":
                rightNumber = context.popValue();
                leftNumber = context.popValue();
                expression =  MathExpression.multiply(leftNumber, rightNumber);
                break;
            case "/":
                rightNumber = context.popValue();
                leftNumber = context.popValue();
                expression =  MathExpression.divide(leftNumber, rightNumber);
                break;
            case "&":
                rightNumber = context.popValue();
                leftNumber = context.popValue();
                expression =  MathExpression.bitwiseAnd(leftNumber, rightNumber);
                break;
            case "|":
                rightNumber = context.popValue();
                leftNumber = context.popValue();
                expression =  MathExpression.bitwiseInclusiveOr(leftNumber, rightNumber);
                break;
            case "^":
                rightNumber = context.popValue();
                leftNumber = context.popValue();
                expression =  MathExpression.bitwiseExclusiveOr(leftNumber, rightNumber);
                break;
            case "<<":
                rightNumber = context.popValue();
                leftNumber = context.popValue();
                expression =  MathExpression.shiftLeft(leftNumber, rightNumber);
                break;
            case ">>":
                rightNumber = context.popValue();
                leftNumber = context.popValue();
                expression =  MathExpression.shiftRight(leftNumber, rightNumber);
                break;
            case "~":
                leftNumber = context.popValue();
                expression =  MathExpression.unaryNot(leftNumber);
                break;
            default:
                expression = MathExpression.number(Integer.parseInt(token));
        }
        return expression;
    }

    public int calculateExpression(String expression) {
        List<String> postfixExpression = PARSE_TO_POSTFIX_SERVICE.infixToPostfix(expression);
        for (String token : postfixExpression) {
            context.pushValue(parseToken(token));
        }
        return context.popValue().interpret(context);
    }
}

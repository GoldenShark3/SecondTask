package com.epam.jwd.text_handling.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParsePostService {
    private final Stack<String> OPERATORS_STACK = new Stack<>();
    private final List<String> POSTFIX_FORM = new ArrayList<>();
    private static ParsePostService instance;
    private final String OPERATOR_REGEX = "[+\\-*/^~&|]|[<]{2}|[>]{2}";

    private ParsePostService() {
    }

    public static ParsePostService getInstance() {
        if (instance == null) {
            instance = new ParsePostService();
        }
        return instance;
    }

    public List<String> infixToPostfix(String expression) {
        String infixForm = splitExpressionByOperators(expression);
        for (String token : infixForm.split("[ ]")) {
            if (token.matches("\\d+")) {
                POSTFIX_FORM.add(token);
            } else if (isOperator(token)) {
                addOperatorToStack(token);
            } else if (token.equals("(")) {
                OPERATORS_STACK.push(token);
            } else if (token.equals(")")) {
                while (!OPERATORS_STACK.peek().equals("(")) {
                    POSTFIX_FORM.add(OPERATORS_STACK.pop());
                }
                OPERATORS_STACK.pop();
            }
        }
        while (!OPERATORS_STACK.empty()) {
            POSTFIX_FORM.add(OPERATORS_STACK.pop());
        }
        return POSTFIX_FORM;
    }

    private int receivePriority(String operator) {
        switch (operator) {
            case "~":
                return 7;
            case "*":
            case "/":
                return 6;
            case "+":
            case "-":
                return 5;
            case "<<":
            case ">>":
            case ">>>":
                return 4;
            case "&":
                return 3;
            case "^":
                return 2;
            case "|":
                return 1;
            default:
                throw new IllegalArgumentException(operator + " - this operator does not exist ");
        }
    }

    private boolean isOperator(String token) {
        return token.matches(OPERATOR_REGEX);
    }

    private String splitExpressionByOperators(String expression) {
        StringBuilder splitExpression = new StringBuilder();
        int index = 0;
        while (index < expression.length()) {
            if (expression.charAt(index) == '<' || expression.charAt(index) == '>') {
                splitExpression.append(expression.charAt(index)).append(expression.charAt(++index)).append(" ");
                index++;
            } else if (Character.isDigit(expression.charAt(index))) {
                while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                    splitExpression.append(expression.charAt(index++));
                }
                splitExpression.append(" ");
            } else {
                splitExpression.append(expression.charAt(index)).append(" ");
                index++;
            }
        }
        return splitExpression.toString();
    }

    private void addOperatorToStack(String token) {
        if ((OPERATORS_STACK.empty()) || (OPERATORS_STACK.peek().equals("("))) {
            OPERATORS_STACK.push(token);
        } else if (receivePriority(token) > (receivePriority(OPERATORS_STACK.peek()))) {
            OPERATORS_STACK.push(token);
        } else {
            while (!OPERATORS_STACK.empty()
                    && (!OPERATORS_STACK.peek().equals("("))
                    && (receivePriority(token) <= receivePriority(OPERATORS_STACK.peek()))) {

                POSTFIX_FORM.add(OPERATORS_STACK.pop());
            }
            OPERATORS_STACK.push(token);
        }
    }
}

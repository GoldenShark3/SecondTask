package com.epam.jwd.text_handling.interpeter;

import java.util.Stack;

class Context {
    private final Stack<MathExpression> contextValues = new Stack<>();
    private static Context instance;

    private Context(){
    }

    static Context getInstance(){
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public MathExpression popValue() {
        return contextValues.pop();
    }

    public void pushValue(MathExpression expression) {
        contextValues.push(expression);
    }
}

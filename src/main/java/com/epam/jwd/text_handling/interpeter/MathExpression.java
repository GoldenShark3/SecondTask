package com.epam.jwd.text_handling.interpeter;

@FunctionalInterface
public interface MathExpression {

    int interpret(Context context);

    static MathExpression number(int number) {
        return context -> number;
    }

    static MathExpression plus(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    static MathExpression minus(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) - right.interpret(context);
    }

    static MathExpression multiply(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) * right.interpret(context);
    }

    static MathExpression divide(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) / right.interpret(context);
    }

    static MathExpression bitwiseAnd(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) & right.interpret(context);
    }

    static MathExpression bitwiseExclusiveOr(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) ^ right.interpret(context);
    }

    static MathExpression bitwiseInclusiveOr(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) | right.interpret(context);
    }

    static MathExpression shiftLeft(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) << right.interpret(context);
    }

    static MathExpression shiftRight(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) >> right.interpret(context);
    }

    static MathExpression unaryNot(MathExpression number) {
        return context -> ~number.interpret(context);
    }

}

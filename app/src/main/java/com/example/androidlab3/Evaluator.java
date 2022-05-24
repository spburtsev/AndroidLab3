package com.example.androidlab3;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {
    private final String expression;

    public Evaluator(String expr) {
        this.expression = expr;
    }

    public double evaluate() {
        List<ExpressionToken> tokenizedExpression = tokenized();

        if (tokenizedExpression.size() == 1) {
            return Double.parseDouble(tokenizedExpression.get(0).getToken());
        }
        return evaluateExpression(tokenizedExpression);
    }


    private double evaluateExpression(List<ExpressionToken> expression) {
        if (expression.size() == 1) {
            return Double.parseDouble(expression.get(0).getToken());
        }
        List<ExpressionToken> newExpression = new ArrayList<>();
        int mulIndex = indexOf(expression, TokenType.MUL);
        int divIndex = indexOf(expression, TokenType.DIV);

        int operationIndex = (mulIndex != -1 && divIndex != -1) ? Math.min(mulIndex, divIndex) : Math.max(mulIndex, divIndex);
        if (operationIndex != -1) {
            double left = Double.parseDouble(expression.get(operationIndex - 1).getToken());
            double right = Double.parseDouble(expression.get(operationIndex + 1).getToken());
            double ans = operationIndex == mulIndex ? (left * right) : (left / right);

            for (int i = 0; i < operationIndex - 1; ++i) {
                newExpression.add(expression.get(i));
            }
            newExpression.add(new ExpressionToken(String.valueOf(ans), TokenType.VALUE));
            for (int i = operationIndex + 2; i < expression.size(); ++i) {
                newExpression.add(expression.get(i));
            }
            return evaluateExpression(newExpression);
        } else {
            int addIndex = indexOf(expression, TokenType.ADD);
            int subIndex = indexOf(expression, TokenType.SUB);

            int nextOperationIndex = (addIndex != -1 && subIndex != -1) ? Math.min(addIndex, subIndex) : Math.max(addIndex, subIndex);
            if (nextOperationIndex != -1) {
                double left = Double.parseDouble(expression.get(nextOperationIndex - 1).getToken());
                double right = Double.parseDouble(expression.get(nextOperationIndex + 1).getToken());
                double ans = nextOperationIndex == addIndex ? (left + right) : (left - right);
                for (int i = 0; i < nextOperationIndex - 1; ++i) {
                    newExpression.add(expression.get(i));
                }
                newExpression.add(new ExpressionToken(ans + "", TokenType.VALUE));
                for (int i = nextOperationIndex + 2; i < expression.size(); ++i) {
                    newExpression.add(expression.get(i));
                }
                return evaluateExpression(newExpression);
            }
        }
        return -1.0;
    }

    private int indexOf(List<ExpressionToken> expression, TokenType type) {
        for (ExpressionToken token : expression) {
            if (token.getType() == type) {
                return expression.indexOf(token);
            }
        }
        return -1;
    }

    private List<ExpressionToken> tokenized() {
        StringBuilder value = new StringBuilder();
        List<ExpressionToken> tokens = new ArrayList<>();

        for (char c : expression.toCharArray()) {
            TokenType type = TokenType.fromChar(c);
            if (!(TokenType.VALUE).equals(type)) {
                if (value.length() > 0) {
                    ExpressionToken t = new ExpressionToken(value.toString(), TokenType.VALUE);
                    tokens.add(t);
                }
                value = new StringBuilder(String.valueOf(c));
                ExpressionToken t = new ExpressionToken(value.toString(), type);
                tokens.add(t);
                value = new StringBuilder();
            } else {
                value.append(c);
            }
        }
        if (value.length() > 0) {
            ExpressionToken t = new ExpressionToken(value.toString(), TokenType.VALUE);
            tokens.add(t);
        }
        return tokens;
    }
}

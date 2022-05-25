package com.example.androidlab3;

public class CalculatorState {
    private String expression;
    private String result;

    public CalculatorState() {
        setExpression("0");
    }

    public CalculatorState(String expression) {
        if (expression.charAt(0) == '-') {
            setExpression("0" + expression);
        } else {
            setExpression(expression);
        }
    }

    private void setExpression(String expression) {
        this.expression = expression;
        result = evaluateExpression(expression);
    }

    public String getExpression() {
        return expression;
    }

    public String getResult() {
        return result;
    }

    public void appendExpression(char value) {
        if (isNumeric(value)) {
            appendWithNumber(value);
        } else if (isOperator(value)) {
            appendWithOperator(value);
        } else if ('.' == value) {
            appendWithDecimalPoint();
        }
    }

    public void backspace() {
        String newExpr;
        if (expression.length() > 1) {
            newExpr = expression.substring(0, expression.length() - 1);
        } else {
            newExpr = "0";
        }
        setExpression(newExpr);
    }

    public void clear() {
        setExpression("0");
    }

    public boolean expressionEndsWithOperator() {
        return expression.charAt(expression.length() - 1) == '+'
                || expression.charAt(expression.length() - 1) == '-'
                || expression.charAt(expression.length() - 1) == '*'
                || expression.charAt(expression.length() - 1) == '/';
    }

    public boolean isZero(String value) {
        return "0".equals(value);
    }

    public boolean isNumeric(char value) {
        return String.valueOf(value).matches("[0-9]");
    }

    public boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    private void appendWithNumber(char number) {
        if (isZero(expression)) {
            setExpression(String.valueOf(number));
        } else {
            setExpression(expression + number);
        }
    }

    private void appendWithOperator(char operator) {
        if (expressionEndsWithOperator()) {
            expression = expression.substring(0, expression.length() - 1);
        }
        expression += operator;
    }

    private void appendWithDecimalPoint() {
        if (expression.contains(".")) {
            return;
        }
        if (expressionEndsWithOperator()) {
            expression += "0.";
        } else {
            expression += '.';
        }
    }

    private String evaluateExpression(String expression) {
        Evaluator evaluator = new Evaluator(expression);
        double evaluated = evaluator.evaluate();
        return String.valueOf(evaluated);
    }
}

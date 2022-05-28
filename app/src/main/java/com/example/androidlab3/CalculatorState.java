package com.example.androidlab3;

public class CalculatorState {
    private Expression expression;
    private String result;

    public CalculatorState() {
        setExpression("0");
    }

    public CalculatorState(String expression) {
        setExpression(expression);
    }

    private void setExpression(String expression) {
        this.expression = new Expression(expression);
        result = String.valueOf(this.expression.evaluate());
    }

    public String getExpression() {
        return expression.getStringified();
    }

    public String getResult() {
        return result;
    }

    public void appendExpression(char value) {
        expression.append(value);
        result = String.valueOf(expression.evaluate());
    }

    public void backspace() {
        expression.eraseLastChar();
        result = String.valueOf(expression.evaluate());
    }

    public void clear() {
        expression.erase();
        result = String.valueOf(expression.evaluate());
    }
}

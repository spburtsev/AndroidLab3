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
        result = getEvaluated();
    }

    public void backspace() {
        expression.eraseLastChar();
        result = getEvaluated();
    }

    public void clear() {
        expression.erase();
        result = getEvaluated();
    }

    private String getEvaluated() {
        return String.valueOf(expression.evaluate());
    }
}

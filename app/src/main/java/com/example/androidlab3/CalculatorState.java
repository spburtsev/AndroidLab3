package com.example.androidlab3;

public class CalculatorState {
    private String expression;
    private String result;

    public CalculatorState() {
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
        result = evaluateExpression();
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
        if (expression.length() > 1) {
            expression = expression.substring(0, expression.length() - 1);
        } else {
            expression = "0";
        }
        result = evaluateExpression();
    }

    public void clear() {
        expression = "0";
        result = "0";
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
            expression = String.valueOf(number);
        } else {
            expression += number;
        }
        result = evaluateExpression();
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

    private String evaluateExpression() {
        Evaluator evaluator = new Evaluator(expression);
        return String.valueOf(evaluator.evaluate());
//        String[] tokens = expression.split("[+\\-*/]");
//        char[] operators = expression.replaceAll("[0-9.]", "").toCharArray();
//        double result = Double.parseDouble(tokens[0]);
//
//        int loops = tokens.length - 1;
//
//        for (int i = 0; i < loops; ++i) {
//            switch (operators[i]) {
//                case '+':
//                    result += Double.parseDouble(tokens[i + 1]);
//                    break;
//                case '-':
//                    result -= Double.parseDouble(tokens[i + 1]);
//                    break;
//                case '*':
//                    result *= Double.parseDouble(tokens[i + 1]);
//                    break;
//                case '/':
//                    result /= Double.parseDouble(tokens[i + 1]);
//                    break;
//            }
//        }
//        return String.valueOf(result);
    }
}

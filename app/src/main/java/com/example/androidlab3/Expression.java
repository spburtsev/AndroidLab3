package com.example.androidlab3;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Expression {
    private String stringified;

    public Expression(String expr) {
        if (startsWithOperator(expr)) {
            stringified = "0" + expr;
        } else {
            stringified = expr;
        }
    }

    public String getStringified() {
        return stringified;
    }

    public List<ExpressionToken> getTokenized() {
        StringBuilder value = new StringBuilder();
        List<ExpressionToken> tokenized = new ArrayList<>();

        for (char c : stringified.toCharArray()) {
            TokenType type = TokenType.fromChar(c);
            if (!(TokenType.VALUE).equals(type)) {
                if (value.length() > 0) {
                    ExpressionToken t = new ExpressionToken(value.toString(), TokenType.VALUE);
                    tokenized.add(t);
                }
                value = new StringBuilder(String.valueOf(c));
                ExpressionToken t = new ExpressionToken(value.toString(), type);
                tokenized.add(t);
                value = new StringBuilder();
            } else {
                value.append(c);
            }
        }
        if (value.length() > 0) {
            ExpressionToken t = new ExpressionToken(value.toString(), TokenType.VALUE);
            tokenized.add(t);
        }
        return tokenized;
    }

    public void append(char value) {
        if (isNumeric(value)) {
            appendWithNumber(value);
        } else if (isOperator(value)) {
            appendWithOperator(value);
        } else if ('.' == value) {
            appendWithDecimalPoint();
        }
    }

    public void eraseLastChar() {
        if (stringified.length() > 1) {
            stringified = stringified.substring(0, stringified.length() - 1);
        } else {
            stringified = "0";
        }
    }

    public void erase() {
        stringified = "0";
    }

    public String evaluate() {
        Evaluator evaluator = new Evaluator(this);
        double evaluated = evaluator.evaluate();
        return String.valueOf(evaluated);
    }

    @NonNull
    @Override
    public String toString() {
        return stringified;
    }

    private void appendWithNumber(char number) {
        if (isZero(stringified)) {
            stringified = String.valueOf(number);
        } else {
            stringified += number;
        }
    }

    private void appendWithOperator(char operator) {
        if (endsWithOperator(stringified)) {
            stringified = stringified.substring(0, stringified.length() - 1);
        }
        stringified += operator;
    }

    private void appendWithDecimalPoint() {
        if (stringified.contains(".")) {
            return;
        }
        if (endsWithOperator(stringified)) {
            stringified += "0.";
        } else {
            stringified += '.';
        }
    }

    private static boolean isZero(String value) {
        return "0".equals(value);
    }

    private static boolean startsWithOperator(String expr) {
        return isOperator(expr.charAt(0));
    }

    private static boolean endsWithOperator(String expr) {
        return isOperator(expr.charAt(expr.length() - 1));
    }

    private static boolean isNumeric(char value) {
        return String.valueOf(value).matches("[0-9]");
    }

    private static boolean isOperator(char value) {
        return String.valueOf(value).matches("[+\\-*/]");
    }
}

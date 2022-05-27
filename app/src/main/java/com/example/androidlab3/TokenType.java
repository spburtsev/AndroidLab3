package com.example.androidlab3;

import androidx.annotation.NonNull;

public enum TokenType {
    ADD,
    SUB,
    MUL,
    DIV,
    VALUE;

    @NonNull
    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            case 4:
                return this.name();
            default:
                return "null";
        }
    }

    public static TokenType fromChar(char c) {
        switch (c) {
            case '+':
                return TokenType.ADD;
            case '-':
                return TokenType.SUB;
            case '*':
                return TokenType.MUL;
            case '/':
                return TokenType.DIV;
            default:
                return TokenType.VALUE;
        }
    }

}


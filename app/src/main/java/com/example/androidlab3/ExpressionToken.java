package com.example.androidlab3;

import androidx.annotation.NonNull;

public class ExpressionToken {
    private String token;
    private TokenType type;

    public ExpressionToken(String token, TokenType type) {
        this.token = token;
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "(Token:" + token + " of type " + type + ")";
    }

    public TokenType getType() {
        return type;
    }

    public String getToken() {
        return token;
    }
}

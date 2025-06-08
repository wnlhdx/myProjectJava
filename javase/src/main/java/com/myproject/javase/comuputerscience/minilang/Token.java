package com.myproject.javase.comuputerscience.minilang;

public class Token {
    TokenType type;
    String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String toString() {
        return type + "('" + value + "')";
    }
}


package com.myproject.javase.comuputerscience.minilang;

import java.util.*;

public class Lexer {
    private final String input;
    private int pos = 0;
    private static final Set<String> KEYWORDS = Set.of("let", "print");

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (Character.isWhitespace(c)) {
                pos++;
            } else if (Character.isDigit(c)) {
                tokens.add(number());
            } else if (Character.isLetter(c)) {
                tokens.add(identifier());
            } else {
                switch (c) {
                    case '+': tokens.add(new Token(TokenType.PLUS, "+")); break;
                    case '-': tokens.add(new Token(TokenType.MINUS, "-")); break;
                    case '*': tokens.add(new Token(TokenType.STAR, "*")); break;
                    case '/': tokens.add(new Token(TokenType.SLASH, "/")); break;
                    case '=': tokens.add(new Token(TokenType.ASSIGN, "=")); break;
                    case '(': tokens.add(new Token(TokenType.LPAREN, "(")); break;
                    case ')': tokens.add(new Token(TokenType.RPAREN, ")")); break;
                    case ';': tokens.add(new Token(TokenType.SEMICOLON, ";")); break;
                    default: throw new RuntimeException("Unexpected char: " + c);
                }
                pos++;
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    private Token number() {
        int start = pos;
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) pos++;
        return new Token(TokenType.NUMBER, input.substring(start, pos));
    }

    private Token identifier() {
        int start = pos;
        while (pos < input.length() && Character.isLetterOrDigit(input.charAt(pos))) pos++;
        String value = input.substring(start, pos);
        if (KEYWORDS.contains(value)) return new Token(TokenType.KEYWORD, value);
        return new Token(TokenType.IDENTIFIER, value);
    }
}


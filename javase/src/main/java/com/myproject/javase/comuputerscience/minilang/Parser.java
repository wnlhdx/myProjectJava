package com.myproject.javase.comuputerscience.minilang;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Statement> parse() {
        List<Statement> stmts = new ArrayList<>();
        while (!match(TokenType.EOF)) {
            stmts.add(statement());
        }
        return stmts;
    }

    private Statement statement() {
        if (match(TokenType.KEYWORD, "let")) {
            String name = consume(TokenType.IDENTIFIER).value;
            consume(TokenType.ASSIGN);
            Expression expr = expression();
            consume(TokenType.SEMICOLON);
            return new LetStatement(name, expr);
        } else if (match(TokenType.KEYWORD, "print")) {
            Expression expr = expression();
            consume(TokenType.SEMICOLON);
            return new PrintStatement(expr);
        }
        throw new RuntimeException("Invalid statement");
    }

    private Expression expression() {
        return addition();
    }

    private Expression addition() {
        Expression expr = multiplication();
        while (match(TokenType.PLUS) || match(TokenType.MINUS)) {
            String op = previous().value;
            Expression right = multiplication();
            expr = new BinaryExpr(op, expr, right);
        }
        return expr;
    }

    private Expression multiplication() {
        Expression expr = primary();
        while (match(TokenType.STAR) || match(TokenType.SLASH)) {
            String op = previous().value;
            Expression right = primary();
            expr = new BinaryExpr(op, expr, right);
        }
        return expr;
    }

    private Expression primary() {
        if (match(TokenType.NUMBER)) {
            return new NumberExpr(Integer.parseInt(previous().value));
        }
        if (match(TokenType.IDENTIFIER)) {
            return new VariableExpr(previous().value);
        }
        if (match(TokenType.LPAREN)) {
            Expression expr = expression();
            consume(TokenType.RPAREN);
            return expr;
        }
        throw new RuntimeException("Expected expression");
    }

    private boolean match(TokenType type) {
        if (check(type)) {
            pos++;
            return true;
        }
        return false;
    }

    private boolean match(TokenType type, String value) {
        if (check(type) && peek().value.equals(value)) {
            pos++;
            return true;
        }
        return false;
    }

    private Token consume(TokenType type) {
        if (check(type)) return tokens.get(pos++);
        throw new RuntimeException("Expected token: " + type);
    }

    private boolean check(TokenType type) {
        return peek().type == type;
    }

    private Token peek() {
        return tokens.get(pos);
    }

    private Token previous() {
        return tokens.get(pos - 1);
    }
}


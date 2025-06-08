package com.myproject.javase.comuputerscience.minilang;

public class PrintStatement implements Statement {
    final Expression expr;
    PrintStatement(Expression expr) {
        this.expr = expr;
    }
}

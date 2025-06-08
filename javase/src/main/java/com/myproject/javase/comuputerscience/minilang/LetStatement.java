package com.myproject.javase.comuputerscience.minilang;




public class LetStatement implements Statement {
    final String name;
    final Expression expr;
    LetStatement(String name, Expression expr) {
        this.name = name; this.expr = expr;
    }
}







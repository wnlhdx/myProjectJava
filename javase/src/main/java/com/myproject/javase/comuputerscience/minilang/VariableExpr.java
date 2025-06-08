package com.myproject.javase.comuputerscience.minilang;

public class VariableExpr implements Expression {
    final String name;
    VariableExpr(String name) { this.name = name; }
}

package com.myproject.javase.comuputerscience.minilang;

public class BinaryExpr implements Expression {
    final String op;
    final Expression left, right;
    BinaryExpr(String op, Expression left, Expression right) {
        this.op = op; this.left = left; this.right = right;
    }
}
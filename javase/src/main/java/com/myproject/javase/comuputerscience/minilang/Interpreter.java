package com.myproject.javase.comuputerscience.minilang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {
    private final Map<String, Integer> variables = new HashMap<>();

    public void run(List<Statement> stmts) {
        for (Statement stmt : stmts) {
            if (stmt instanceof LetStatement letStmt) {
                int value = eval(letStmt.expr);
                variables.put(letStmt.name, value);
            } else if (stmt instanceof PrintStatement printStmt) {
                int value = eval(printStmt.expr);
                System.out.println(value);
            }
        }
    }

    private int eval(Expression expr) {
        if (expr instanceof NumberExpr n) return n.value;
        if (expr instanceof VariableExpr v) {
            if (!variables.containsKey(v.name))
                throw new RuntimeException("Undefined variable: " + v.name);
            return variables.get(v.name);
        }
        if (expr instanceof BinaryExpr b) {
            int left = eval(b.left);
            int right = eval(b.right);
            return switch (b.op) {
                case "+" -> left + right;
                case "-" -> left - right;
                case "*" -> left * right;
                case "/" -> left / right;
                default -> throw new RuntimeException("Unknown operator: " + b.op);
            };
        }
        throw new RuntimeException("Unknown expression type");
    }
}


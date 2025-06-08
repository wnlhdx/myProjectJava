package com.myproject.javase.computerscience.minilang;


import com.myproject.javase.comuputerscience.minilang.*;

import java.util.List;

public class MiniLangTest {
    public static void main(String[] args) {
            String source = """
            let x = 3 + 4 * (2 - 1);
            let y = x * 2;
            print y + 1;
        """;

            Lexer lexer = new Lexer(source);
            List<Token> tokens = lexer.tokenize();

            Parser parser = new Parser(tokens);
            List<Statement> program = parser.parse();

            Interpreter interpreter = new Interpreter();
            interpreter.run(program);
        }
}

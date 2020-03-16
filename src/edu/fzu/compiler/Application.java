package edu.fzu.compiler;

import edu.fzu.compiler.lexer.Token;
import edu.fzu.compiler.lexer.Tokenizer;
import java.util.List;

/**
 * @author xjliang
 */
public class Application {

    private static Tokenizer tokenizer = new Tokenizer();

    public static void compile(String input) {
        try {
            List<Token> tokenList = tokenizer.parse(input);
            for (Token token : tokenList) {
                System.out.println(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.out.println(" === begin parse program");

        String input = "if (a>b) {a=b+3.0}";
        compile(input);

        System.out.println(" === end parse program");
    }
}

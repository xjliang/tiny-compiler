package edu.fzu.compiler.test;

import edu.fzu.compiler.lexer.Token;
import edu.fzu.compiler.lexer.Tokenizer;
import java.util.List;
import org.junit.Test;

/**
 * @author liang on 3/16/2020.
 * @version 1.0
 */
public class TokenizerTest {

    private void test(String input) throws Exception {
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokenList = tokenizer.parse(input);
        for (Token token : tokenList) {
            System.out.println(token);
        }
    }

    @Test
    public void test01() throws Exception {
        String input = "if (a>b) {a=b+30}";
        test(input);
    }

    @Test
    public void test02() throws Exception {
        String input = "if (a>b) {a=b+3.0}";
        test(input);
    }
}

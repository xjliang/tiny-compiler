package edu.fzu.compiler.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author xjliang
 */
public class Tokenizer {

    private static final HashSet<String> KEY_WORDS = new HashSet<>(
        Arrays.asList("void", "if", "while", "do", "break", "true",
            "false", "int", "float", "double", "bool", "char", "main", "static", "const"));

    /**
     * paren ( )
     */
    private static final String PAREN_TYPE = "paren";

    /**
     * 常数
     */
    private static final String NUMBER_TYPE = "number";

    /**
     * identifier 标识符
     */
    private static final String ID_TYPE = "id";

    /**
     * key word 基本保留字
     */
    private static final String KEY_TYPE = "key";
// TODO: other tokens
    /**
     * relation operator: > <
     */
    private static final String RELOP_TYPE = "relop";

    /**
     * operator 运算符
     */
    private static final String OP_TYPE = "op";

    /**
     * delimiter 分隔符  { }
     */
    private static final String DELIM_TYPE = "delim";

    /**
     * assignment 赋值 =
     */
    private static final String ASSIGN_TYPE = "assign";

    public List<Token> parse(String input) throws Exception {
        List<Token> tokenList = new ArrayList<>();
        int currentPos = 0;
        int length = input.length();
        while (currentPos < length) {
            char currentChar = input.charAt(currentPos);
            if (currentChar == '(' || currentChar == ')') {
                tokenList.add(new Token(PAREN_TYPE, currentChar + ""));
                currentPos++;
            } else if (Character.isWhitespace(currentChar)) {
                // skip white space
                currentPos++;
            } else if (isOperator(currentChar)) {
                tokenList.add(new Token(OP_TYPE, currentChar+""));
                currentPos++;
            } else if (isReOperator(currentChar)) {
                tokenList.add(new Token(RELOP_TYPE, currentChar+""));
                currentPos++;
            } else if (isDelimiter(currentChar)) {
                tokenList.add(new Token(DELIM_TYPE, currentChar+""));
                currentPos++;
            } else if (isAssignment(currentChar)) {
                tokenList.add(new Token(ASSIGN_TYPE, currentChar+""));
                currentPos++;
            } else if (Character.isDigit(currentChar)) {
                StringBuilder stringBuilder = new StringBuilder();
                while (Character.isDigit(currentChar)) {
                    stringBuilder.append(currentChar);
                    currentPos++;
                    currentChar = input.charAt(currentPos);
                }
                tokenList.add(new Token(NUMBER_TYPE, stringBuilder.toString()));
            } else if (isAlpha(currentChar)) {
                StringBuilder stringBuilder = new StringBuilder();
                while (isAlpha(currentChar)) {
                    stringBuilder.append(currentChar);
                    currentPos++;
                    currentChar = input.charAt(currentPos);
                }
                String token = stringBuilder.toString();
                if (KEY_WORDS.contains(token)) {
                    tokenList.add(new Token(KEY_TYPE, token));
                } else {
                    tokenList.add(new Token(ID_TYPE, token));
                }
            } else {
                throw new Exception("unknown token: " + currentChar);
            }
        }
        return tokenList;
    }

    private boolean isReOperator(char ch) {
        return (ch == '>' || ch == '<');
    }

    private boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }

    private boolean isDelimiter(char ch) {
        return (ch == '{' || ch == '}');
    }

    private boolean isAssignment(char ch) {
        return ch == '=';
    }

    private boolean isAlpha(char currentChar) {
        return (currentChar >= 'A' && currentChar <= 'Z')
            || (currentChar >= 'a' && currentChar <= 'z');
    }
}

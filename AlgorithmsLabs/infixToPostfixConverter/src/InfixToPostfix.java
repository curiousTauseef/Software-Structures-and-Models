
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class InfixToPostfix {

    public static final Pattern NUM_PATTERN = Pattern.compile("\\d");
    public static final Pattern PRIORITY_OPERATOR_PATTERN = Pattern.compile("(\\*|/)");
    public static final Pattern NON_PRIORITY_OPERATOR_PATTERN = Pattern.compile("(\\+|-)");  // pattern = Pattern.compile("[az]$", Pattern.MULTILINE | Pattern.UNIX_LINES);

    private SimpleStack<Character> operatorStack;
    private String infixEquation;
    private String postfixEquation = "";

    public InfixToPostfix(String in) {
        infixEquation = in;
        operatorStack = new SimpleStack();
    }
    
    public String convert() {
        for (int index = 0; index < infixEquation.length(); index++) {
            // Convert char to string
            String aChar = "" + infixEquation.charAt(index);
            // System.out.println(aChar);
            if (NON_PRIORITY_OPERATOR_PATTERN.matcher(aChar).find()) {
                // System.out.println(aChar + " is non-priority operator");
                encounterOperator(aChar, 1);
            } else if (PRIORITY_OPERATOR_PATTERN.matcher(aChar).find()) {
                // System.out.println(aChar + " is priority operator");
                encounterOperator(aChar, 2);
            } else if (aChar.equals("(")) {
                // System.out.println(aChar + " is encountered");
                operatorStack.push(aChar.toCharArray()[0]);
            } else if (aChar.equals(")")) {
                // System.out.println(aChar + " is encountered");
                encounterClosingParenthesis();
            } else if (NUM_PATTERN.matcher(aChar).find()) {
                postfixEquation += aChar;
                // System.out.println(aChar + " is Number");
            }
        }
        Character c = operatorStack.pop();
        while (c != null) {
            postfixEquation += c;
            c = operatorStack.pop();
        }
        return postfixEquation;
    }
    
    public void encounterOperator(String thisOperator, int prec1) {
        Character topChar = operatorStack.pop();
        while (topChar != null) {
            if (topChar == '(') {
                operatorStack.push(topChar);
                break;
            } else {
                int prec2;
                if (NON_PRIORITY_OPERATOR_PATTERN.matcher("" + topChar).find()) {
                    prec2 = 1;
                } else {
                    prec2 = 2;
                }
                if (prec2 < prec1) {
                    operatorStack.push(topChar);
                    break;
                } else {
                    postfixEquation = postfixEquation + topChar;
                }
            }
            topChar = operatorStack.pop();
        }
        operatorStack.push(thisOperator.toCharArray()[0]);
    }

    public void encounterClosingParenthesis() {
        Character topChar = operatorStack.pop();
        while (topChar != null) {
            if (topChar == '(') {
                break;
            } else {
                postfixEquation = postfixEquation + topChar;
            }
            topChar = operatorStack.pop();
        }
    }
}

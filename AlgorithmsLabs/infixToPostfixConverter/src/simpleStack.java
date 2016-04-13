
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleStack<T extends Comparable<T>> {

    private static final int MAXN = 10;
    private int top;
    private T[] array;

    public SimpleStack() {
        top = -1;
        array = (T[]) new Comparable[MAXN];
    }

    public boolean push(T item) {
        if (top >= MAXN - 1) {
            return false;
        } else {
            array[++top] = item;
        }
        return true;
    }

    public T pop() {
        if (top == -1) {
            return null;
        } else {
            return array[top--];
        }
    }

    public void print() {
        for (int i = top; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println("Infix to postfix converter:\n");
        System.out.println("Give an infix equation at one line");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        InfixToPostfix infixToPostfix = new InfixToPostfix(input);
        String output = infixToPostfix.convert();
        System.out.println("Equivalent postfix equation is " + output);
        RPN_Calculator rpnCal = new RPN_Calculator();
        System.out.println("And it's value is " + rpnCal.evaluatepostfix(output));
    }

//    public static void main(String[] args) {
//        SimpleStack<Character> operatorStack = new SimpleStack();
//
//        String infixEquation = "";
//        String postfixEquation = "";
//
//        System.out.println("Infix to postfix converter:\n");
//        System.out.println("Give an infix equation at one line");
//        Scanner scanner = new Scanner(System.in);
//        Pattern NUM_PATTERN = Pattern.compile("\\d");
//        Pattern PRIORITY_OPERATOR_PATTERN = Pattern.compile("(\\*|/)");
//        Pattern NON_PRIORITY_OPERATOR_PATTERN = Pattern.compile("(\\+|-)");  // pattern = Pattern.compile("[az]$", Pattern.MULTILINE | Pattern.UNIX_LINES);
//
//        infixEquation = scanner.nextLine();
//        for (int index = 0; index < infixEquation.length(); index++) {
//            // Convert char to string
//            String aChar = "" + infixEquation.charAt(index);
//            System.out.println(aChar);
//            if (NUM_PATTERN.matcher(aChar).find()) {
//                postfixEquation += aChar;
//                System.out.println(aChar + " is Number");
//            } else if (PRIORITY_OPERATOR_PATTERN.matcher(aChar).find()) {
//                System.out.println(aChar + " is priority operator");
//                operatorStack.push(aChar.toCharArray()[0]);
//            } else if (NON_PRIORITY_OPERATOR_PATTERN.matcher(aChar).find()) {
//                Character c = operatorStack.pop();
//                
//                while (c != null) {
//                    if (PRIORITY_OPERATOR_PATTERN.matcher(""+c).find()){
//                        postfixEquation += c;
//                        c = operatorStack.pop();
//                    } else {
//                        operatorStack.push(c);
//                        break;
//                    }
//                }
//                System.out.println(aChar + " is non-priority operator");
//                operatorStack.push(aChar.toCharArray()[0]);
//            }
//        }
//        Character c = operatorStack.pop();
//        while (c != null) {
//            postfixEquation += c;
//            c = operatorStack.pop();
//        }
//        System.out.println("Equivalent postfix equation is " + postfixEquation);
//    }
}

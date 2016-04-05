
import java.util.Scanner;


public class simpleStack<T extends Comparable<T>> {

    private static final int MAXN = 10;
    private int top;
    private T[] array;

    public simpleStack() {
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
        simpleStack<Character> operandStack = new simpleStack();
        simpleStack<Character> operatorStack = new simpleStack();
        Character item;
        String infixEquation = "";

        System.out.println("Infix to postfix converter:\n");
        System.out.println("Give an infix equation at one line");
        Scanner scanner = new Scanner(System.in);
        try {
            infixEquation = scanner.nextLine();
            for (int index = 0; index < string1.length();
index++) {
     char aChar = string1.charAt(index);
}
            // repeat until Q is entered
            while (item.compareTo(new Character('Q')) != 0) {
                if (item.compareTo(new Character('+')) == 0
                        || item.compareTo(new Character('-')) == 0) {
                    operatorStack.push(item);
                    System.out.println("operator pushed");
                    // operatorStack.print();
                } else if (item.compareTo(new Character('=')) == 0) {
                    int result = 0;
                    if (operatorStack.pop().compareTo(new Character('+')) == 0) {
                        result = (Integer.parseInt(operandStack.pop().toString())
                                + Integer.parseInt(operandStack.pop().toString()));
                    } else if (operatorStack.pop().compareTo(new Character('-')) == 0) {
                        result = (Integer.parseInt(operandStack.pop().toString())
                                - Integer.parseInt(operandStack.pop().toString()));
                    }
                    System.out.println("top value is " + result);
                    operandStack.push(new Character((char) result));
                } else if (item.compareTo(new Character('\n')) == 0){
                } else {
                    operandStack.push(item);
                    System.out.println("operand pushed");
                    // operandStack.print();
                }
                item = new Character((char) System.in.read());
            }
            System.out.print("stack contained: ");
            operandStack.print();
            System.out.println();
            System.out.println();
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }
}


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
        // simpleStack<Character> operatorStack = new simpleStack();
        Character item;

        System.out.println("This is a RPN calculator. Enter operands and operators:\n");
        try {
            item = new Character((char) System.in.read());
            // repeat until Q is entered
            while (item.compareTo(new Character('Q')) != 0) {
                if (item.compareTo(new Character('+')) == 0){
                    int result = (Integer.parseInt(operandStack.pop().toString())
                                + Integer.parseInt(operandStack.pop().toString()));
                    Character c = new Character((char) (result+'0'));
                    operandStack.push(c);
                } else if (item.compareTo(new Character('-')) == 0){
                    int result = (Integer.parseInt(operandStack.pop().toString())
                                - Integer.parseInt(operandStack.pop().toString()));
                    Character c = new Character((char) (result+'0'));
                    operandStack.push(c);
                } else if (item.compareTo(new Character('=')) == 0) {
                    Character result = operandStack.pop();
                    System.out.println("top value is " + result);
                    operandStack.push(result);
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

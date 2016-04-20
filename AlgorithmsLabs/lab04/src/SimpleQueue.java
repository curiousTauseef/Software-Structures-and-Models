
public class SimpleQueue<T extends Comparable<T>> {

    private static int MAXN = 5;
    private int number_of_items;
    private T[] array;

    public SimpleQueue() {
        number_of_items = 0;
        array = (T[]) new Comparable[MAXN];
    }

    public boolean enqueue(T item) {
        if (number_of_items >= MAXN) {
            MAXN += 5;
            T[] newArray = (T[]) new Comparable[MAXN];
            for (int i = 0; i < number_of_items; i++) {
                // System.out.println(array[i]);
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[number_of_items++] = item;
        System.out.println("size is increased and is now " + number_of_items + " items and max capacity is " + MAXN);
        return true;
    }

    public T dequeue() {
        if (number_of_items == 0) {
            return null;
        } else {
            T item = array[0];
            for (int i = 0; i < number_of_items - 1; i++) {
                array[i] = array[i + 1];
            }
            number_of_items--;
            return item;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < number_of_items; i++) {
            s.append(array[i] + " ");
        }

        return s.toString();
    }

private static final String testStr = "metropolia\n";
// private static final String testStr = "asdfghjklzxcvbnmqwertyuiop111111111111asdfg1111111136836836861111111111365969694697976111111111\n";

    public static void main(String[] args) {
        SimpleQueue<Character> queue = new SimpleQueue<>();
        Character item;

        System.out.println("Enter a letter to push onto stack");
        System.out.println("or digit 1 to dequeue a letter");
        System.out.println("Return to end the program\n");
        long tic = System.currentTimeMillis();
        try {
            int i = 0;
            item = (char) testStr.charAt(i);
            while (item.compareTo('\n') != 0) {
                if (item.compareTo('1') == 0) {
                    System.out.println("A letter dequeued " + queue.dequeue());
                } else {
                    queue.enqueue(item);
                }
                System.out.println("Queue content: [" + queue + "]");
                item = (char) testStr.charAt(++i);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        long tac = System.currentTimeMillis();
        long elapsedTime = tac - tic;
        System.out.println("SQ, Time took " + elapsedTime + " ms");
    }
}

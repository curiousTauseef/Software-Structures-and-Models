
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CircularBufferQueue<T extends Comparable<T>> {

    private int MAXN = 5;
    private int first, last, number_of_items;
    private T[] array;

    public CircularBufferQueue() {
        first = number_of_items = 0;
        last = -1;
        array = (T[]) new Comparable[MAXN];
    }

    public boolean enqueue(T item) {
        if (number_of_items >= MAXN) {
            T[] newArray = (T[]) new Comparable[MAXN + 5];
            last = first - 1;
            for (int i = 0; i < number_of_items; i++) {
                // System.out.println(array[i]);
                newArray[(i + first) % (MAXN + 5)] = array[(i + first) % MAXN]; // remainder is not as efficient as addition, subtraction
                last++;
            }
            MAXN += 5;
            array = newArray;
        }
        if (++last > MAXN - 1) {
            last = 0;
        }
        array[last] = item;
        number_of_items++;
        System.out.println("size is increased and is now " + number_of_items + " items and max capacity is " + MAXN);
        return true;
    }

    public T dequeue() {
        if (number_of_items == 0) {
            return null;
        } else {
            T item = array[first++];
            if (first > MAXN - 1) {
                first = 0;
            }
            number_of_items--;
            return item;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < number_of_items; i++) {
            s.append(array[(i + first) % MAXN]).append(" ");
        }
        return s.toString();
    }

    private static final String testStr = "asdfghjklzxcvbnmqwertyuiop111111111111asdfg1111111136836836861111111111365969694697976111111111\n";

    public static void main(String[] args) {
        CircularBufferQueue<Character> queue = new CircularBufferQueue();
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
        System.out.println("CQ, Time took " + elapsedTime + " ms");
    }
}

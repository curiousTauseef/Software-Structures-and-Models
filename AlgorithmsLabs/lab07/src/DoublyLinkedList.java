
import java.util.Scanner;

public class DoublyLinkedList<T extends Comparable<T>> {

    private Node first;
    private Node last;

    public DoublyLinkedList() {
        initialize_dbl();
    }

    private void initialize_dbl() {
        first = null;
        last = null;
    }

    public void insert_to_front(T item) {
        first = new Node(item, first, null);
        if (first.next != null) {
            first.next.prev = first;
        }
        if (last == null) {
            last = first;
        }
    }

//    void delete_first() {
//        if (first != null) {
//            first = first.next;
//        }
//    }
//
//    void delete_last() {
//        if (first == null) {
//            return;
//        }
//        Node node = first;
//        while (node.next != null) {
//            node = node.next;
//        }
//        node = null;
//    }
    public void print() {
        Node traverse = first;
        System.out.print("List: ");
        while (traverse != null) {
            System.out.print(traverse.data+" ");
            traverse = traverse.next;
        }
        System.out.println("");
    }

    public void print_reverse() {
        Node traverse = last;
        System.out.print("List: ");
        while (traverse != null) {
            System.out.print(traverse.data+" ");
            traverse = traverse.prev;
        }
        System.out.println("");
    }

    public int find_pos_in_list(T item) {
        Node node = first;
        int i = 0;
        while (node.data != item) {
            node = node.next;
            i++;
        }
        return i;
    }

    public void insert_to_back(T item) {
        last = new Node(item, null, last);
        if (last.prev != null)
            last.prev.next = last;
        if (first == null) {
            first = last;
        }
    }

    private class Node {

        private T data;
        private Node next, prev;

        public Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node p = first;
        while (p != null) {
            s.append(p.data + " ");
            p = p.next;
        }

        return s.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> dbl
                = new DoublyLinkedList<Integer>();
        int order_no;
        char to_be_searched;
        dbl.print();
        dbl.print_reverse();
        dbl.insert_to_back(10);
        dbl.print();
        dbl.print_reverse();
        dbl.insert_to_front(20);
        dbl.print();
        dbl.print_reverse();
        dbl.insert_to_back(30);
        dbl.print();
        dbl.print_reverse();
        dbl.insert_to_front(40);
        dbl.print();
        dbl.print_reverse();
        dbl.insert_to_back(50);
        dbl.print();
        dbl.print_reverse();
    }
}

public class LinkedList<T extends Comparable<T>> {
    private Node first;
    private Node last;

    public LinkedList() {
        first = null;
        last = null;
    }

    public void add(T item) {
        if (first != null) {
            Node prev = last;
            last = new Node(item, null);
            prev.next = last;
        }
        else {
            last = new Node(item, null);
            first = last;
        }
    }

    private class Node {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override public String toString() {
        StringBuilder s = new StringBuilder();
        Node p = first;
        while (p != null) {
            s.append(p.data + " ");
            p = p.next;
        }
        
        return s.toString();
    }

    public static void main(String[] args) {
        LinkedList<Character> list = new LinkedList<Character>();
        
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
        
        System.out.println("List: " + list);
    }
}

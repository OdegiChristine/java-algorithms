import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

// This class implements a custom deque data type using a doubly linked list
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }

    }
    // construct an empty deque
    public Deque(){
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return head == null;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null){
            throw new IllegalArgumentException("Argument cannot be null");
        }
        Node<Item> newNode = new Node<>(item);
        if (isEmpty()){
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException("Argument cannot be null");
        }

        Node<Item> newNode = new Node<>(item);
        if (isEmpty()){
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException("The deque is empty.");
        }
        Item first = head.data;
        head = head.next;
        if (head != null){
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return first;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException("The deque is empty.");
        }
        Item last = tail.data;
        tail = tail.prev;
        if (tail != null){
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return last;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    // Inner class for the deque iterator
    private class DequeIterator implements Iterator<Item>{
        private Node<Item> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item data = current.data;
            current = current.next;
            return data;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(9);
        deque.addLast(0);
        deque.addLast(15);
        deque.addFirst(2);
        deque.addLast(7);

        //Print deque elements
        StdOut.println("Deque elements: ");
        for (int num : deque) {
            StdOut.printf(num + " ");
        }
        StdOut.println();

        deque.removeFirst();
        deque.removeLast();

        StdOut.println("Deque after removing first and last elements:");
        for (int num : deque) {
            StdOut.printf(num + " ");
        }
        StdOut.println();
        StdOut.printf("Deque size = " + deque.size());
    }
}

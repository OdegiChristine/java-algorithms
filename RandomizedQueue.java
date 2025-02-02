import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// This class implements a randomized queue data type. Elements are dequeued and sampled at random
public class RandomizedQueue<Item> implements Iterable<Item>{
    private Item[] queue;
    private int N = 0; //Number of elements in the array

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2]; //Casting to create a generic array
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (N == queue.length) resize(2 * queue.length);
        queue[N++] = item; // Add item to the nth position the increment it
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        int randomIndex = StdRandom.uniformInt(N); // Random index between 0 and N-1
        Item randomItem = queue[randomIndex]; // Select random item
        queue[randomIndex] = queue[N-1]; // Swap with the last item
        queue[N-1] = null; // Avoid memory leak
        N--; // Decrease size
        if (N > 0 && N == queue.length / 4) resize(queue.length / 2); // Shrink if necessary
        return randomItem;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()){
            throw new NoSuchElementException("Queue is empty.");
        }
        int randomIndex = StdRandom.uniformInt(N); //Generate a random index
        return queue[randomIndex];

    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>{
        private int current = 0;
        private final Item[] randomQueue;

        public RandomizedQueueIterator() {
            randomQueue = (Item[]) new Object[N]; // Create a copy of the original array
            for (int i = 0; i < N; i++) {
                randomQueue[i] = queue[i];
            }
            StdRandom.shuffle(randomQueue); // Shuffle the contents of our new array
        }

        @Override
        public boolean hasNext() {
            return current < N;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return randomQueue[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }
    }

    // Private method to resize the array
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        
        // Test enqueue
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);

        // Test sample
        StdOut.println("Sample: " + randomizedQueue.sample());

        // Test dequeue
        StdOut.println("Dequeue: " + randomizedQueue.dequeue());
        StdOut.println("Dequeue: " + randomizedQueue.dequeue());

        // Test iteration
        for (int item : randomizedQueue) {
            StdOut.println("Iterator item: " + item);
        }

        //Test size
        StdOut.println("The size of the queue is " + randomizedQueue.size());
    }
}

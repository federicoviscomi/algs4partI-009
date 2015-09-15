import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_INITIAL_AND_MIN_CAPACITY = 10;
    private static final int SHUFFLE_THRESHOLD = 5;
    private int size;
    private Item[] elements;

    public RandomizedQueue() {
        this.elements = (Item[]) new Object[DEFAULT_INITIAL_AND_MIN_CAPACITY];
        this.size = 0;
    }

    /*@Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RandomizedQueue{");
        sb.append("size=").append(size);
        sb.append(", elements=").append(elements == null ? "null" : Arrays.asList(elements).toString());
        sb.append(", random=").append(random);
        sb.append('}');
        return sb.toString();
    }*/

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        grow();
        this.elements[size] = item;
        size++;
    }

    private void grow() {
        if (size == this.elements.length) {
            this.elements = Arrays.copyOf(elements, 2 * this.elements.length);
        }
    }

    public Item dequeue() {
        int peek = StdRandom.uniform(size);
        Item dequeued = this.elements[peek];
        this.elements[peek] = this.elements[size];
        this.elements[size] = null;
        size--;
        shrink();
        return dequeued;
    }

    private void shrink() {
        if (size <= this.elements.length / 4) {
            int capacity = Math.max(DEFAULT_INITIAL_AND_MIN_CAPACITY, this.elements.length / 2);
            this.elements = Arrays.copyOf(elements, capacity);
        }
    }

    public Item sample() {
        int sampleIndex = StdRandom.uniform(size);
        Item sample = this.elements[sampleIndex];
        return sample;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>();
    }

    private final class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private final int[] indexes;
        private int next;

        public RandomizedQueueIterator() {
            this.indexes = new int[RandomizedQueue.this.size];
            for (int i = 0; i < indexes.length; i++) {
                indexes[i] = i;
            }

            // shuffle
            for (int i = indexes.length - 1; i >= 0; i--) {
                int j = StdRandom.uniform(i + 1);
                int temp = indexes[i];
                indexes[i] = indexes[j];
                indexes[j] = temp;
            }

            this.next = 0;
        }

        @Override
        public boolean hasNext() {
            return this.next < indexes.length;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            Item nextItem = (Item) RandomizedQueue.this.elements[indexes[next]];
            next++;
            return nextItem;
        }
    }
}





import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> front;
    private Node<Item> end;
    private int size;

    public Deque() {
        this.front = null;
        this.end = null;
        this.size = 0;
    }

    public static void main(String[] args) {
        throw new NotImplementedException();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (this.front == null) {
            assert this.end == null;
            this.front = this.end = new Node<>(item);
        } else if (this.front == this.end) {
            Node<Item> newNode = new Node<>(item);
            newNode.next = this.front;
            this.front = newNode;
            this.end.prev = newNode;
        } else {
            assert this.end != null;
            Node<Item> newNode = new Node<Item>(item, this.front, null);
            this.front.prev = newNode;
            this.front = newNode;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (this.front == null) {
            assert this.end == null;
            this.front = this.end = new Node<>(item);
        } else if (this.front == this.end) {
            Node<Item> newNode = new Node<>(item);
            newNode.prev = this.end;
            this.end = newNode;
            this.front.next = newNode;
        } else {
            assert this.end != null;
            Node<Item> newNode = new Node<Item>(item, null, this.end);
            this.end.next = newNode;
            this.end = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item;
        if (this.front == null) {
            assert this.end == null;
            throw new NoSuchElementException();
        }
        item = this.front.item;
        if (this.front == this.end) {
            this.front = this.end = null;
        } else {
            assert this.end != null;
            assert this.front.next != null;

            this.front = this.front.next;
        }
        size--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        Item item;
        if (this.end == null) {
            assert this.front == null;
            throw new NoSuchElementException();
        }
        item = this.end.item;
        if (this.front == this.end) {
            this.front = this.end = null;
        } else {
            assert this.front != null;
            assert this.end.prev != null;

            this.end = this.end.prev;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        // it should check for concurrent modifications
        // or maybe just use synchronized
        return new Iterator<Item>() {
            private Node<Item> nextNode = Deque.this.front;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public Item next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Item nextItem = nextNode.item;
                nextNode = nextNode.next;
                return nextItem;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static final class Node<Item> {
        public final Item item;
        public Node<Item> next;
        public Node<Item> prev;

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            if (item == null) {
                throw new NullPointerException();
            }
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public Node(Item item) {
            if (item == null) {
                throw new NullPointerException();
            }
            this.item = item;
            this.next = this.prev = null;
        }
    }
}
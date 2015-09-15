import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }
        int k = Integer.parseInt(args[0]);
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        String next;
        try {
            while ((next = StdIn.readString()) != null) {
                randomizedQueue.enqueue(next);
            }
        } catch (NoSuchElementException e) {
            // not an error
        }
        Iterator<String> iterator = randomizedQueue.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(iterator.next());
        }
    }
}

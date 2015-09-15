import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.StringTokenizer;

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
        String line = StdIn.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        while (stringTokenizer.hasMoreTokens()) {
            randomizedQueue.enqueue(stringTokenizer.nextToken());
        }
        if (k > randomizedQueue.size()) {
            throw new IllegalArgumentException();
        }

        Iterator<String> iterator = randomizedQueue.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(iterator.next());
        }
    }
}

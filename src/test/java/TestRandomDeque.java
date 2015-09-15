import org.junit.Test;

import static org.junit.Assert.*;

public class TestRandomDeque {

    @Test
    public void test1() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertEquals(0, randomizedQueue.size());
        assertTrue(randomizedQueue.isEmpty());
    }

}

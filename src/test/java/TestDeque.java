import org.junit.Test;

import static org.junit.Assert.*;

public class TestDeque {

    @Test
    public void test1() {
        Deque<Integer> deque = new Deque<>();
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());

        for (int i = 1; i <= 100; i++) {
            deque.addFirst(i);
            assertEquals(i, deque.size());
            assertFalse(deque.isEmpty());
        }

        for (int i = 100; i > 1; i--) {
            Integer first = deque.removeFirst();
            assertEquals(i - 1, deque.size());
            assertFalse(deque.isEmpty());
            assertEquals(i, first.intValue());
        }
    }

    @Test(expected = NullPointerException.class)
    public void testAddFirstNullItem() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddLastNullItem() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(null);
    }


    @Test
    public void test10() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        assertEquals(1, deque.removeFirst().intValue());
        deque.addLast(3);
        deque.addFirst(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addFirst(7);
        deque.addLast(8);
        deque.addFirst(9);
        assertEquals(8, deque.removeLast().intValue());
        assertEquals(6, deque.size());
        for (Integer x : deque) {
            System.out.println(x);
        }
    }
}

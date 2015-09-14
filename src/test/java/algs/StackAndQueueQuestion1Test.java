package algs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackAndQueueQuestion1Test {
    @Test
    public void testSeed566419() {
        int[][] testCases = new int[][]{
                new int[]{2, 1, 0, 3, 4, 6, 5, 7, 8, 9},
                new int[]{5, 4, 3, 2, 1, 0, 6, 7, 8, 9},
                new int[]{3, 2, 1, 9, 8, 7, 6, 5, 4, 0},
                new int[]{1, 3, 0, 4, 9, 8, 7, 6, 5, 2},
                new int[]{2, 3, 4, 6, 1, 5, 7, 9, 8, 0}
        };
        boolean[] valid = new boolean[]{
                false,
                true,
                false,
                true,
                true
        };
        for (int i = 0; i < testCases.length; i++) {
            // assertEquals(valid[i], StackAndQueueQuestion1.validate(testCases[i]));
            System.out.println(StackAndQueueQuestion1.validate(testCases[i]));
        }
    }


    @Test
    public void testSeed188315() {
        int[][] testCases = new int[][]{
                new int[]{4, 6, 5, 3, 8, 2, 7, 1, 9, 0},
                new int[]{0, 3, 2, 1, 5, 7, 9, 8, 6, 4},
                new int[]{1, 3, 2, 5, 0, 7, 8, 9, 6, 4},
                new int[]{0, 4, 6, 5, 3, 7, 2, 9, 8, 1},
                new int[]{4, 3, 2, 1, 0, 5, 6, 7, 8, 9}
        };
        boolean[] valid = new boolean[]{
                false,
                true,
                false,
                true,
                true
        };
        for (int i = 0; i < testCases.length; i++) {
            assertEquals(valid[i], StackAndQueueQuestion1.validate(testCases[i]));
        }
    }

    @Test
    public void testSeed688245() {
        int[][] testCases = new int[][]{
                new int[]{6, 5, 4, 3, 2, 1, 0, 7, 8, 9},
                new int[]{0, 1, 2, 3, 5, 4, 6, 7, 9, 8},
                new int[]{0, 1, 3, 5, 9, 8, 7, 6, 4, 2},
                new int[]{3, 4, 2, 5, 7, 1, 6, 8, 0, 9},
                new int[]{3, 4, 2, 6, 1, 9, 8, 7, 5, 0}
        };
        boolean[] valid = new boolean[]{
                true,
                true,
                true,
                false,
                false
        };
        for (int i = 0; i < testCases.length; i++) {
            assertEquals(valid[i], StackAndQueueQuestion1.validate(testCases[i]));
        }
    }

    @Test
    public void testSeed417911() {
        int[][] testCases = new int[][]{
                new int[]{0, 1, 2, 3, 5, 4, 6, 7, 9, 8},
                new int[]{1, 0, 3, 8, 9, 7, 6, 5, 4, 2},
                new int[]{4, 3, 2, 1, 0, 5, 6, 7, 8, 9},
                new int[]{1, 4, 3, 2, 6, 0, 7, 8, 5, 9},
                new int[]{0, 1, 5, 6, 8, 4, 7, 9, 3, 2}
        };
        boolean[] valid = new boolean[]{
                true,
                true,
                true,
                false,
                false
        };
        for (int i = 0; i < testCases.length; i++) {
            assertEquals(valid[i], StackAndQueueQuestion1.validate(testCases[i]));
        }
    }
}

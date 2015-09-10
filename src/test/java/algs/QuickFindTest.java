package algs;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuickFindTest {

    @Test
    public void testQuickFind() {
        QuickFind uf = new QuickFind(10);
        int[][] input = new int[][]{
                new int[]{4, 3},
                new int[]{3, 8},
                new int[]{6, 5},
                new int[]{9, 4},
                new int[]{2, 1},
                new int[]{8, 9},
                new int[]{5, 0},
                new int[]{7, 2},
                new int[]{6, 1},
                new int[]{1, 0},
                new int[]{6, 7}
        };
        for (int[] inputPair : input) {
            System.out.println("\n\n");
            System.out.println(uf);
            if (!uf.connected(inputPair[0], inputPair[1])) {
                uf.union(inputPair[0], inputPair[1]);
                System.out.println(uf);
                assertTrue(uf.connected(inputPair[0], inputPair[1]));
                assertTrue(uf.connected(inputPair[1], inputPair[0]));
            }
        }
        System.out.println(uf);
    }

    @Test
    public void testEmptyAllNotConnected() {
        QuickFind qf = new QuickFind(10);
        for (int i = 0; i < qf.size(); i++) {
            for (int j = 0; j < qf.size(); j++) {
                if (i != j) {
                    assertFalse(qf.connected(i, j));
                }
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutOfBoundConstructor() {
        new QuickFind(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutOfBoundUnion() {
        QuickFind qf = new QuickFind(10);
        qf.union(0, qf.size());
    }

}

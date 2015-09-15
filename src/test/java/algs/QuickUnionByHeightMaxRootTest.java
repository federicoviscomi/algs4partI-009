package algs;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class QuickUnionByHeightMaxRootTest {

    @Test
    public void testQuickUnionByHeightMaxRoot() {
        QuickUnionByHeightMaxRoot uf = new QuickUnionByHeightMaxRoot(12);
        int[][] input = new int[][]{
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{2, 6},
                new int[]{6, 9},
        };
        for (int[] inputPair : input) {
            uf.union(inputPair[0], inputPair[1]);
        }
        assertEquals(9, uf.find(0));
        assertEquals(9, uf.find(1));
        assertEquals(9, uf.find(2));
        assertEquals(9, uf.find(6));
        assertEquals(9, uf.find(9));
    }
}

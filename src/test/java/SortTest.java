import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class SortTest {
    private static final int[][][] sortTestCases = new int[][][]{
            new int[][]{null, null},
            new int[][]{new int[]{}, new int[]{}},
            new int[][]{new int[]{0}, new int[]{0}},
            new int[][]{new int[]{0, 0}, new int[]{0, 0}},
            new int[][]{new int[]{0, 0, 0}, new int[]{0, 0, 0}},
            new int[][]{new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}},
            new int[][]{new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}},
            new int[][]{new int[]{0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}},
            new int[][]{new int[]{0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0}},
            new int[][]{new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}},

            new int[][]{new int[]{0, 1}, new int[]{0, 1}},
            new int[][]{new int[]{1, 0, 0}, new int[]{0, 0, 1}},
            new int[][]{new int[]{0, 0, 1, 0}, new int[]{0, 0, 0, 1}},
            new int[][]{new int[]{0, 1, 0, 0, 0}, new int[]{0, 0, 0, 0, 1}},
            new int[][]{new int[]{0, 0, 0, 1, 0, 0}, new int[]{0, 0, 0, 0, 0, 1}},
            new int[][]{new int[]{0, 0, 0, 0, 1, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 1}},
            new int[][]{new int[]{0, 0, 0, 0, 0, 1, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 1}},
            new int[][]{new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}},
            new int[][]{new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}},
            new int[][]{new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}},

            new int[][]{new int[]{1, 0}, new int[]{0, 1}},
            new int[][]{new int[]{2, 1, 0}, new int[]{0, 1, 2}},
            new int[][]{new int[]{3, 2, 1, 0}, new int[]{0, 1, 2, 3}},
            new int[][]{new int[]{4, 3, 2, 1, 0}, new int[]{0, 1, 2, 3, 4}},
            new int[][]{new int[]{5, 4, 3, 2, 1, 0}, new int[]{0, 1, 2, 3, 4, 5}},
            new int[][]{new int[]{6, 5, 4, 3, 2, 1, 0}, new int[]{0, 1, 2, 3, 4, 5, 6}},
            new int[][]{new int[]{7, 6, 5, 4, 3, 2, 1, 0}, new int[]{0, 1, 2, 3, 4, 5, 6, 7}},
            new int[][]{new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}},
            new int[][]{new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}},

            new int[][]{new int[]{1, 0, 1}, new int[]{0, 1, 1}},
            new int[][]{new int[]{2, 1, 0, 1}, new int[]{0, 1, 1, 2}},
            new int[][]{new int[]{3, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 3}},
            new int[][]{new int[]{4, 3, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 3, 4}},
            new int[][]{new int[]{5, 4, 3, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 3, 4, 5}},
            new int[][]{new int[]{6, 5, 4, 3, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 3, 4, 5, 6}},
            new int[][]{new int[]{7, 6, 5, 4, 3, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 3, 4, 5, 6, 7}},
            new int[][]{new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 3, 4, 5, 6, 7, 8}},
            new int[][]{new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9}},

            new int[][]{new int[]{2, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 2}},
            new int[][]{new int[]{3, 2, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 2, 3}},
            new int[][]{new int[]{4, 3, 2, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 2, 3, 4}},
            new int[][]{new int[]{5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 2, 3, 4, 5}},
            new int[][]{new int[]{6, 5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 2, 3, 4, 5, 6}},
            new int[][]{new int[]{7, 6, 5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 2, 3, 4, 5, 6, 7}},
            new int[][]{new int[]{8, 7, 6, 5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 2, 3, 4, 5, 6, 7, 8}},
            new int[][]{new int[]{9, 8, 7, 6, 5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9}},


            new int[][]{new int[]{2, 2, 1, 0, 1, 2, 2, 1, 0, 1}, new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2}},
            new int[][]{new int[]{3, 2, 2, 1, 0, 1, 3, 2, 2, 1, 0, 1}, new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3}},
            new int[][]{new int[]{4, 3, 2, 2, 1, 0, 1, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4}},
            new int[][]{new int[]{5, 4, 3, 2, 2, 1, 0, 1, 5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5}},
            new int[][]{new int[]{6, 5, 4, 3, 2, 2, 1, 0, 1, 6, 5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6}},
            new int[][]{new int[]{7, 6, 5, 4, 3, 2, 2, 1, 0, 1, 7, 6, 5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7}},
            new int[][]{new int[]{8, 7, 6, 5, 4, 3, 2, 2, 1, 0, 1, 8, 7, 6, 5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8}},
            new int[][]{new int[]{9, 8, 7, 6, 5, 4, 3, 2, 2, 1, 0, 1, 9, 8, 7, 6, 5, 4, 3, 2, 2, 1, 0, 1}, new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9}},

            // TODO produce bigger test cases
    };
    private java.util.function.Function<int[], int[]>[] Function;

    @Test
    public void testAll() {
        for (int[][] sortTestCase : sortTestCases) {
            for (Function<int[], int[]> sortMethod : getSortMethods()) {
                Assert.assertArrayEquals(sortTestCase[1], sortMethod.apply(sortTestCase[0]));
            }
        }
    }

    public Function<int[], int[]>[] getSortMethods() {
        Function[] sortMethods = new Function[3];
        // sortMethods[0] = Selection::sort; gives error
        Function<int[], int[]> sort = Selection::sort;
        sortMethods[0] = sort;
        sort = Insertion::sort;
        sortMethods[1] = sort;
        sort = Shellsort::sort;
        sortMethods[2] = sort;
        return sortMethods;
    }
}


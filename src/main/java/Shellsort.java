public class Shellsort {
    public static int[] sort(int[] a) {
        if (a == null) {
            return null;
        }
        for (int i = 0; i < a.length; i++) {
            int minInd = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[minInd])) {
                    minInd = j;
                }
            }
            exch(a, i, minInd);
        }
        return a;
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(int a, int b) {
        return a < b;
    }
}

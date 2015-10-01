public class Insertion {
    public static int[] sort(int[] a) {
        if (a == null) {
            return null;
        }
        for (int i = 0; i < a.length; i++) {
            int j = i;
            while (j > 0 && less(a[j], a[j - 1])) {
                exch(a, j, j - 1);
                j--;
            }
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


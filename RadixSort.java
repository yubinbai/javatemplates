import java.util.*;

public class RadixSort {
    private static final int radix = 256, shifts[] = {8, 16, 24}, mask = radix - 1;
    private final int[] bar = new int[radix];
    private int[] s = new int[65536]; // better caching for array in object

    public void ensureSort(int len) {
        if (s.length < len)
            s = new int[len];
    }

    public void sort(int[] a) {
        int n = a.length;
        ensureSort(n);

        // last 8 bits
        Arrays.fill(bar, 0);
        for (int i = 0; i < n; i++) bar[a[i] & mask]++;
        for (int i = 1; i < radix; i++) bar[i] += bar[i - 1];
        // fill in reverse order to maintain stable sort
        for (int i = 0; i < n; i++) s[--bar[a[i] & mask]] = a[i];

        // second last 8 bits
        Arrays.fill(bar, 0);
        for (int i = 0; i < n; i++) bar[(s[i] >> 8) & mask]++;
        for (int i = 1; i < radix; i++) bar[i] += bar[i - 1];
        for (int i = n - 1; i >= 0; i--) a[--bar[(s[i] >> 8) & mask]] = s[i];

        // third last 8 bits
        Arrays.fill(bar, 0);
        for (int i = 0; i < n; i++) bar[(a[i] >> 16) & mask]++;
        for (int i = 1; i < radix; i++) bar[i] += bar[i - 1];
        for (int i = n - 1; i >= 0; i--) s[--bar[(a[i] >> 16) & mask]] = a[i];

        // first byte including the sign
        Arrays.fill(bar, 0);
        for (int i = 0; i < n; i++) bar[(s[i] >> 24) & mask]++;
        for (int i = 129; i < radix; i++) bar[i] += bar[i - 1];
        bar[0] += bar[255]; // bar[128...255] are negative numbers
        for (int i = 1; i < 128; i++) bar[i] += bar[i - 1];
        for (int i = n - 1; i >= 0; i--) a[--bar[(s[i] >> 24) & mask]] = s[i];
    }
    public static void main(String[] args) {
        RadixSort rs = new RadixSort();
        int[] arr = new int[] {1, 300, 10};
        rs.sort(arr);
        System.out.format("%s", Arrays.toString(arr));
    }
}
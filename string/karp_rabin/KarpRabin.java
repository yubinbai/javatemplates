public class KarpRabin {
    static final long P = 1000000007;
    static long[] p; // p[i] = P^i % 2^64
    static long[] a; // a[i] = t[i-1]*p[0] + t[i-2]*p[1] + ... + t[0]*p[i-1]
    char[] s, t;
    int m, n;
    private int foundCounter;
    private int[] locations;

    public KarpRabin(char[] s) {
        this.s = s;
        this.n = s.length;
    }

    static long hh(int x, int y) {
        /*
         * Assumes x<=y. Let k = y-x. This function returns t[y]*p[0] +
         * t[y-1]*p[1] + ... + t[x]*p[k]. In other words, it's the hash function
         * from x to y inclusive
         */
        return a[y + 1] - a[x] * p[y - x + 1];
    }

    public void searchIn(char[] t) {
        // Search inside a string
        this.t = t;
        this.m = t.length;
        p = new long[m + 1];
        a = new long[m + 1];
        this.locations = new int[m - n];
        this.foundCounter = 0;

        /* pre-compute p[] and a[] to make hh() work in O(1) time */
        p[0] = 1;
        for (int i = 1; i <= m; i++) {
            p[i] = p[i - 1] * P;
            a[i] = a[i - 1] * P + t[i - 1];
        }

        long H = 0;
        for (int i = 1; i <= n; i++) H = P * H + s[i - 1];

        for (int i = 0; i + n <= m; i++) {
            if (hh(i, i + n - 1) == H) {
                locations[foundCounter] = i;
                foundCounter++;
            }
        }
    }

    public String printResult() {
        String s = "";
        for (int i = 0; i < this.foundCounter; i++)
            s += String.format("found at:  %d \n", locations[i]);
        return s;
    }

    public static void main(String[] args) {
        char[] s = "test".toCharArray();
        char[] t = "testestest hello there test!".toCharArray();
        KarpRabin kr = new KarpRabin(s);
        kr.searchIn(t);
        String found = kr.printResult();
        System.out.println(found);
    }

}

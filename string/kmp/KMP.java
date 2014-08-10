public class KMP {
    int n, m;
    char[] s, t;
    int[] memo, found;
    private int foundCounter;
    private int[] locations;

    /**
     * Init the search string and build the memo table here
     *
     * @param s
     */
    public KMP(char[] s) {

        this.s = s;
        this.n = s.length;

        /*
         * memo[k] will store the length of the longest prefix of s that matches
         * the tail of s1...sk
         */
        this.memo = new int[n];

        int j = 0;
        memo[0] = 0;
        // calculate memo[]
        for (int i = 1; i < n; i++) {
            while (j > 0 && s[i] != s[j])
                j = memo[j - 1];
            if (s[i] == s[j])
                j++;
            memo[i] = j;
        }

    }

    /**
     * Search for t in s
     *
     * @param t
     * @return a series of locations
     */
    public int[] searchIn(char[] t) {
        this.t = t;
        this.m = t.length;
        this.locations = new int[m - n];
        this.foundCounter = 0;

        int j = 0;
        for (int i = 0; i < m; i++) {
            while (j > 0 && t[i] != s[j])
                j = memo[j - 1];
            if (t[i] == s[j])
                j++;
            if (j == n) {
                locations[foundCounter] = (i - n + 1);
                foundCounter++;
                j = memo[j - 1];
            }
        }
        return locations;
    }

    public String printMemo() {
        String s = "";
        for (int i = 0; i < n; i++)
            s += String.format("%d: memo %d \n", i, memo[i]);
        return s;
    }

    public String printFound() {
        String s = "";
        for (int i = 0; i < this.foundCounter; i++)
            s += String.format("found at:  %d \n", locations[i]);
        return s;
    }

    public static void main(String[] args) {
        char[] s = "test".toCharArray();
        char[] t = "testestest hello there test!".toCharArray();

        KMP kmp = new KMP(s);
        kmp.searchIn(t);

        System.out.println(kmp.printMemo());
        System.out.println(kmp.printFound());
    }
}

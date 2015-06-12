import java.util.*;

class SegmentTreeMin {
    int size;
    int[] tree;

    public SegmentTreeMin(int size) {
        this.size = 1;
        while (this.size < size) this.size <<= 1;

        tree = new int[this.size * 2 - 1];
        Arrays.fill(tree, Integer.MAX_VALUE);
    }
    public SegmentTreeMin(int[] array) {
        this(array.length);
        System.arraycopy(array, 0, tree, this.size - 1, array.length);
        for (int i = size - 2; i >= 0; i--) {
            tree[i] = Math.min(tree[2 * i + 1], tree[2 * i + 2]);
        }
    }
    /**
     * set value at i (zero-based) to be a
     * @param i
     * @param a
     */
    public boolean set(int i, int a) {
        if (i >= size) return false;
        i += size - 1;
        // leaf
        tree[i] = a;
        // parents
        while (i > 0) {
            i = (i - 1) / 2;
            tree[i] = Math.min(tree[2 * i + 1], tree[2 * i + 2]);
        }
        return true;
    }

    /**
     * find min in [begin, end) , with limit inside the range [low, high)
     * @param  begin
     * @param  end
     * @param  node
     * @param  low
     * @param  high
     */
    private int query(int begin, int end, int node, int low, int high) {

        //if the current interval doesn't intersect the query interval return
        if (high <= begin || low >= end) return Integer.MAX_VALUE;

        //if the current interval is included in the query interval return M[node]
        if (low >= begin && high <= end) return tree[node];

        int p1 = query(begin, end, node * 2 + 1, low, (low + high) / 2);
        int p2 = query(begin, end, node * 2 + 2, (low + high) / 2, high);
        return Math.min(p1, p2);
    }
    public int findMin(int begin, int end) {
        return query(begin, end, 0, 0, size);
    }
    public static void main(String[] args) {
        int n = 10;
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            int t = r.nextInt(n * n);
            System.out.format("#%d->%d \t", i, t);
            arr[i] = t;
        }
        SegmentTreeMin sgm = new SegmentTreeMin(arr);
        int begin = 0, end = 2;
        System.out.println();
        System.out.format("begin: %d, end: %d, value: %d\n", begin, end, sgm.findMin(0, 2));
    }
}

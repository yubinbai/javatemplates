// In this implementation, the tree is represented by a list
// Elements are numbered by 0, 1, ..., n-1.
// tree[i] is sum of elements with indexes i&(i+1)..i, inclusive.
// (Note: this is a bit different from what is proposed in Fenwick's article.
// To see why it makes sense, think about the trailing 1's in binary
// representation of indexes.)

import java.util.Random;

public class BinaryIndexedTree {
    int size;
    int[] tree;
    public BinaryIndexedTree(int size) {
        this.size = size;
        this.tree = new int[size + 1];
    }

    /**
     * add the i-th element by delta (zero-based)
     * @param i
     * @param delta
     */
    public void add(int i, int delta) {
        i++; // zero-based to one-based
        while (i <= size) {
            tree[i] += delta;
            i += i & -i;
        }
    }

    /**
     * get sum between [0..i] 
     * i is zero-based
     * @param i
     * @return
     */
    private int sum(int i) {
        i++; // zero-based to one-based
        int currSum = 0;
        while (i > 0) {
            currSum += tree[i];
            i -= i & -i;
        }
        return currSum;
    }

    /**
     * get sum between left and right
     * @param left
     * @param right
     * @return
     */
    public int getSum(int left, int right) {
        if (left == 0) {
            return sum(right);
        } else {
            return sum(right) - sum(left - 1);
        }
    }

    public static void main(String[] args) {
        int size = 10;
        int[] raw = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            raw[i] = r.nextInt(20);
        }
        BinaryIndexedTree t = new BinaryIndexedTree(size);
        for (int i = 0; i < size; i++) {
            if (raw[i] > 0) {
                t.add(i, raw[i]);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                int s = 0;
                for (int k = i; k <= j; k++) {
                    s += raw[k];
                }
                int ss = t.getSum(i, j);
                if (s != ss) {
                    System.out.format("Not equal %d %d %d %d\n", i, j, s, ss);
                } else {
                    System.out.format("Equal %d %d %d %d\n", i, j, s, ss);
                }
            }
        }
    }

}

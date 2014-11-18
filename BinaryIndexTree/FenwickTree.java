// In this implementation, the tree is represented by a list
// Elements are numbered by 0, 1, ..., n-1.
// tree[i] is sum of elements with indexes i&(i+1)..i, inclusive.
// (Note: this is a bit different from what is proposed in Fenwick's article.
// To see why it makes sense, think about the trailing 1's in binary
// representation of indexes.)

import java.util.Random;

public class FenwickTree {
    int size;
    int[] tree;
    public FenwickTree(int size) {
        this.size = size;
        this.tree = new int[size];
    }

    /**
     * increase the i-th element by delta
     * @param i
     * @param delta
     */
    public void increase(int i, int delta) {
        while (i < size) {
            tree[i] += delta;
            i |= i + 1;
        }
    }

    /**
     * get sum between left and right
     * @param left
     * @param right
     * @return
     */
    public int getSum(int left, int right) {
        if (left == 0) {
            return _sum(right);
        } else {
            return _sum(right) - _sum(left - 1);
        }
    }

    /**
     * get sum between [0..index]
     * @param index
     * @return
     */
    private int _sum(int index) {
        int currSum = 0;
        while (index >= 0) {
            currSum += tree[index];
            index &= index + 1;
            index -= 1;
        }
        return currSum;
    }

    public static void main(String[] args) {
        int size = 100;
        int[] raw = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            raw[i] = r.nextInt(20);
        }
        FenwickTree t = new FenwickTree(size);
        for (int i = 0; i < size; i++) {
            if (raw[i] > 0) {
                t.increase(i, raw[i]);
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

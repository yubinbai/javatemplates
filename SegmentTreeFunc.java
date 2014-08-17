/**
 * Created on Apr 1, 2014
 * @author: Yubin Bai
 * Segment Tree Library
 * The segment tree is stored like a heap array with 1-base
 */

import java.util.*;

class SegmentTreeFunc <T extends Comparable<T>> {
    private int length;
    protected ArrayList<T> tree;

    public SegmentTreeFunc(int len) {
        this.length = len;
        int arrayLen = 1;
        while (arrayLen < 2 * len)
            arrayLen <<= 1;
        this.tree = new ArrayList<T>();
        for (int i = 0; i < arrayLen; i++) {
            this.tree.add(null);
        }
    }

    public T func(T t1, T t2) {
        // default to max function
        return (t1.compareTo(t2) >= 0) ? t1 : t2;
    }

    public void buildTree(T[] arr) {
        this.buildTree(arr, 1, 0, arr.length - 1);
    }

    protected void buildTree(T[] arr, int node, int begin, int end) {
        if (begin == end) {
            this.tree.set(node, arr[begin]);
        } else {
            int left = 2 * node;
            int right = 2 * node + 1;
            this.buildTree(arr, left, begin, (begin + end) / 2);
            this.buildTree(arr, right, (begin + end) / 2 + 1, end);
            T i1 = this.tree.get(left);
            T i2 = this.tree.get(right);
            this.tree.set(node, func(i1, i2));
        }
    }

    public T get(int i, int j) {
        return this.get(1, 0, this.length - 1, i, j);
    }

    protected T get(int node, int b, int e, int i, int j) {
        if (i > e || j < b)
            return null;
        if (b >= i && e <= j)
            return this.tree.get(node);
        T t1 = get(2 * node, b, (b + e) / 2, i, j);
        T t2 = get(2 * node + 1, (b + e) / 2 + 1, e, i, j);
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        return func(t1, t2);
    }

    public boolean set(T value, int i) {
        return set(value, i, i);
    }

    public boolean set(T value, int i, int j) {
        if (i < 0 || j > this.length - 1 || i > j) return false;
        set(value, 1, 0, this.length - 1, i, j);
        return true;
    }

    protected T set(T value, int node, int b, int e, int i, int j) {
        if (i > e || j < b)
            return this.get(b, e);
        if (b == e) {
            this.tree.set(node, value);
            return value;
        }
        if (this.get(node, b, e, i, j).equals(value))
            return value;
        T t1 = set(value, node * 2, b, (b + e) / 2, i, j);
        T t2 = set(value, node * 2 + 1, (b + e) / 2 + 1, e, i, j);
        T tt = func(t1, t2);
        this.tree.set(node, tt);
        return tt;
    }

    public static void main(String[] args) {
        Integer[] A = {8, 7, 3, 9, 5, 1, 10};
        SegmentTreeFunc<Integer> t = new SegmentTreeFunc<Integer>(A.length);
        t.buildTree(A);
        System.out.format("%d ", t.get(0, 1));
        System.out.format("%d ", t.get(1, 1));
        System.out.format("%d ", t.get(1, 2));
        System.out.format("%d ", t.get(1, 3));
        System.out.format("%d ", t.get(0, 3));
        System.out.format("%d ", t.get(3, 3));
        System.out.println();
        t.set(11, 2, 2);
        System.out.format("%d ", t.get(0, 1));
        System.out.format("%d ", t.get(1, 1));
        System.out.format("%d ", t.get(1, 2));
        System.out.format("%d ", t.get(1, 3));
        System.out.format("%d ", t.get(0, 3));
        System.out.format("%d ", t.get(3, 3));
        System.out.println();
        t.set(33, 1, 2);
        System.out.format("%d ", t.get(0, 1));
        System.out.format("%d ", t.get(1, 1));
        System.out.format("%d ", t.get(1, 2));
        System.out.format("%d ", t.get(1, 3));
        System.out.format("%d ", t.get(0, 3));
        System.out.format("%d ", t.get(3, 3));
        System.out.println();
    }
}



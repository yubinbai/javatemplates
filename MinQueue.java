import java.util.*;

public class MinQueue {
    ArrayDeque<Integer> minQ, data;//We need 2 deque objects
    public MinQueue() {
        minQ = new ArrayDeque<Integer>();
        data = new ArrayDeque<Integer>();
    }
    public void offer(int element) {
        data.offer(element);
        while (!minQ.isEmpty() && minQ.peekLast() > element) {
            minQ.pollLast();
        }
        minQ.offer(element);
    }
    public int poll() {
    	if (data.isEmpty()) throw new RuntimeException();
        if (minQ.peekFirst() == data.peekFirst()) minQ.pollFirst();
        return data.pollFirst();
    }
    public int getMin() {
        if (minQ.isEmpty()) {
            throw new RuntimeException();
        }
        return minQ.peekFirst();
    }
    public static void main(String[] args) {
        MinQueue q = new MinQueue();
        for (int i : new int[] {12, 5, 10, 7, 11, 19}) {
            q.offer(i);
            System.out.format("%d %d\n", i, q.getMin());
        }
        q = new MinQueue();
        for (int i : new int[] {12, 5, 10, 7, 11, 19}) {
            q.offer(i);
            System.out.format("%d %d\n", i, q.getMin());
            q.poll();
        }
    }
}

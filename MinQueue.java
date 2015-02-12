import java.util.*;
class MinQueue {
    ArrayDeque<Integer> minQ, q;//We need 2 deque objects
    public MinQueue() {
        minQ = new ArrayDeque<Integer>();
        q = new ArrayDeque<Integer>();
    }
    public void offer(int element) {
        q.offerLast(element);
        while (!minQ.isEmpty() && minQ.peekLast() > element)
            minQ.pollLast();
        minQ.offerLast(element);
    }
    public int poll() {
        if (minQ.peekFirst() == q.peekFirst() )
            minQ.pollFirst();
        return q.pollFirst();
    }
    public int getMin() {
        if (minQ.isEmpty()) {
            throw new RuntimeException();
        } 
        return minQ.peekFirst();
    }
    public static void main(String[] args) {
        MinQueue q = new MinQueue();
        for (int i : new int[] {12,5,10,7,11,19}) {
            q.offer(i);
            System.out.format("%d %d\n", i, q.getMin());
        }
        q = new MinQueue();
        for (int i : new int[] {12,5,10,7,11,19}) {
            q.offer(i);
            System.out.format("%d %d\n", i, q.getMin());
            q.poll();
        }
    }
}

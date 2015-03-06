import java.util.*;
<<<<<<< HEAD
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
=======

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
>>>>>>> 91e3da61c371e7d31a5e4eed036f118d1198629e
    }
    public int getMin() {
        if (minQ.isEmpty()) {
            throw new RuntimeException();
<<<<<<< HEAD
        } 
=======
        }
>>>>>>> 91e3da61c371e7d31a5e4eed036f118d1198629e
        return minQ.peekFirst();
    }
    public static void main(String[] args) {
        MinQueue q = new MinQueue();
<<<<<<< HEAD
        for (int i : new int[] {12,5,10,7,11,19}) {
=======
        for (int i : new int[] {12, 5, 10, 7, 11, 19}) {
>>>>>>> 91e3da61c371e7d31a5e4eed036f118d1198629e
            q.offer(i);
            System.out.format("%d %d\n", i, q.getMin());
        }
        q = new MinQueue();
<<<<<<< HEAD
        for (int i : new int[] {12,5,10,7,11,19}) {
=======
        for (int i : new int[] {12, 5, 10, 7, 11, 19}) {
>>>>>>> 91e3da61c371e7d31a5e4eed036f118d1198629e
            q.offer(i);
            System.out.format("%d %d\n", i, q.getMin());
            q.poll();
        }
    }
}

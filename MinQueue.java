import java.util.*;
public class MinQueue {
	ArrayDeque<Integer> data, minQ;
	public MinQueue() {
		data = new ArrayDeque<Integer>();
		minQ = new ArrayDeque<Integer>();
	}
	public void enqueue(int e) {
		if (!minQ.isEmpty() && e <= getMin()) {
			while (!minQ.isEmpty() && e < getMin()) {
				minQ.pollLast();
			}
		}
		minQ.offer(e);
		data.offer(e);
	}
	public int dequeue() {
		if (data.isEmpty()) {
			return -1;
		}
		int e = data.poll();
		if (e == minQ.peekFirst()) {
			minQ.poll();
		}
		return e;
	}
	public int getMin() {
		if (data.isEmpty()) {
			return -1;
		}
		return minQ.peekFirst();
	}
	public static void main(String[] args) {
		MinQueue q = new MinQueue();
		for (int i : new int[] {3, 2, 1}) {
			q.enqueue(i);
		}
		System.out.println(q.getMin());
		q.dequeue();
		System.out.println(q.getMin());
		q.dequeue();
		System.out.println(q.getMin());
	}
}

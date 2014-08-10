package algorithm.basic.sortStack;

import java.util.Stack;

/**
 * Sort the stack s using an aux stack r
 * 
 */
public class SortStack {

	public static <T extends Comparable<T>> Stack<T> sort(Stack<T> s) {
		// sort the stack

		Stack<T> r = new Stack<T>();
		while (!s.isEmpty()) {
			T tmp = s.pop();
			while (!r.isEmpty() && r.peek().compareTo(tmp) < 0) {
				s.push(r.pop());
			}
			r.push(tmp);
		}
		return r;

	}
}

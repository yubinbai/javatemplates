package reverseLinkedList;

import java.util.*;
/**
 * Reverse a linked list
 * 
 */
public class ReverseLinkedList {

	public static <T> LinkedListNode<T> reverse(LinkedListNode<T> head) {
		// reversing a linked list need two swap space
		LinkedListNode<T> oldList = head.next, newList = head, swapOld, swapNew;

		while (oldList != null) {

			// save the old list variables
			swapOld = oldList;
			swapNew = newList;

			// move the first node of old list to new list
			oldList = oldList.next;
			newList = swapOld;
			newList.next = swapNew;

		}
		return newList;
	}
	public static void main(String[] args) {
		int length = 1000;

		ArrayList<LinkedListNode<Double>> data = new ArrayList<LinkedListNode<Double>>();

		// setup linked list
		LinkedListNode<Double> head = new LinkedListNode<Double>(new Double(0));
		data.add(head);

		LinkedListNode<Double> curr = head, next;
		for (int i = 1; i < length; i++) {
			next = new LinkedListNode<Double>(new Double(i));
			data.add(next);
			curr.next = next;
			curr = next;
		}

		// reverse list
		LinkedListNode<Double> reversedList = ReverseLinkedList.reverse(head);

		// try to test with array list
		curr = reversedList;

		for (int i = length - 1; i >= 0; i--) {
			if (data.get(i) != curr) System.out.println("Fault");
			curr = curr.next;
		}
	}
}

package interview.questions.reverseLinkedList;

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
}

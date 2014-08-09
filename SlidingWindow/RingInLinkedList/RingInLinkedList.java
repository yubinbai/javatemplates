package interview.questions.RingInLinkedList;

/**
 * Test if a linked list has ring
 * 
 */
/**
 * @author Yubin Bai
 * 
 */
public class RingInLinkedList {

	public static <T> boolean hasRing(LinkedListNode<T> head) {
		// Use two cursors to transverse the linked list, one with stepwidth 1,
		// the other with stepwidth 2.
		// They will meet in the end if the linked list contains ring

		LinkedListNode<T> n1 = head, n2 = head.next;
		while (n1 != n2 && n1 != null && n2.next != null) {
			n1 = n1.next;
			n2 = n2.next.next;
		}
		if (n1 == n2 && n1 != null)
			return true;
		else
			return false;
	}

	public static <T> LinkedListNode<T> ringStart(LinkedListNode<T> head) {

		LinkedListNode<T> n1 = head;
		LinkedListNode<T> n2 = head;

		// Find meeting point
		while (n2.next != null) {
			n1 = n1.next;
			n2 = n2.next.next;
			if (n1 == n2) {
				break;
			}
		}
		// Error check - there is no meeting point, and therefore no ring
		if (n2.next == null) {
			return null;
		}

		/*
		 * Move n1 to Head. Keep n2 at Meeting Point. Each are k steps from
		 * the ring Start. If they move at the same pace, they must meet at ring
		 * Start.
		 */
		n1 = head;
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		// Now n2 points to the start of the ring.
		return n2;

	}

}

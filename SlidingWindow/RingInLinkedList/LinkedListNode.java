package interview.questions.RingInLinkedList;

public class LinkedListNode<T> {
	T payload;
	LinkedListNode<T> next;

	public LinkedListNode(T payload) {
		super();
		this.payload = payload;
		this.next = null;
	}

}

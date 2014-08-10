package interview.questions.reverseLinkedList;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for reverse linked list.
 */
public class ReverseLinkedListTest extends TestCase {

	int length = 1000;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ReverseLinkedListTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ReverseLinkedListTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testReverseLinkedList() {

		ArrayList<LinkedListNode<Double>> data = new ArrayList<LinkedListNode<Double>>();

		// setup linked list
		LinkedListNode<Double> head = new LinkedListNode<Double>(new Double(0));
		data.add(head);

		LinkedListNode<Double> curr = head, next;
		for (int i = 1; i < this.length; i++) {
			next = new LinkedListNode<Double>(new Double(i));
			data.add(next);
			curr.next = next;
			curr = next;
		}

		// reverse list
		LinkedListNode<Double> reversedList = ReverseLinkedList.reverse(head);

		// try to test with array list
		curr = reversedList;

		for (int i = this.length - 1; i >= 0; i--) {
			assertEquals(data.get(i), curr);
			curr = curr.next;
		}

	}
}

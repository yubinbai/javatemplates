package interview.questions.RingInLinkedList;

import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class RingInLinkedListTest extends TestCase {
	private int length = 1000;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public RingInLinkedListTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(RingInLinkedListTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testForRing() {
		// setup linked list with no ring
		LinkedListNode<Double> head = new LinkedListNode<Double>(new Double(0));
		LinkedListNode<Double> curr = head, next = null;
		for (int i = 1; i < this.length; i++) {
			next = new LinkedListNode<Double>(new Double(i));
			curr.next = next;
			curr = next;
		}

		assertFalse(RingInLinkedList.hasRing(head));
		
		
		// setup linked list with a ring
		curr = head;
		Random rand = new Random();
		int beforeRing = rand.nextInt(this.length);
		for (int i = 1; i < beforeRing; i++) {
			next = new LinkedListNode<Double>(new Double(i));
			curr.next = next;
			curr = next;
		}

		// link next to a node in front
		LinkedListNode<Double> curr2 = head;
		for (int i = beforeRing - 4; i > 1; i--)
			curr2 = curr2.next;

		curr.next = curr2;

		assertTrue(RingInLinkedList.hasRing(head));
	}
	
	public void testRingStart() {
		// setup linked list with ring
		LinkedListNode<Double> head = new LinkedListNode<Double>(new Double(0));
		LinkedListNode<Double> curr = head, next = null;
		Random rand = new Random();
		int beforeRing = rand.nextInt(this.length);
		for (int i = 1; i < beforeRing; i++) {
			next = new LinkedListNode<Double>(new Double(i));
			curr.next = next;
			curr = next;
		}

		// link next to a node in front
		LinkedListNode<Double> curr2 = head;
		for (int i = beforeRing - 4; i > 1; i--)
			curr2 = curr2.next;

		curr.next = curr2;

		assertEquals(curr2, RingInLinkedList.ringStart(head));
	}
}
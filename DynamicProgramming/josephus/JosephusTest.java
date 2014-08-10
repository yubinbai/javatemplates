package algorithm.clrs.josephus;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class JosephusTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public JosephusTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(JosephusTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testJosephus() {

		int numOfPeople = 5000;
		int killN = 3;

		ArrayList<Integer> permutation = Josephus.permutation(numOfPeople,
				killN);
		ArrayList<Integer> permutation2 = Josephus.permutation2(numOfPeople,
				killN);
		for (int i = 0; i < numOfPeople; i++)
			if (permutation2.get(i) == 0)
				permutation2.set(i, new Integer(numOfPeople));
		// System.out.println(permutation2.toString());
		assertEquals(permutation.get(permutation.size() - 1),
				Josephus.survivor(numOfPeople, killN));
		assertEquals(permutation, permutation2);
	}
}

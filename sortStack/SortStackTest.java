package algorithm.basic.sortStack;

import java.util.Random;
import java.util.Stack;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class SortStackTest extends TestCase {

	int problemSize = 100;

	/**
	 * Rigourous Test :-)
	 */
	public void testSortStack() {

		Stack<Double> s = new Stack<Double>();

		Random rand = new Random();

		for (int i = 0; i < this.problemSize; i++)
			s.push(new Double(rand.nextDouble()));

		s = SortStack.sort(s);

		Double d1 = 0.0, d2 = 0.0;
		while (s.isEmpty() == false) {
			d2 = s.pop();
			assertTrue(d1 <= d2);
			d1 = d2;
			System.out.format("%f \t", d2);
		}
	}
}

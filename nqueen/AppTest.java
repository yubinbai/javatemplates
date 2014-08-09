package algorithm.backtrack.nqueen;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	int problemSize = 14;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testOneSolution() {
		NQueen nq = new NQueen(this.problemSize);
		String solution = nq.getOneSolution();
		System.out.println(solution);
		assertTrue(true);
	}
	
	public void testGetSolution() {
		NQueen nq = new NQueen(this.problemSize);
		ArrayList<String> solution = nq.getAllSolution();
		System.out.println(solution.get(1));
		assertTrue(true);
	}

	public void testCountSolutions() {
		NQueen nq = new NQueen(this.problemSize);
		System.out.println("The number of solutions for the "
				+ this.problemSize + " queen problem is : "
				+ nq.countSolutions());
	}
}

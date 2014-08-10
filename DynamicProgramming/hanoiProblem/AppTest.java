package hanoi.hanoiProblem;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
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

//	public void testRecursive() {
//		RecursiveHanoi.RecursiveSteps(15, "A", "B", "C");
//		assertTrue(true);
//	}

	public void testIterative() {
		IterativeOne i1 = new IterativeOne();
		i1.solutionSteps(20);
		assertTrue(true);
	}

//	public void testIterative2() {
//		IterativeTwo i2 = new IterativeTwo();
//		i2.solutionSteps(15);
//		assertTrue(true);
//	}

	public void testIterative3() {
		IterativeThree i3 = new IterativeThree();
		i3.solutionSteps(20);
		assertTrue(true);
	}

}

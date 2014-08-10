package dynamic.programming.cut_rod;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	int[] rodValue = { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 28 };
	int numOfRepeats = 1000000;
	int resultOfRecursive;
	int testLength = 10;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		RodRecursive rr = new RodRecursive(rodValue);

		this.resultOfRecursive = rr.cutRod(this.testLength);
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
	public void testRecursive() {

		RodRecursive rr = new RodRecursive(rodValue);

		this.resultOfRecursive = rr.cutRod(this.testLength);

		assertTrue(true);
	}

	public void testDynamic() {
		for (int i = 1; i < this.numOfRepeats; i++) {
			RodDynamic rd = new RodDynamic(rodValue);
			assertTrue(this.resultOfRecursive == rd.cutRodTopDown(this.testLength));
		}
	}

	public void testDynamicBottom() {
		for (int i = 1; i < this.numOfRepeats; i++) {
			RodDynamic rd = new RodDynamic(rodValue);
			assertTrue(this.resultOfRecursive == rd.cutRodBottomUp(this.testLength));
		}
	}

	public void testSolution() {

		RodDynamic rd = new RodDynamic(rodValue);
		rd.extendCutRodBottomUp(10);
		System.out.println(rd.printSelection(10));

		assertTrue(true);
	}
}

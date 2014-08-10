package algorithms.dp.matrixchain;

import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	int[] matrixDimension;
	int length = 500;

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

	@Override
	protected void setUp() throws Exception {

		super.setUp();

		// dimension of each matrix A[i] is p[i-1] * p[i]
		// so this holds a length number of matrices
		this.matrixDimension = new int[this.length + 1];

		//
		Random rand = new Random();
		for (int i = 0; i <= this.length; i++)
			matrixDimension[i] = rand.nextInt(1000) + 2;
	}

	/**
	 * Rigourous Test :-)
	 */
	// public void testRecursive() {
	//
	// MatrixChainRecursive mcr = new MatrixChainRecursive(this.matrixDimension,
	// this.length);
	// System.out.println(mcr.calcSum());
	// assertTrue(true);
	// }

	// public void testDynamicCorrectness() {
	// MatrixChainRecursive mcr = new MatrixChainRecursive(this.matrixDimension,
	// this.length);
	// MatrixChainDynamic mcd = new MatrixChainDynamic(this.matrixDimension,
	// this.length);
	//
	// assertTrue(mcr.calcSum() == mcd.calcSum());
	// }

	public void testDynamicPerformance() {
		MatrixChainDynamic mcd = new MatrixChainDynamic(this.matrixDimension,
				this.length);
		mcd.calcSum();
		// System.out.println(mcd.printSoluion());
		assertTrue(true);
	}

	public void testBottomUpCorrectness() {
		MatrixChainBottomUp mcb = new MatrixChainBottomUp(this.matrixDimension,
				this.length);
		MatrixChainDynamic mcd = new MatrixChainDynamic(this.matrixDimension,
				this.length);

		assertTrue(mcb.calcSum() == mcd.calcSum());
		assertTrue(mcb.printSoluion().equals(mcd.printSoluion()));
	}

	public void testBottomUpPerformance() {
		MatrixChainBottomUp mcb = new MatrixChainBottomUp(this.matrixDimension,
				this.length);
		mcb.calcSum();
	}
}

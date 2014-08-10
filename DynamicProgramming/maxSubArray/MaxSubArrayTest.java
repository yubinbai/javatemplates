package algorithms.dp.maxSubArray;

import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MaxSubArrayTest extends TestCase {

	Float data[];
	int length = 1000;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MaxSubArrayTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MaxSubArrayTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testMSA() {

		this.data = new Float[this.length];

		// generate a series of random numbers, 0..length, totally length+1
		Random rand = new Random();
		for (int i = 0; i < this.length; i++) {
			data[i] = rand.nextFloat();
		}

		MaxSubArray<Float> msa = new MaxSubArray<Float>(data, length);

		assertEquals(msa.bruteForce(), msa.kadane());
	}

	public void testMSAzero() {
		this.data = new Float[this.length];

		for (int i = 0; i < this.length; i++) {
			data[i] = (float) -1.0;
		}

		MaxSubArray<Float> msa = new MaxSubArray<Float>(data, length);

		assertEquals(msa.bruteForce(), msa.kadane());
	}
}

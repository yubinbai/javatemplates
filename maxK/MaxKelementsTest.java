package interview.biggest.maxK;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MaxKelementsTest extends TestCase {

	int arraySize = 1000000;
	int sampleSize = 1000;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MaxKelementsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MaxKelementsTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testMaxK() {

		Double data[] = new Double[this.arraySize];

		Random rand = new Random();
		for (int i = 0; i < this.arraySize; i++) {
			data[i] = rand.nextDouble();
		}

		MaxKelements<Double> mk = new MaxKelements<Double>(arraySize, data);

		Collection<Double> bigK = mk.maxK(this.sampleSize);

		// sort the array
		Arrays.sort(data);
		
		assertTrue(bigK.size() == this.sampleSize);
		
		// max K elements are selected
		for (int i = 1; i <= this.sampleSize; i++)
			assertTrue(bigK.contains(data[this.arraySize - i]));

		assertTrue(bigK.size() == this.sampleSize);
	}
}

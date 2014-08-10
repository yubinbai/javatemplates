package algorithm.clrs.mergesort;

import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MergeSortTest extends TestCase {

	int arraySize = 10000;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MergeSortTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MergeSortTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Double data[] = new Double[this.arraySize];

		Random rand = new Random();
		for (int i = 0; i < this.arraySize; i++) {
			data[i] = rand.nextDouble();
		}

		MergeSort<Double> ms = new MergeSort<Double>(arraySize, data);
		data = (Double[]) ms.mergeSort();

		for (int i = 0; i < this.arraySize - 1; i++) {
			assertTrue(data[i] <= data[i + 1]);
		}

	}
}

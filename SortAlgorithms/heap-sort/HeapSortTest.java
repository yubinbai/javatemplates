package algorithm.clrs.heapsort;

import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class HeapSortTest extends TestCase {

	int arraySize = 1000000;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public HeapSortTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(HeapSortTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testHeapSort() {
		Double data[] = new Double[this.arraySize];

		Random rand = new Random();
		for (int i = 0; i < this.arraySize; i++) {
			data[i] = rand.nextDouble();
		}

		HeapSort<Double> qs = new HeapSort<Double>(arraySize, data);
		data = (Double[]) qs.heapSort();

		for (int i = 0; i < this.arraySize - 1; i++) {
			assertTrue(data[i] <= data[i + 1]);
		}

	}
}

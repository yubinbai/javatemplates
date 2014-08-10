package algorithm.quickselect.quickselect;

import java.util.Random;

import algorithm.quickselect.quicksort.QuickSort;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the quick select and quick sort problem
 * 
 * Ensure the selection of n-th member in list at cost O(n)
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */

	int problemSize = 100000;
	int sampleSize = 300;

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
	public void testQuickSort() {

		// constructor
		// init array

		Double[] data = new Double[this.problemSize];

		Random rand = new Random();
		for (int i = 0; i < this.problemSize; i++) {
			data[i] = rand.nextDouble();
		}

		QuickSort<Double> qs = new QuickSort<Double>(this.problemSize, data);
		data = qs.quickSort(0, this.problemSize);

		for (int i = 0; i < this.problemSize - 1; i++)
			assertTrue(data[i] <= data[i + 1]);
	}

	public void testQuickSelect() {
		Double[] data = new Double[this.problemSize];
		Double[] data2 = new Double[this.problemSize];

		Random rand = new Random();
		for (int i = 0; i < this.problemSize; i++) {
			data2[i] = data[i] = rand.nextDouble();

		}

		QuickSort<Double> qs = new QuickSort<Double>(this.problemSize, data);
		data = qs.quickSort(0, this.problemSize);

		int testIndex = 0;

		testIndex = rand.nextInt(this.problemSize);
		QuickSelect<Double> qSel = new QuickSelect<Double>(this.problemSize,
				data2);

		// data[testIndex] is in rank 'testIndex+1'
		// System.out.format("%f %f \n", data[testIndex + 1],
		// qSel.select(testIndex));
		assertTrue(data[testIndex] == qSel.select(testIndex + 1));

	}

	public void testQuickSortEfficienty() {
		for (int i = 0; i < this.sampleSize; i++)
			this.testQuickSort();
	}

	public void testQuickSelectEfficiency() {
		Double[] data = new Double[this.problemSize];

		for (int s = 0; s < this.sampleSize; s++) {
			Random rand = new Random();
			for (int i = 0; i < this.problemSize; i++) {
				data[i] = rand.nextDouble();
			}

			int testIndex = 0;
			testIndex = rand.nextInt(this.problemSize);
			QuickSelect<Double> qSel = new QuickSelect<Double>(
					this.problemSize, data);
			qSel.select(testIndex);

		}

	}

}

package algorithms.merge.medianOfTwoSortedArrays;

import java.util.Arrays;
import java.util.Random;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class MedianOfSortedTest extends TestCase {

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Random rand = new Random();

		int aLength = 2, bLength = 2, maxInt = 1000000;

		int[] a = new int[aLength];
		int[] b = new int[bLength];
		int[] c = new int[aLength + bLength];

		for (int i = 0; i < aLength; i++) {
			a[i] = rand.nextInt(maxInt);
			c[i] = a[i];
		}

		for (int i = 0; i < bLength; i++)
			b[i] = rand.nextInt(maxInt);

		for (int i = aLength; i < aLength + bLength; i++)
			c[i] = b[i - aLength];

		Arrays.sort(a);
		Arrays.sort(b);

		double median = MedianOfSorted.getMedian(a, b);

		Arrays.sort(c);

		double cMedian = 0.0;
		if (c.length % 2 == 0)
			cMedian = (c[c.length / 2 - 1] + c[c.length / 2]) / 2;
		else
			cMedian = c[c.length / 2];

		assertEquals(cMedian, median);
	}
}

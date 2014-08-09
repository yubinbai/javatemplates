package algorithm.sliding.window.duplicateInArray;

import java.util.Arrays;
import java.util.Random;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class RemoveDuplicateTest extends TestCase {

	/**
	 * Rigourous Test :-)
	 */
	public void testRemoveDuplicate() {
		int size = 100;
		Integer[] data = new Integer[size];

		Random rand = new Random();
		for (int i = 0; i < size; i++)
			data[i] = rand.nextInt(size / 2);

		Arrays.sort(data);
		int newSize = RemoveDuplicate.process(data);
		// for (int i = 0; i < size; i++)
		// System.out.println(data[i]);

		for (int i = 0; i < newSize - 1; i++)
			for (int j = i + 1; j < newSize; j++)
				assertTrue(data[i] != data[j]);

		// System.out.println(newSize);

	}
}

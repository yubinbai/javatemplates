package apress.questions.q29;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class GetMajorityTest extends TestCase {

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		int[] array1 = { 1, 2, 3, 2, 2, 2, 5, 4, 2 };

		assertEquals(2, GetMajority.getMajor1(array1));

		assertEquals(2, GetMajority.getMajor2(array1));

		int[] array2 = { 1 };

		assertEquals(1, GetMajority.getMajor1(array2));

		assertEquals(1, GetMajority.getMajor2(array2));
	}
}

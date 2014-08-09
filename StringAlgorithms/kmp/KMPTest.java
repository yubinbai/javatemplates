package algorithms.string.kmp;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class KMPTest extends TestCase {

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		char[] s = "test".toCharArray();
		char[] t = "testestest hello there test!".toCharArray();

		KMP kmp = new KMP(s);
		kmp.searchIn(t);

		System.out.println(kmp.printMemo());
		System.out.println(kmp.printFound());

	}
}

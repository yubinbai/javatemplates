package algorithm.string.karp_rabin;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class KarpRabinTest extends TestCase {

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		char[] s = "test".toCharArray();
		char[] t = "testestest hello there test!".toCharArray();
		KarpRabin kr = new KarpRabin(s);
		kr.searchIn(t);
		String found = kr.printResult();
		System.out.println(found);
	}
}

package algorithm.basic.palindrome;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class PalindromeTest extends TestCase {

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		assertTrue(Palindrome.isPalindrome(0));
		assertTrue(Palindrome.isPalindrome(121));
		assertTrue(Palindrome.isPalindrome(1221));
		assertFalse(Palindrome.isPalindrome(120));
	}
}

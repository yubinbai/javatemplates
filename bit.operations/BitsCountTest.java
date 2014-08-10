package javaLanguage.bit.operations;

import javaLanguage.bit.operations.NaiveCounter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class BitsCountTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public BitsCountTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(BitsCountTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testNaiveCounter() {
		NaiveCounter nc = new NaiveCounter();
		assertEquals(3, nc.bitsCount(7));
		assertEquals(1, nc.bitsCount(4));
	}
	
	public void testKernighan() {
		Kernighan kh = new Kernighan();
		assertEquals(3, kh.bitsCount(7));
		assertEquals(1, kh.bitsCount(4));
	}
	
	public void testLookUp() {
		LookupTable lt = new LookupTable();
		assertEquals(3, lt.bitsCount(7));
		assertEquals(1, lt.bitsCount(4));
	}
}

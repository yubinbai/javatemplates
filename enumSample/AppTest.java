package javaLang.enumSample;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
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
	public void testApp() {
		EnumSample firstDay = new EnumSample(Day.MONDAY);
		firstDay.tellItLikeItIs();
		EnumSample thirdDay = new EnumSample(Day.WEDNESDAY);
		thirdDay.tellItLikeItIs();
		EnumSample fifthDay = new EnumSample(Day.FRIDAY);
		fifthDay.tellItLikeItIs();
		EnumSample sixthDay = new EnumSample(Day.SATURDAY);
		sixthDay.tellItLikeItIs();
		EnumSample seventhDay = new EnumSample(Day.SUNDAY);
		seventhDay.tellItLikeItIs();
		assertTrue(true);
	}
}

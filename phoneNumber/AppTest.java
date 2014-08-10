package interview.practice.phoneNumber;

import java.util.ArrayList;
import java.util.ListIterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	String[] urls = {"http://baidu.com",
			"http://www.upenn.edu/",
			"http://sina.com.cn"
	};
	
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
		
		PhoneNumberFinder pnf = new PhoneNumberFinder();
		for (int i=0;i<urls.length;i++) {
			ArrayList<String> results = pnf.findPhoneNumbers(urls[i]);
			ListIterator<String> iter = results.listIterator();
			while(iter.hasNext())
				System.out.println(iter.next());
		}
		assertTrue(true);
	}
}

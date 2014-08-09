package algorithm.splay.splayTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.math.util.MathUtils;

import com.google.common.collect.Collections2;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class PancakeTest extends TestCase {
	private int pancakeSize = 11;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public PancakeTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(PancakeTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testPancake() {
		int n = this.pancakeSize;
		int[] A = new int[n];

		ArrayList<Integer> permute = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			A[i] = new Integer(i);
			permute.add(A[i]);
		}
		Collection<List<Integer>> allPermutations = Collections2
				.orderedPermutations(permute);
		Iterator<List<Integer>> iter = allPermutations.iterator();
		List<Integer> perm = null;
		Random rand = new Random();
		long permutationIndex = (long) (rand.nextDouble()
				* MathUtils.factorial(this.pancakeSize));
		while (iter.hasNext() && permutationIndex > 0) {
			perm = iter.next();
			permutationIndex--;
		}

		for (int i = 0; i < n; i++) {
			A[i] = new Integer(perm.get(i));
		}
		System.out.print(perm.toString());
		FastSolve FS = new FastSolve(A);
		int[] ans = FS.solve();
		int m = ans.length;
		for (int i = 0; i < m; i++) {
			System.out.print(ans[i]);
			System.out.print(" ");
		}
		System.out.println();

		assertTrue(true);
	}
}

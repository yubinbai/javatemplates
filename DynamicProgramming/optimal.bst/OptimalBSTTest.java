package algorithm.clrs.optimal.bst;

import java.util.Random;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class OptimalBSTTest extends TestCase {

	int numOfKeys = 100;

	/**
	 * Rigourous Test :-)
	 */
	public void testOptmalBST() {
		Random rand = new Random();
		double sum = 0;
		double[] p = new double[this.numOfKeys + 1];
		double[] q = new double[this.numOfKeys + 1];
		for (int i = 1; i <= this.numOfKeys; i++) {
			p[i] = rand.nextDouble();
			q[i] = rand.nextDouble();
			sum += p[i] + q[i];
		}
		q[0] = rand.nextDouble();
		sum += q[0];

		for (int i = 1; i <= this.numOfKeys; i++) {
			p[i] /= sum;
			q[i] /= sum;
		}
		q[0] /= sum;

		OptimalBST obst = new OptimalBST(p, q, this.numOfKeys);
		double expectation = obst.getExpectation();

		BSTree<Double> bst = obst.constructTree();

		double expectationSum = 0;
		// calc the expectation
		for (int i = 1; i <= this.numOfKeys; i++)
			expectationSum += p[i] * bst.searchCost((double) i);

		for (int i = 0; i <= this.numOfKeys; i++)
			expectationSum += q[i] * bst.searchCost(i + 0.5);

		System.out.println("Expected search cost is: " + expectation);
		assertEquals(expectation, expectationSum, 1E-6);

	}
}

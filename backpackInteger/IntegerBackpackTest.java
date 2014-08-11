package backpackInteger;

import java.util.Random;

import junit.framework.TestCase;

/**
 * Test for the integer backpack problem
 */
public class IntegerBackpackTest extends TestCase {

	int numOfItems;
	double backpackCapacity;
	double[] itemValue, itemCost;

	@Override
	protected void setUp() throws Exception {
		numOfItems = 20;
		backpackCapacity = 1.5;

		itemValue = new double[numOfItems];
		itemCost = new double[numOfItems];

		// init the items
		Random rand = new Random();
		for (int i = 0; i < numOfItems; i++) {
			itemValue[i] = rand.nextDouble();
			itemCost[i] = rand.nextDouble();
		}

	}

	/**
	 * Rigourous Test :-)
	 */
	public void testBackpack() {

		BackpackSolver bs = new BackpackSolver(numOfItems, backpackCapacity,
				itemValue, itemCost);

		long prev = System.nanoTime();
		double a = bs.backtrack();

		System.out.println(System.nanoTime() - prev);
		prev = System.nanoTime();

		double b = bs.bruteforce();

		System.out.println(System.nanoTime() - prev);

		assertEquals(a, b, 1E-6);
		// System.out.format("%f, %f", a, b);

	}

}

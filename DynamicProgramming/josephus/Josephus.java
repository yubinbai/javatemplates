package algorithm.clrs.josephus;

import java.util.ArrayList;

/**
 * Josephus problem
 * 
 * Get the Josephus permutation and survivor
 * 
 */
public class Josephus {

	public static ArrayList<Integer> permutation(int numOfPeople, int killN) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> permutation = new ArrayList<Integer>();
		// load the initial list
		// use only 1..numOfPeople nodes
		for (int count = 0; count <= numOfPeople; count++) {
			list.add(new Integer(count));
		}

		int counter = killN;

		while (numOfPeople > 1) {
			permutation.add(list.remove(counter));
			numOfPeople--;
			counter = (counter + killN - 1) % numOfPeople;
			if (counter == 0)
				counter = numOfPeople;
		}
		
		permutation.add(list.get(1));

		return permutation;
	}

	public static Integer survivor(int numOfPeople, int killN) {
		// f(n, k) = ((f(n-1, k) + k - 1) mod n) + 1
		int prevSurvivor = 1;
		int currSurvivor = 0;

		for (int i = 2; i <= numOfPeople; i++) {
			currSurvivor = (prevSurvivor + killN - 1) % i + 1;
			prevSurvivor = currSurvivor;

		}

		return currSurvivor;
	}
	
	public static ArrayList<Integer> permutation2(int numOfPeople, int killN) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> permutation = new ArrayList<Integer>();
		// load the initial list
		// use only 0..numOfPeople-1 nodes
		for (int count = 0; count < numOfPeople; count++) {
			list.add(new Integer(count));
		}

		int counter = killN;

		while (numOfPeople > 1) {
			permutation.add(list.remove(counter));
			numOfPeople--;
			counter = (counter + killN - 1) % numOfPeople;
		}
		
		permutation.add(list.get(0));
		
		return permutation;
	}

}

package hanoi.hanoiProblem;

/**
 * Example of trade-offs: the Towers of Hanoi with memoization For each of the
 * six permutation of (src,dst,tmp) and for each n, save the steps for
 * hanoi(n,src,dst,tmp).
 * 
 * By doing a bottom-up construction, one requires only two rows in the matrix
 * of all solutions: previous and current.
 * 
 * The permutation can be stored as a single octal digit. Each permutation is of
 * the numbers 0, 1, and 2, meaning that all must total to 3, and only an
 * ordered pair needs to be retained. This will be considered to be src and dst.
 * If this ordered pair is considered as a base-3 number, then the range is 01
 * through 21 --- meaning the magnitudes are 1 through 7.
 * 
 * The space complexity, however, is nasty: the two rows have (2)*6 strings ---
 * with three numbers, there are six permutations. Each string, however,
 * contains the full solution for the problem of a given value of n --- pow(2,
 * n) - 1 disk movements.
 * 
 * Above 23: java -Xms1024m -Xmx1024m
 * 
 * Perpetrator: Timothy Rolfe
 */
public class IterativeThree extends HanoiSolution {

	@Override
	public String solutionSteps(int n) {
		//
		return solve(n);
	}

	private String solve(int n) {
		// 6 empty strings for 01 through 21; null
		// for unused cells 0 and 4
		String[] prev = { null, "", "", "", null, "", "", "" };

		String[] curr = new String[8];
		// compute into this
		String[] temp; // interchange prev and curr

		// Final result will be in prev
		for (int i = 1; i <= n; i++) { // Populate curr from prev
			for (int j = 0; j < curr.length; j++) { // 00, 11, and 22 are not
				// allowed
				int src = j / 3, dst = j % 3, tmp = 3 - src - dst;

				if (src == dst)
					continue; // I.e., 00 or 11
				// h(i-1,src,tmp,dst) src to dst h(i-1,tmp,dst,src)
				curr[j] = prev[src * 3 + tmp] + (src * 3 + dst)
						+ prev[tmp * 3 + dst];
			}
			// Swap curr and prev.
			temp = curr;
			curr = prev;
			prev = temp;
		}

		return prev[1];
	}
}

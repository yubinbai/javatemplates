package algorithm.splay.splayTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Collections2;

/*  
 This program takes an array which contains a permutation of 
 0...n-1, and computes a sequence of prefix reversals that sorts 
 the permutation. 

 It's set up to take a permutation on the command line, like this: 

 $ java Pancake 5 4 1 2 0 3 
 6 
 4 
 2 
 3 

 The output is a sequence of prefixes to reverse in order to sort 
 the permutation. 

 D. Sleator, September 2012 
 */

public class Pancake {
	public static void main(String[] args) {
		int n = 5;
		int[] A = new int[n];

		ArrayList<Integer> permute = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			A[i] = new Integer(i);
			permute.add(A[i]);
		}
		Collection<List<Integer>> allPermutations = Collections2
				.orderedPermutations(permute);

		for (List<Integer> perm : allPermutations) {
			for (int i = 0; i < n; i++) {
				A[i] = new Integer(perm.get(i));
			}
			System.out.print(perm.toString());
			FastSolve FS = new FastSolve(A);
			int[] ans = FS.solve();
			int m = ans.length;
			for (int i = 0; i < m; i++)
				System.out.print(ans[i]);
			System.out.println();
		}
	}
}
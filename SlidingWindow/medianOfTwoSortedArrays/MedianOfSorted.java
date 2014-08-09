package algorithms.merge.medianOfTwoSortedArrays;

/**
 * There are two sorted arrays A and B of size m and a.length respectively. Find
 * the median of the two sorted arrays. The overall run time complexity should
 * be O(log (m+n)).
 * 
 * Similar to finding the k-th smallest, the divide and conquer method is a
 * natural approach to this problem. First, we choose Ai and Bj (the middle
 * elements of A and B) where i and j are defined as m/2 and n/2. We made an
 * observation that if Ai <= Bj, then the median must be somewhere between Ai
 * and Bj (inclusive). Therefore, we could dispose a total of i elements from
 * left of Ai and a total of n-j-1 elements to the right of Bj. Please take
 * extra caution not to dispose Ai or Bj, as we might need two middle values to
 * calculate the median (it might also be possible that the two middle values
 * are both in the same array). The case where Ai > Bj is similar.
 * 
 * 
 * Therefore, an important invariant we have to maintain is:
 * 
 * The number of elements being disposed from each array must be the same.
 * 
 * 
 */
public class MedianOfSorted {

	public static double getMedian(int[] a, int[] b) {
		int size = a.length + b.length;
		if (size % 2 == 0) {

			return findEvenMedian(a, b, 0, a.length - 1, 0, b.length - 1,
					size / 2);

		} else
			return findKthSmallest(a, b, 0, a.length - 1, 0, b.length - 1,
					size / 2 + 1);
	}

	private static double findEvenMedian(int[] a, int[] b, int lowA, int highA,
			int lowB, int highB, int k) {
		// if (k > 0)
		// System.out.format("k is bigger than zero. %d \n", k);
		// i is : number before midA : midA - lowA
		// j is : number before midB : midB - lowB

		// invariant: before midA + before midB = k - 1

		// partition mid by weight of length
		int i = (int) ((double) (highA - lowA + 1)
				/ (highA - lowA + highB - lowB + 2) * (k - 1));
		int j = (k - 1) - i;

		int midA = lowA + i;
		int midB = lowB + j;

		// invariant: i + j = k-1
		// Note: A[lowA] = -INF and A[highA + 1] = +INF to maintain invariant
		int Asubmid = ((midA == lowA) ? Integer.MIN_VALUE : a[midA - 1]);
		int Bsubmid = ((midB == lowB) ? Integer.MIN_VALUE : b[midB - 1]);
		int Amid = ((midA == highA + 1) ? Integer.MAX_VALUE : a[midA]);
		int Bmid = ((midB == highB + 1) ? Integer.MAX_VALUE : b[midB]);

		if (Bsubmid < Amid && Amid < Bmid) {
			int nextA = (midA == highA) ? Integer.MAX_VALUE : a[midA + 1];
			return (Amid + Math.min(nextA, b[midB])) / 2;
		} else if (Asubmid < Bmid && Bmid < Amid) {
			int nextB = (midB == highB) ? Integer.MAX_VALUE : b[midB + 1];
			return (Bmid + Math.min(a[midA], nextB)) / 2;
		}

		// if none of the cases above, then it is either:
		if (Amid < Bmid)
			// exclude Amid and below portion
			// exclude Bmid and above portion
			return findEvenMedian(a, b, midA + 1, highA, lowB, midB - 1, k - i
					- 1);
		else
			/* Bmid < Amid */
			// exclude Amid and above portion
			// exclude Bmid and below portion
			return findEvenMedian(a, b, lowA, midA - 1, midB + 1, highB, k - j
					- 1);
	}

	private static int findKthSmallest(int[] a, int[] b, int lowA, int highA,
			int lowB, int highB, int k) {

		// if (k > 0)
		// System.out.format("k is bigger than zero. %d \n", k);
		// i is : number before midA : midA - lowA
		// j is : number before midB : midB - lowB

		// invariant: before midA + before midB = k - 1

		// partition mid by weight of length
		int i = (int) ((double) (highA - lowA + 1)
				/ (highA - lowA + highB - lowB + 2) * (k - 1));
		int j = (k - 1) - i;

		int midA = lowA + i;
		int midB = lowB + j;

		// invariant: i + j = k-1
		// Note: A[lowA] = -INF and A[highA + 1] = +INF to maintain invariant
		int Asubmid = ((midA == lowA) ? Integer.MIN_VALUE : a[midA - 1]);
		int Bsubmid = ((midB == lowB) ? Integer.MIN_VALUE : b[midB - 1]);
		int Amid = ((midA == highA + 1) ? Integer.MAX_VALUE : a[midA]);
		int Bmid = ((midB == highB + 1) ? Integer.MAX_VALUE : b[midB]);

		if (Bsubmid < Amid && Amid < Bmid)
			return Amid;
		else if (Asubmid < Bmid && Bmid < Amid)
			return Bmid;

		// if none of the cases above, then it is either:
		if (Amid < Bmid)
			// exclude Amid and below portion
			// exclude Bmid and above portion
			return findKthSmallest(a, b, midA + 1, highA, lowB, midB - 1, k - i
					- 1);
		else
			/* Bmid < Amid */
			// exclude Amid and above portion
			// exclude Bmid and below portion
			return findKthSmallest(a, b, lowA, midA - 1, midB + 1, highB, k - j
					- 1);
	}

}

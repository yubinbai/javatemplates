package algorithm.quickselect.quickselect;

import java.util.Random;

import algorithm.quickselect.quicksort.QuickSort;

/**
 * QuickSelect
 * 
 * Ensure the selection of n-th member at cost of O(n)
 * 
 * @author Administrator
 * 
 * @param <Key>
 */
public class QuickSelect<Key extends Comparable> extends QuickSort {

	public QuickSelect(int length, Key[] data) {
		super(length, data);

	}

	public Key select(int rank) {

		return quickSelect(0, this.length - 1, rank);

	}

	private Key quickSelect(int start, int end, int rank) {

		// call partition with random pivot
		// randomize is critical to get over the worst condition
		Random rand = new Random();
		int nextInt = rand.nextInt(end - start + 1) + start;
		Key swapValue = (Key) data[nextInt];
		data[nextInt] = data[end];
		data[end] = swapValue;

		int pivotPosition = partition(start, end);

		int sizeOfLess = pivotPosition - start;

		// selection only follow one recursive call
		if (sizeOfLess == rank - 1)
			return (Key) data[pivotPosition];
		else if (sizeOfLess > rank - 1)
			return quickSelect(start, pivotPosition - 1, rank);
		else
			return quickSelect(pivotPosition + 1, end, rank - sizeOfLess - 1);

	}

}

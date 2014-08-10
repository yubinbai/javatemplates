package algorithm.quickselect.quicksort;

/**
 * QuickSort 
 * 
 * Sort array at cost of O(n log n)
 * 
 */
public class QuickSort<Key extends Comparable> {

	// data array
	protected Key[] data;
	protected int length;

	public QuickSort(int length, Key[] data) {
		// constructor
		// array constructed outside, containing 0..length, a total of length+1
		// values
		super();
		this.length = length;
		this.data = data;
	}

	// sort
	public void sort(int start, int end) {
		if (start < end) {
			// call partition
			int pivotPosition = partition(start, end);
			// divide into two arrays
			sort(start, pivotPosition - 1);
			sort(pivotPosition + 1, end);
		}
	}

	// partition
	protected int partition(int start, int end) {
		// pivot = data[n]
		Key pivotValue = data[end];
		Key swapValue = null;

		// leftIndex =
		int leftIndex = start - 1;
		// rightIndex =
		int rightIndex = start;

		// loop for exchange
		for (; rightIndex < end; rightIndex++) {

			if (data[rightIndex].compareTo(pivotValue) <= 0) {
				leftIndex++;
				swapValue = data[rightIndex];
				data[rightIndex] = data[leftIndex];
				data[leftIndex] = swapValue;
			}
		}

		// place the pivot to right place
		swapValue = data[leftIndex + 1];
		data[leftIndex + 1] = pivotValue;
		data[end] = swapValue;

		// return
		return leftIndex + 1;
	}

	public Key[] quickSort(int start, int end) {
		this.sort(0, this.length - 1);
		return data;
	}

}

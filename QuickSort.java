package algorithms.BSTree;

import java.util.Random;

/**
 * Hello world!
 * 
 */
public class QuickSort {

	// data array
	Float[] data;
	int length;

	public QuickSort(int length) {
		// constructor
		// init array
		super();
		this.length = length;
		data = new Float[length + 1];

		Random rand = new Random();
		for (int i = 0; i <= length; i++) {
			data[i] = rand.nextFloat();
		}
	}

	public QuickSort(int length, Float[] data) {
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
	private int partition(int start, int end) {
		// pivot = data[n]
		Float pivotValue = data[end];
		Float swapValue = (float) 0;

		// leftIndex =
		int leftIndex = start - 1;
		// rightIndex =
		int rightIndex = start;

		// loop for exchange
		for (; rightIndex < end; rightIndex++) {

			if (data[rightIndex] <= pivotValue) {
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

	public Float[] getData() {
		return data;
	}

}

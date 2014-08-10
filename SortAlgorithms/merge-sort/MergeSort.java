package algorithm.clrs.mergesort;

import java.util.ArrayList;

/**
 * Hello world!
 * 
 */
public class MergeSort<Key extends Comparable<? super Key>> {

	// data array
	Key[] data;
	int length;

	public MergeSort(int length, Key[] data) {

		// constructor
		// array constructed outside, containing 0..length-1, a total of length
		// values
		super();
		this.length = length;
		this.data = data;

	}

	public Key[] mergeSort() {
		//

		mergeSort(0, length - 1);
		return data;
	}

	private void mergeSort(int start, int end) {
		//
		if (start < end) {
			int mid = (int) Math.floor((start + end) / 2);
			mergeSort(start, mid);
			mergeSort(mid + 1, end);
			merge(start, mid, end);
		}

	}

	private void merge(int start, int mid, int end) {
		//
		int sizeL = mid - start + 1;
		int sizeR = end - mid;
		ArrayList<Key> l = new ArrayList<Key>(), r = new ArrayList<Key>();
		for (int i = start; i <= mid; i++)
			l.add(data[i]);
		for (int i = mid + 1; i <= end; i++)
			r.add(data[i]);

		int i = 0, j = 0;
		int curr = start;
		for (; i < sizeL && j < sizeR && curr <= end; curr++) {
			if (l.get(i).compareTo(r.get(j)) <= 0) {
				data[curr] = l.get(i);
				i++;
			} else {
				data[curr] = r.get(j);
				j++;

			}
		}
		while (i < sizeL && curr <= end) {
			data[curr] = l.get(i);
			i++;
			curr++;
		}
		while (j < sizeR && curr <= end) {
			data[curr] = r.get(j);
			j++;
			curr++;
		}
	}

}

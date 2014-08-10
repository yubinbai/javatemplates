package interview.biggest.maxK;

import java.util.ArrayList;
import java.util.Collection;

public class MinHeap<Key extends Comparable<? super Key>> {
	ArrayList<Key> heapData;
	public int heapSize;

	public MinHeap() {
		heapData = new ArrayList<Key>();
		heapSize = 0;
	}

	public Collection<Key> getCollection() {
		//
		return heapData;
	}

	public Key peekMin() {
		//
		if (heapSize > 0)
			return heapData.get(0);
		else
			return null;
	}

	public boolean addElement(Key key) {
		heapSize++;
		heapData.add(heapSize - 1, key);
		int p = parent(heapSize - 1);
		while (p != 0) {
			minHeapify(p);
			p = parent(p);
		}

		return true;
	}

	public boolean increaseMin(Key key) {
		heapData.set(0, key);
		minHeapify(0);
		return true;
	}

	private void minHeapify(int i) {
		int l = leftChild(i);
		int r = rightChild(i);
		int smallest = 0;
		if (l < heapSize && heapData.get(l).compareTo(heapData.get(i)) < 0)
			smallest = l;
		else
			smallest = i;
		if (r < heapSize
				&& heapData.get(r).compareTo(heapData.get(smallest)) < 0)
			smallest = r;
		if (smallest != i) {
			Key s = heapData.get(i);
			heapData.set(i, heapData.get(smallest));
			heapData.set(smallest, s);
			minHeapify(smallest);
		}
	}

	/**
	 * @param parent
	 * @return the left child index
	 */
	private int leftChild(int parent) {
		return parent * 2 + 1;
	}

	/**
	 * @param parent
	 * @return the right child index
	 */
	private int rightChild(int parent) {
		return parent * 2 + 2;
	}

	private int parent(int child) {
		//
		return (int) Math.floor((child - 1) / 2);
	}

}

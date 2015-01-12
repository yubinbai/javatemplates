package interview.biggest.maxK;

import java.util.Collection;

/**
 * Hello world!
 * 
 */
public class MaxKelements<Key extends Comparable<? super Key>> {

	MinHeap<Key> bigK;
	Key[] data;
	int length, heapSize;

	public MaxKelements(int arraySize, Key[] data) {
		this.data = data;
		this.length = arraySize;
		bigK = new MinHeap<Key>();
	}

	public Collection<Key> maxK(int K) {
		//
		for (int i = 0; i < this.length; i++)
			if (bigK.heapSize < K )
				bigK.addElement(data[i]);
			else if (data[i].compareTo(bigK.peekMin()) > 0) {
				bigK.increaseMin(data[i]);
			}

		return bigK.getCollection();
	}
}

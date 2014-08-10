package algorithm.clrs.heapsort;


/**
 * HeapSort algorithm for a float array
 *
 */
public class HeapSort<Key extends Comparable<? super Key>> {
    Key[] data;
    int length, heapSize;

    /**
     * construct the array fill the array with random numbers
     *
     * @param length
     */

    public HeapSort(int arraySize, Key[] data) {
        // init
        this.data = data;
        this.length = arraySize;
        this.heapSize = 0;
    }

    /**
     * arrange the heap so the i is root of new max heap
     *
     * @param i
     *
     */
    private void maxHeapify(int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        int largest = 0;
        if (l < heapSize && data[l].compareTo(data[i]) > 0)
            largest = l;
        else
            largest = i;
        if (r < heapSize && data[r].compareTo(data[largest]) > 0)
            largest = r;
        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
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

    /**
     * build data[] into max heap, called by the sort
     */
    private void buildMaxHeap() {
        heapSize = length;
        for (int i = (int) Math.floor(length / 2.0) - 1; i >= 0; i--)
            maxHeapify(i);
    }

    /**
     * @param posA
     * @param posB
     *            swap the data in posA and posB
     */
    private void swap(int posA, int posB) {
        Key temp;
        temp = data[posA];
        data[posA] = data[posB];
        data[posB] = temp;
    }

    /**
     * Entry point of the heap sort
     *
     * @return the sorted array
     */
    public Key[] heapSort() {

        buildMaxHeap();
        for (int i = length - 1; i > 0; i--) {
            swap(0, i);
            heapSize--;
            maxHeapify(0);
        }
        return data;
    }

}
package algorithms.dp.maxSubArray;

/**
 * Hello world!
 * 
 */
public class MaxSubArray<Key extends Number> {

	Key data[];
	int length;

	public MaxSubArray(Key[] data, int length) {
		//
		this.data = data;
		this.length = length;
	}

	public double bruteForce() {
		//
		Double maxSum = 0.0, currSum = 0.0;

		for (int i = 0; i < this.length; i++)
			for (int j = i; j < this.length; j++) {
				currSum = 0.0;
				for (int k = i; k <= j; k++)
					currSum += data[k].doubleValue();
				if (currSum > maxSum)
					maxSum = currSum;
			}

		return maxSum;
	}

	public double kadane() {
		// kadane's dynamic programming algorithm
		Double maxSum = 0.0, currSum = 0.0;

		for (int i = 0; i < this.length; i++) {
			currSum = Math.max(data[i].doubleValue(), data[i].doubleValue()
					+ currSum);
			maxSum = Math.max(maxSum, currSum);
		}

		return maxSum;
	}

}

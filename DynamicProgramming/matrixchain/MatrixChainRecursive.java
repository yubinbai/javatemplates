package algorithms.dp.matrixchain;

public class MatrixChainRecursive {
	int[] matrixDimension;
	int length = 100;

	public MatrixChainRecursive(int[] matrixDimension, int length) {
		//
		this.matrixDimension = matrixDimension;
		this.length = length;
	}

	public double calcSum() {

		return calcSum(1, this.length);
	}

	private double calcSum(int start, int end) {
		if (start >= end) {
			return 0;
		} else {
			double sum = Double.MAX_VALUE;
			double tempSum = Double.MAX_VALUE;
			for (int i = start; i < end; i++) {
				tempSum = calcSum(start, i) + calcSum(i + 1, end)
						+ this.matrixDimension[start - 1]
						* this.matrixDimension[i] * this.matrixDimension[end];
				if (tempSum < sum)
					sum = tempSum;
			}
			return sum;
		}
	}

}

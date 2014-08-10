package algorithms.dp.matrixchain;

public class MatrixChainBottomUp {
	int[] matrixDimension;
	int length = 100;
	Double[][] memo;
	int[][] solution;
	String parens;

	public MatrixChainBottomUp(int[] matrixDimension, int length) {
		this.matrixDimension = matrixDimension;
		this.length = length;
		this.memo = new Double[this.length + 1][this.length + 1];
		this.solution = new int[this.length + 1][this.length + 1];
	}

	public double calcSum() {
		//
		for (int i = 0; i <= this.length; i++)
			memo[i][i] = 0.0;
		for (int chainLength = 2; chainLength <= this.length; chainLength++) {
			int start = 1;
			int end = start + chainLength - 1;
			while (end <= this.length) {
				double sum = Double.MAX_VALUE;
				double tempSum = Double.MAX_VALUE;

				for (int i = start; i < end; i++) {
					tempSum = memo[start][i] + memo[i + 1][end]
							+ this.matrixDimension[start - 1]
							* this.matrixDimension[i]
							* this.matrixDimension[end];
					if (tempSum < sum) {
						sum = tempSum;
						solution[start][end] = i;
					}
				}
				memo[start][end] = sum;
				start++;
				end++;
			}
		}
		return memo[1][this.length];
	}

	public String printSoluion() {
		parens = "";
		printOptimalParens(1, this.length);
		return parens;
	}

	private void printOptimalParens(int i, int j) {
		if (i == j)
			parens += "A[" + i + "]";
		else {
			parens += "(";
			this.printOptimalParens(i, solution[i][j]);
			this.printOptimalParens(solution[i][j] + 1, j);
			parens += ")";
		}

	}
}

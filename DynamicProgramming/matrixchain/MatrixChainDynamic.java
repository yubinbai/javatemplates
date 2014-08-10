package algorithms.dp.matrixchain;

public class MatrixChainDynamic {
	int[] matrixDimension;
	int length = 100;
	Double[][] memo;
	int[][] solution;
	String parens;

	public MatrixChainDynamic(int[] matrixDimension, int length) {
		//
		this.matrixDimension = matrixDimension;
		this.length = length;
		this.memo = new Double[this.length + 1][this.length + 1];
		this.solution = new int[this.length + 1][this.length + 1];

	}

	public double calcSum() {
		// TODO Auto-generated method stub
		return calcSum(1, this.length);
	}

	private double calcSum(int start, int end) {
		if (start >= end) {
			memo[start][end] = 0.0;
			return 0;
		} else if (memo[start][end] != null) {
			return memo[start][end];
		} else {

			double sum = Double.MAX_VALUE;
			double tempSum = Double.MAX_VALUE;
			for (int i = start; i < end; i++) {
				tempSum = calcSum(start, i) + calcSum(i + 1, end)
						+ this.matrixDimension[start - 1]
						* this.matrixDimension[i] * this.matrixDimension[end];
				if (tempSum < sum) {
					sum = tempSum;
					solution[start][end] = i;
				}
			}
			memo[start][end] = sum;
			return sum;
		}
	}
	
	public String printSoluion() {
		parens = "";
		printOptimalParens(1, this.length);
		return parens;
	}

	private void printOptimalParens(int i, int j) {
		if(i==j)
			parens += "A[" + i +"]";
		else {
			parens += "(";
			this.printOptimalParens(i, solution[i][j]);
			this.printOptimalParens(solution[i][j] + 1, j);
			parens += ")";
		}
		
	}
}

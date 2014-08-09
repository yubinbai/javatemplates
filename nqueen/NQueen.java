package algorithm.backtrack.nqueen;

import java.util.ArrayList;

public class NQueen {
	int N;
	Integer[] current;
	Boolean[] availableCol;
	Boolean solutionFound = false;
	int solutionCount = 0;
	ArrayList<String> solutionList;

	public NQueen(int problemSize) {
		//
		this.N = problemSize;
		current = new Integer[this.N + 1];
		availableCol = new Boolean[this.N + 1];
		for (int i = 0; i <= this.N; i++)
			availableCol[i] = true;
		this.solutionList = new ArrayList<String>();
	}

	public ArrayList<String> getAllSolution() {
		putQueen(1);
		return this.solutionList;
	}

	private void putQueen(int row) {

		// fill the row
		for (int col = 1; col <= this.N; col++) {
			if (availableCol[col] && isDiagonalValid(row, col)) {
				availableCol[col] = false;
				this.current[row] = col;
				if (row < this.N)
					putQueen(row + 1);
				else {
					this.solutionFound = true;

					// record solution
					String thisSln = "";
					for (int i = 1; i <= this.N; i++)
						thisSln += current[i];
					thisSln += this.isValidSolution(current);
					thisSln += "\n";
					this.solutionList.add(thisSln);
					this.solutionCount++;
				}
				// if row is not filled, then previous selection is problematic
				// prune the branch
				availableCol[col] = true;
			}
		}

	}

	private boolean isDiagonalValid(int row, int col) {
		// check the validity of #row filled with #col with previous ones
		// attack diagonals and off-diag
		if (row <= 1)
			return true;
		for (int i = 1; i < row; i++) {
			if (i - current[i] == row - col || i + current[i] == row + col
					|| col == current[i])
				return false;
		}
		return true;
	}

	public boolean isValidSolution(Integer[] solution) {
		//
		for (int i = 1; i < this.N; i++)
			for (int j = i + 1; j <= this.N; j++) {
				if (i - solution[i] == j - solution[j]
						|| i + solution[i] == j + solution[j]
						|| solution[i] == solution[j])
					return false;
			}
		return true;
	}

	public int countSolutions() {
		putQueen(1);
		return this.solutionCount;
	}

	public String getOneSolution() {
		// 

		putQueenOne(1);
		return this.solutionList.get(0);
	}

	private void putQueenOne(int row) {
		if (this.solutionFound)
			return;
		// fill the row
		for (int col = 1; col <= this.N; col++) {
			if (availableCol[col] && isDiagonalValid(row, col)) {
				availableCol[col] = false;
				this.current[row] = col;
				if (row < this.N)
					putQueenOne(row + 1);
				else {
					this.solutionFound = true;

					// record solution
					String thisSln = "";
					for (int i = 1; i <= this.N; i++)
						thisSln += current[i];
					thisSln += this.isValidSolution(current);
					thisSln += "\n";
					this.solutionList.add(thisSln);
					this.solutionCount++;
					// terminate when found solution
					return;
				}
				// if row is not filled, then previous selection is problematic
				// prune the branch
				availableCol[col] = true;
			}
		}
	}

}

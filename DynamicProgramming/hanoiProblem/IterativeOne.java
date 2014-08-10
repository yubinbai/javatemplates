package hanoi.hanoiProblem;

public class IterativeOne extends HanoiSolution {

	@Override
	public String solutionSteps(int n) {
		// 

		String[][] memoized = new String[(int) (n + 1)][6];
		for (int index = 0; index < 6; index++)
			memoized[1][index] = "Move disk " + 1 + indexToStr(index);

		String result = buildHanoi(memoized, (int) n, "A", "B", "C");

		return result;
	}
	
	private static String buildHanoi(String[][] memoized, int n, String pole1,
			String pole2, String pole3) {
		int index = strToIndex(pole1, pole3);
		if (memoized[n][index] != null) {
			return memoized[n][index];
		} else {
			String result = buildHanoi(memoized, n - 1, pole1, pole3, pole2);
			memoized[n][index] = "Move disk " + n + indexToStr(index);
			result += memoized[n][index];
			result += buildHanoi(memoized, n - 1, pole2, pole1, pole3);
			return result;
		}
	}

}

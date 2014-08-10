package hanoi.hanoiProblem;

public class IterativeTwo extends HanoiSolution {

	@Override
	public String solutionSteps(int n) {
		String[][] memoized = new String[n + 1][6];

		buildHanoi(memoized, (int) n);

		return memoized[(int) n][1];
	}

	private static void buildHanoi(String[][] memoized, int n) {
		
		for (int i = 0; i < 6; i++)
			memoized[1][i] = "Move disk " + 1 + indexToStr(i);

		for (int disk = 2; disk <= n; disk++)
			for (int i = 0; i < 6; i++) {
				// lookupMove = { "AB", "AC", "BA", "BC", "CA", "CB" };
				// 0 1 2 3 4 5
				switch (i) {
				case 0:
					memoized[disk][i] = memoized[disk - 1][1] + "Move disk "
							+ disk + indexToStr(i) + memoized[disk - 1][5];
					break;
				case 1:
					memoized[disk][i] = memoized[disk - 1][0] + "Move disk "
							+ disk + indexToStr(i) + memoized[disk - 1][3];
					break;
				case 2:
					memoized[disk][i] = memoized[disk - 1][3] + "Move disk "
							+ disk + indexToStr(i) + memoized[disk - 1][4];
					break;
				case 3:
					memoized[disk][i] = memoized[disk - 1][2] + "Move disk "
							+ disk + indexToStr(i) + memoized[disk - 1][1];
					break;
				case 4:
					memoized[disk][i] = memoized[disk - 1][5] + "Move disk "
							+ disk + indexToStr(i) + memoized[disk - 1][2];
					break;
				case 5:
					memoized[disk][i] = memoized[disk - 1][4] + "Move disk "
							+ disk + indexToStr(i) + memoized[disk - 1][0];
					break;
				}
			}

	}

}

package hanoi.hanoiProblem;

abstract class HanoiSolution {
	public static final String[] lookupMove = { "AB", "AC", "BA", "BC", "CA",
			"CB" };

	public abstract String solutionSteps(int n);
	
	protected static String indexToStr(int index) {
		return " from " + lookupMove[index].substring(0, 1) + " to "
				+ lookupMove[index].substring(1, 2) + "\n";

	}

	protected static int strToIndex(String a, String b) {
		String str = a + b;
		for (int i = 0; i < 6; i++) {

			if (str.compareTo(lookupMove[i]) == 0)
				return i;
		}
		return 6;
	}
}

package dynamic.programming.cut_rod;

public class RodRecursive {

	int[] rodValue;

	public RodRecursive(int[] rodValue) {
		super();
		this.rodValue = rodValue;
	}

	public int cutRod(int length) {

		// 1 if n == 0
		// 2 return 0
		if (length == 0)
			return 0;
		int value = Integer.MIN_VALUE;
		for (int i = 1; i <= length; i++) {
			value = Math.max(value, rodValue[i] + cutRod(length - i));
		}
		
		return value;

	}

}

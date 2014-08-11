package apress.questions.q29;

/**
 * How do you find the majority element in an array when it exists? The majority
 * is an element that occurs for more than half of the size of the array. For
 * example, the number 2 in the array {1, 2, 3, 2, 2, 2, 5, 4, 2} is the
 * majority element because it appears five times and the size of the array is
 * 9.
 * 
 */
public class GetMajority {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	/**
	 * If there is a majority element in an array, the majority should occur in
	 * the middle of the array when it is sorted. That is to say, the majority
	 * of an array is also the median of the array,
	 * 
	 * @param array1
	 * @return the majority of the numbers
	 */
	public static int getMajor1(int[] numbers) {
		int length = numbers.length;
		int middle = length >> 1;
		int start = 0;
		int end = length - 1;
		
		// find the median
		int index = partition(numbers, start, end);
		while (index != middle) {
			if (index > middle) {
				end = index - 1;
				index = partition(numbers, start, end);
			} else {
				start = index + 1;
				index = partition(numbers, start, end);
			}
		}
		int result = numbers[middle];
		if (!checkMajorityExistence(numbers, result))
			throw new IllegalArgumentException("No majority exisits.");
		return result;
	}

	/**
	 * According to the definition of the majority, the occurrence of a majority
	 * element is greater than the total occurrences of all other elements.
	 * Therefore, this problem can be solved with a new strategy.
	 * 
	 * It scans the array from the beginning to the end, and saves and updates
	 * an element of the array as well as a number for occurrences. When an
	 * element is visited, the occurrence number is incremented if the currently
	 * visited element is the same as the saved one. Otherwise, it decreases the
	 * occurrence number when the visited element is different from the saved
	 * one. When the occurrence number becomes 0, it saves the currently visited
	 * element and sets the occurrence number as 1. The last element that sets
	 * the occurrence number to 1 is the majority element.
	 * 
	 * @param array1
	 * @return
	 */
	public static int getMajor2(int[] numbers) {
		int result = numbers[0];
		int times = 1;
		for (int i = 1; i < numbers.length; ++i) {
			if (times == 0) {
				result = numbers[i];
				times = 1;
			} else if (numbers[i] == result)
				times++;
			else
				times--;
		}
		if (!checkMajorityExistence(numbers, result))
			throw new IllegalArgumentException("No majority exisits.");
		return result;
	}

	private static int partition(int[] numbers, int start, int end) {
		// pivot = numbers[end]
		int pivotValue = numbers[end];
		int swapValue = 0;

		// leftIndex =
		int leftIndex = start - 1;
		// rightIndex =
		int rightIndex = start;

		// loop for exchange
		for (; rightIndex < end; rightIndex++) {

			if (numbers[rightIndex] <= pivotValue) {
				leftIndex++;
				swapValue = numbers[rightIndex];
				numbers[rightIndex] = numbers[leftIndex];
				numbers[leftIndex] = swapValue;
			}
		}

		// place the pivot to right place
		swapValue = numbers[leftIndex + 1];
		numbers[leftIndex + 1] = pivotValue;
		numbers[end] = swapValue;

		// return
		return leftIndex + 1;
	}

	private static boolean checkMajorityExistence(int[] numbers, int result) {
		int times = 0;
		for (int i = 0; i < numbers.length; ++i) {
			if (numbers[i] == result)
				times++;
		}
		return (times * 2 > numbers.length);
	}

}

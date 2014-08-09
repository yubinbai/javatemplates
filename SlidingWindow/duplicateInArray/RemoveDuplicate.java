package algorithm.sliding.window.duplicateInArray;

/**
 * Hello world!
 * 
 */
public class RemoveDuplicate {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	public static int process(Integer[] data) {
		// remove adjacent duplicates in an array
		// use the sliding windows data[0..i] is processed region

		int i = 0, j = 0, size = data.length;
		for (j = 0; j < size; j++)
			if (data[i] != data[j]) {
				i++;
				data[i] = data[j];
			}
		return i + 1;
	}
}

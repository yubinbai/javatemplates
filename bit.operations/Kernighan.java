package javaLanguage.bit.operations;

public class Kernighan implements CountBits {

	public int bitsCount(int number) {
		int c = 0;
		for (; number != 0; c++) {
		// clear the least significant bit set
			number &= number - 1; 
		}
		return c;
	}

}

package javaLanguage.bit.operations;

public class LookupTable implements CountBits {

	public int bitsCount(int number) {
		//
		// To initially generate the table algorithmically:
		char[] BitsSetTable256 = new char[256];
		BitsSetTable256[0] = 0;
		for (char i = 0; i < 256; i++) {
			BitsSetTable256[i] = (char) ((i & 1) + BitsSetTable256[i / 2]);
		}

		return BitsSetTable256[number & 0xff]
				+ BitsSetTable256[(number >> 8) & 0xff]
				+ BitsSetTable256[(number >> 16) & 0xff]
				+ BitsSetTable256[number >> 24];
	}

}

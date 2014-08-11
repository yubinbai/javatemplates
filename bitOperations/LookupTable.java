package bitOperations;

public class LookupTable implements CountBits {
    public static char[] BitsSetTable256 = new char[256];
    static {
        // To initially generate the table algorithmically:
        BitsSetTable256[0] = 0;
        for (char i = 0; i < 256; i++) {
            BitsSetTable256[i] = (char) ((i & 1) + BitsSetTable256[i / 2]);
        }
    }

    public int bitsCount(int number) {

        return BitsSetTable256[number & 0xff]
               + BitsSetTable256[(number >> 8) & 0xff]
               + BitsSetTable256[(number >> 16) & 0xff]
               + BitsSetTable256[number >> 24];
    }

}

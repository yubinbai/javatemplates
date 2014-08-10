package javaLanguage.bit.operations;

public class NaiveCounter implements CountBits {

    public int bitsCount(int number) {
        int c = 0;
        for (; number != 0; number >>= 1) {
            c += number & 1;
        }
        return c;
    }

}

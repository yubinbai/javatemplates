import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Yubin on 11/18/2014.
 */
public class CatalanNumber {
    ArrayList<BigInteger> arr;
    public CatalanNumber() {
        arr = new ArrayList<BigInteger>();
        arr.add(BigInteger.ONE);
        arr.add(BigInteger.ONE);
    }

    /**
     * get i-th catalan number
     * @param i
     */
    public BigInteger get(int i) {
        if (arr.size() > i) {
            return arr.get(i);
        } else {
            for (long j = arr.size() - 1; j <= i; j++) {
                BigInteger c = arr.get(arr.size() - 1);
                arr.add(c.multiply(BigInteger.valueOf(2 * (2 * j + 1))).divide(BigInteger.valueOf(j + 2)));
            }
            return arr.get(i);
        }
    }

    public static void main(String[] args) {
        CatalanNumber c = new CatalanNumber();
        for (int i = 0; i < 100; i++) {
            System.out.format("%d %s\n", i, c.get(i).toString());
        }
    }
}

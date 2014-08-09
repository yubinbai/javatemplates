import java.util.*;
class EulerPhi {
    public static long eulerPhi(long n) {
        if (n == 0) return 0;
        long ans = n;
        for (long x = 2; x * x <= n ; x++) {
            if ( n % x == 0) {
                ans -= ans / x ;
                while ( n % x == 0 ) n /= x;
            }
        }
        if ( n > 1) ans -= ans / n;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("EulerPhi of 44");
        System.out.println(eulerPhi(44));
    }
}
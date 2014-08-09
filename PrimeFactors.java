import java.util.*;
class PrimeFactors {
    public static List<Integer> primeFactors(int numbers) {
        int n = numbers;
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }

    public static void main(String[] args) {
        System.out.println("Primefactors of " + args[0]);
        for (Integer integer : primeFactors(new Integer(args[0]))) {
            System.out.println(integer);
        }
    }
}
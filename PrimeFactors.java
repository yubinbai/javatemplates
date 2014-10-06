import java.util.*;
class PrimeFactors {
    public static List<Integer> primeFactors(int number) {
        int n = number;
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

    public static HashMap<Integer, Integer> primeFactorsMap(int number) {
        int n = number;
        HashMap<Integer, Integer> factors = new HashMap<Integer, Integer>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                if (factors.containsKey(i)) {
                    factors.put(i, factors.get(i) + 1);
                } else {
                    factors.put(i, 1);
                }
                n /= i;
            }
        }
        if (n > 1) {
            if (factors.containsKey(n)) {
                factors.put(n, factors.get(n) + 1);
            } else {
                factors.put(n, 1);
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        System.out.println("Primefactors of " + args[0]);
        // for (Integer integer : primeFactors(new Integer(args[0]))) {
        //     System.out.println(integer);
        // }
        HashMap<Integer, Integer> m = primeFactorsMap(new Integer(args[0]));
        for (Integer integer : m.keySet()) {
            System.out.format("%d %d \n", integer, m.get(integer));
        }
    }
}
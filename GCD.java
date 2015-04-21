import java.util.*;
class GCD {
	static int gcd(int a, int b) {
		int t;
		while (b != 0) {
			t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
	static int gcd2(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}
	public static void main(String[] args) {
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int a = r.nextInt(10000) + 1;
			int b = r.nextInt(10000) + 1;
			if (gcd(a, b) != gcd2(a, b)) {
				System.out.println("Wrong");
			}
		}
	}
}
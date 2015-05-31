/*************************************************************************
 *  Compilation:  javac ExtendedEuclid.java
 *  Execution:    java Euclid p q
 *
 *  Reads two command line parameters p and q and computes the greatest
 *  common divisor of p and q using the extended Euclid's algorithm.
 *  Also prints out integers a and b such that a(p) + b(q) = gcd(p, q).
 *
 *  Sample execution:
 *
 *     % java ExtendedEuclid 36163 21199
 *     gcd(36163, 21199) = 1247
 *     -7(36163) + 12(21199) = 1247
 *
 *     % java ExtendedEuclid 36163 1058
 *     gcd(36163, 1058) = 1
 *     493(36163) + -16851(1058) = 1
 *
 *
 *************************************************************************/

public class ExtendedEuclid {

   //  return array [d, a, b] such that d = gcd(p, q), ap + bq = d
   static int[] gcd(int p, int q) {
      if (q == 0)
         return new int[] { p, 1, 0 };

      int[] vals = gcd(q, p % q);
      int d = vals[0];
      int a = vals[2];
      int b = vals[1] - (p / q) * vals[2];
      return new int[] { d, a, b };
   }

   public static void main(String[] args) {
      // args = new String[] {"10", "9"};
      int p = Integer.parseInt(args[0]);
      int q = Integer.parseInt(args[1]);
      int vals[] = gcd(p, q);
      System.out.println("gcd(" + p + ", " + q + ") = " + vals[0]);
      System.out.println(vals[1] + "(" + p + ") + " + vals[2] + "(" + q + ") = " + vals[0]);
   }
}


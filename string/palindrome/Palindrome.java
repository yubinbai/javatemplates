package algorithm.basic.palindrome;

/**
 * Test if a number is palindrome
 *
 */
public class Palindrome {

    public static boolean isPalindrome(int number) {
        // determine if number is a palindrome
        int reverse = 0, original = number;

        while (number != 0) {
            reverse = reverse * 10 + number % 10;
            number /= 10;
        }

        return (original == reverse);
    }
}

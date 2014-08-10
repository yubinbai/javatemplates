/**
 * Test if a number is palindrome
 *
 */
public class PalindromeNumber {

    public static boolean isPalindrome(int number) {
        // determine if number is a palindrome
        int reverse = 0, original = number;

        while (number != 0) {
            reverse = reverse * 10 + number % 10;
            number /= 10;
        }
        return (original == reverse);
    }

    public static void main(String[] args) {
        System.out.println(PalindromeNumber.isPalindrome(0));
        System.out.println(PalindromeNumber.isPalindrome(121));
        System.out.println(PalindromeNumber.isPalindrome(1221));
        System.out.println(PalindromeNumber.isPalindrome(120));
    }
}

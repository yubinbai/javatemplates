// Next Permutation
// Implement next permutation, which rearranges numbers into the
// lexicographically next greater permutation of numbers.

// If such arrangement is not possible, it must rearrange it as the lowest
// possible order (ie, sorted in ascending order).

// The replacement must be in-place, do not allocate extra memory.

// Here are some examples. Inputs are in the left-hand column and
// its corresponding outputs are in the right-hand column.
// 1,2,3 => 1,3,2
// 3,2,1 => 1,2,3
// 1,1,5 => 1,5,1

import java.util.*;

public class NextPermutation {
    // get the next permutation in place
    public static void nextPermutation(ArrayList<Integer> num) {
        int size = num.size();
        if (size <= 1) return;

        // 1.find the first acending order pair from the bottom
        int low = size - 2;
        int high = size - 1;
        while (num.get(low) >= num.get(high)) {
            if (low == high - 1) {
                low -= 1;
                high = size - 1;
                if (low < 0) break;
            } else {
                high -= 1;
            }
        }

        // reached end of permutation
        if (low < 0) {
            Collections.reverse(num);
            return;
        }

        // 2.swap num[lo] with num[hi] if lo >= 0
        Collections.swap(num, low, high);

        // 3.reverse lo+1 .. end
        low = low + 1;
        high = size - 1;
        while (low < high) {

            Collections.swap(num, low, high);
            low++;
            high--;
        }

        return;
    }

    // get the next permutation in place
    public static int nextPermutation(int[] num) {
        int size = num.length;
        if (size <= 1) return 0;

        // 1.find the first acending order pair from the bottom
        int low = size - 2;
        int high = size - 1;
        while (num[low] >= num[high]) {
            if (low == high - 1) {
                low -= 1;
                high = size - 1;
                if (low < 0) break;
            } else {
                high -= 1;
            }
        }

        // reached end of permutation
        if (low < 0) {
            Arrays.sort(num);
            return 0;
        }

        // 2.swap num[lo] with num[hi] if lo >= 0
        int temp = 0;
        temp = num[low];
        num[low] = num[high];
        num[high] = temp;

        // 3.reverse lo+1 .. end
        low = low + 1;
        high = size - 1;
        while (low < high) {
            temp = num[low];
            num[low] = num[high];
            num[high] = temp;
            low++;
            high--;
        }

        return 1;
    }
    public static void main(String[] args) {
        int[] seq = {1, 2, 2, 3, 4, 5, 6};
        ArrayList<Integer> t = new ArrayList<Integer>();
        for (int i : seq) {
            t.add(i);
        }
        for (int i = 0; i < 100000000; i++) {
            // System.out.println(t);
            NextPermutation.nextPermutation(t);
        }
    }

}

Find the max k number of elements in an unsorted array
============
Solution: Using a min heap to keep the currently found big k elements
    when new element is scanned, compare with the min value in heap and
    decide whether put it in heap

    Results in O (n log k) time complexity and O(k) space
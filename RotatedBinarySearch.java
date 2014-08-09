public class RotatedBinarySearch {
    int[] a;
    public RotatedBinarySearch(int[] data) {
        this.a = data;
    }
    public int find(int x) {
        return search(0, a.length - 1, x);
    }
    private int search(int low, int high, int x) {
        while (low <= high) {
            int mid = (low + high) / 2;
            // comparing x to a[mid]
            if (x == a[mid])
                return mid;
            if (a[low] <= a[mid]) {
                if (x > a[mid]) {
                    low = mid + 1;
                } else { // x <= a[mid]
                    if (x >= a[low])
                        high = mid - 1;
                    else
                        // x < a[low]
                        low = mid + 1;
                }
            } else { // a[low] > a[high]
                if (x < a[mid])
                    high = mid - 1;
                else if (x <= a[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] data = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
        RotatedBinarySearch rs = new RotatedBinarySearch(data);
        assertEquals(8, rs.find(5));
    }
}

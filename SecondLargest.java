public class SecondLargest {

    double[] data;
    int N;

    public SecondLargest(double[] data, int problemSize) {
        this.data = data;
        this.N = problemSize;
    }

    public double getSecondLargest() {
        double max = Double.MIN_VALUE, secondMax = Double.MIN_VALUE;
        for (int i = 0; i < this.N; i++) {
            if (data[i] > max) {
                secondMax = max;
                max = data[i];
            } else if (data[i] > secondMax) {
                secondMax = data[i];
            }
        }
        return secondMax;
    }

}

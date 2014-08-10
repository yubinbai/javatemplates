package hanoi.hanoiProblem;

public class RecursiveHanoi {
    public static double RecursiveSolution(double n) {
        if (n == 1)
            return 1;
        else
            return RecursiveSolution(n - 1) * 2 + 1;
    }

    public static void RecursiveSteps(double n, String pole1, String pole2, String pole3) {
        if (n == 0) {
            return;
        }

        else {
            RecursiveSteps(n - 1, pole1, pole3, pole2);
            String s = String.format("Move disk %1.0f from %s to %s\n", n, pole1, pole3);
            RecursiveSteps(n - 1, pole2, pole1, pole3);
        }

    }
}

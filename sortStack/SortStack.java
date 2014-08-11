import java.util.*;

/**
 * Sort the stack s using an aux stack r
 *
 */
public class SortStack {

    public static <T extends Comparable<T>> Stack<T> sort(Stack<T> s) {
        // sort the stack

        Stack<T> r = new Stack<T>();
        while (!s.isEmpty()) {
            T tmp = s.pop();
            while (!r.isEmpty() && r.peek().compareTo(tmp) < 0) {
                s.push(r.pop());
            }
            r.push(tmp);
        }
        return r;

    }

    public static void main(String[] args) {
        Stack<Double> s = new Stack<Double>();

        Random rand = new Random();

        for (int i = 0; i < 1000; i++)
            s.push(new Double(rand.nextDouble()));

        s = SortStack.sort(s);

        Double d1 = 0.0, d2 = 0.0;
        while (s.isEmpty() == false) {
            d2 = s.pop();
            if (d1 > d2) System.out.format("%f %f \n", d1, d2);
            d1 = d2;
        }
    }
}

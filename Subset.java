/**
 * Created by ybai on 12/5/14.
 */
import java.util.*;

public class Subset {
    ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
    Stack<Integer> path = new Stack<Integer>();
    int[] seq;

    public Subset(int[] seq) {
        Arrays.sort(seq);
        this.seq = seq;
        subset(0);
    }

    private void subset(int step) {
        ArrayList<Integer> curr = new ArrayList<Integer>(path);
        results.add(curr);
        for (int i = step; i < seq.length; i++) {
            if (i != step && seq[i] == seq[i - 1]) {
                continue;
            }
            path.push(seq[i]);
            subset(i + 1);
            path.pop();
        }
    }

    private ArrayList<ArrayList<Integer>> getSubSets() {
        return results;
    }

    public static void main(String[] args) {
        int[] seq = new int[] {2, 4, 5, 6};
        Subset s = new Subset(seq);
        ArrayList<ArrayList<Integer>> subset = s.getSubSets();
        for (ArrayList<Integer> arr : subset) {
            System.out.format("%s\n", arr);
        }
    }

}

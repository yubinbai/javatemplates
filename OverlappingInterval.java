import java.util.*;
public class OverlappingInterval {
    class Point implements Comparable<Point> {
        int pos, ordinal;
        boolean isStart;
        public Point(int _pos, int _ordinal, boolean _isStart) {
            pos = _pos; ordinal = _ordinal; isStart = _isStart;
        }
        public int compareTo(Point other) {
            if (pos == other.pos) {
                return isStart ? -1 : 1;
            } else {
                return Integer.compare(pos, other.pos);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> findall(int[][] intervals) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        ArrayList<Point> pts = new ArrayList<Point>();

        for (int i = 0; i < intervals.length; ++i) {
            int[] interval = intervals[i];
            pts.add(new Point(interval[0], i, true));
            pts.add(new Point(interval[1], i, false));
        }
        Collections.sort(pts);
        HashSet<Integer> currOpen = new HashSet<Integer>();
        for (Point pt : pts) {
            if (pt.isStart) {
                currOpen.add(pt.ordinal);
            } else {
                for (int ord1 : currOpen) {
                    if (ord1 != pt.ordinal) {
                        int[] int1 = intervals[ord1];
                        int[] int2 = intervals[pt.ordinal];
                        ArrayList<Integer> overlap = new ArrayList<Integer>();
                        overlap.add(int1[0]);
                        overlap.add(int1[1]);
                        overlap.add(int2[0]);
                        overlap.add(int2[1]);
                        ret.add(overlap);
                    }
                }
                currOpen.remove(pt.ordinal);
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        OverlappingInterval o = new OverlappingInterval();
        int[][] arr = new int[][] {{1, 3}, {12, 14}, {2, 4}, {13, 15}, {1, 10}};
        for (ArrayList<Integer> a : o.findall(arr)) {
            System.out.println(a);
        }
    }
}
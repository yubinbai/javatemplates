import java.util.*;
class Point {
    double x, y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
class Points {
    public static Point makeVector(Point p1, Point p2) {
        return new Point(p2.x - p1.x, p2.y - p1.y);
    }
    public static double crossProduct(Point p1, Point p2, Point p3) {
        Point v1 = makeVector(p1, p2);
        Point v2 = makeVector(p1, p3);
        return crossProduct(v1, v2);
    }
    public static double crossProduct(Point v1, Point v2) {
        return v1.x * v2.y - v2.x * v1.y;
    }
}
public class Triangle {
    Point a, b, c;
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public boolean isTriangle() {
        double[] d = new double[3];
        d[0] = Math.hypot(a.x - b.x, a.y - b.y);
        d[1] = Math.hypot(a.x - c.x, a.y - c.y);
        d[2] = Math.hypot(b.x - c.x, b.y - c.y);
        Arrays.sort(d);
        return d[0] + d[1] > d[2];
    }
    public boolean hasInside(Point p) {
        boolean b1, b2, b3;
        b1 = Points.crossProduct(p, a, b) < 0;
        b2 = Points.crossProduct(p, b, c) < 0;
        b3 = Points.crossProduct(p, c, a) < 0;
        return (b1 == b2) && (b2 == b3);
    }
    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(1, 0);
        Point c = new Point(-1, -1);
        Triangle t = new Triangle(a, b, c);
        System.out.format("%s\n", t.isTriangle());
        System.out.format("%s\n", t.hasInside(new Point(0, 0)));
        System.out.format("%s\n", t.hasInside(new Point(1, 1)));
    }
}
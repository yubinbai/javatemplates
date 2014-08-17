package algorithm.clrs.BFS;

public class Vertex implements Comparable<Vertex> {
    String name;
    Color color;
    Vertex prevVertex;
    double distance;

    public Vertex(String name) {
        this.name = name;
        this.color = Color.WHITE;
        this.prevVertex = null;
    }

    public int compareTo(Vertex other) {
        return this.distance < other.distance ? -1 : 1;
    }

}

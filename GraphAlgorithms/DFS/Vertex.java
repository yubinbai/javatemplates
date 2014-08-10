package algorithm.clrs.DFS;

public class Vertex implements Comparable<Vertex> {
	String name;
	Color color;
	Vertex prevVertex;
	int discoveryT, finishT;

	public Vertex(String name) {
		//
		this.name = name;
		this.color = Color.WHITE;
		this.prevVertex = null;
	}

	public int compareTo(Vertex other) {

		return this.finishT < other.finishT ? -1 : 1;
	}

}

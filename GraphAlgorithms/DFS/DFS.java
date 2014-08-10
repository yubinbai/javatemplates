package algorithm.clrs.DFS;

import java.util.Arrays;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

/**
 * Hello world!
 * 
 */
public class DFS<E> {

	DirectedGraph<Vertex, E> graph;
	Vertex[] visitedVertices;
	Set<Vertex> vertices;

	public static int time = 0;

	public DFS(DefaultDirectedGraph<Vertex, E> graph) {
		this.graph = graph;
		this.vertices = graph.vertexSet();

		time = 0;
	}

	public Vertex[] depthFirstTree(Vertex source) {
		for (Vertex v : graph.vertexSet()) {
			v.color = Color.WHITE;
			v.discoveryT = v.finishT = Integer.MAX_VALUE;
		}
		DFSVisit(source);

		this.visitedVertices = new Vertex[vertices.size()];
		int i = 0;
		for (Vertex v : vertices)
			if (i < vertices.size()) {
				visitedVertices[i] = v;
				i++;
			}

		Arrays.sort(visitedVertices);
		return visitedVertices;
	}

	/**
	 * Get the depth first generated tree from 'source'
	 * 
	 * @param lastVertex
	 * @return
	 */
	public Vertex[] depthFirst(Vertex source) {

		DFSVisit(source);
		for (Vertex v : graph.vertexSet())
			if (v.color == Color.WHITE)
				DFSVisit(v);

		this.visitedVertices = new Vertex[vertices.size()];
		int i = 0;
		for (Vertex v : vertices)
			if (i < vertices.size()) {
				visitedVertices[i] = v;
				i++;
			}

		Arrays.sort(visitedVertices);
		return visitedVertices;
	}

	private void DFSVisit(Vertex v) {
		//
		time++;
		v.discoveryT = time;
		v.color = Color.GRAY;
		for (Vertex adj : this.vertices)
			if (graph.containsEdge(v, adj)) {
				if (adj.color == Color.WHITE) {
					adj.prevVertex = v;
					DFSVisit(adj);
				}
			}
		v.color = Color.BLACK;
		time++;
		v.finishT = time;

	}

}

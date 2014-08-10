package algorithm.clrs.BFS;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedGraph;

/**
 * Hello world!
 * 
 */
public class BFS<E extends Comparable<E>> {

	private DefaultDirectedGraph<Vertex, E> graph;
	private Set<Vertex> vertices;
	Vertex[] visitedVertices;

	public BFS(DefaultDirectedGraph<Vertex, E> graph) {
		//
		this.graph = graph;
		this.vertices = graph.vertexSet();
	}

	public Vertex[] breadthFirstTree(Vertex source) {
		// init
		for (Vertex v : vertices) {
			v.color = Color.WHITE;
			v.distance = Double.MAX_VALUE;
			v.prevVertex = null;
		}
				
		source.color = Color.GRAY;
		source.distance = 0;
		source.prevVertex = null;
		
		// start bfs
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		q.offer(source);
		
		while(!q.isEmpty()) {
			Vertex u = q.poll();
			for (Vertex adj : vertices)
				if (graph.containsEdge(u,adj) && adj.color == Color.WHITE) {
					adj.color = Color.GRAY;
					adj.distance = u.distance + 1;
					adj.prevVertex = u;
					q.offer(adj);
				}
			u.color = Color.BLACK;
		}
		
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

}

package algorithm.clrs.Dijkstra;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import junit.framework.TestCase;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Unit test for simple App.
 */
public class DijkstraTest extends TestCase {

	int numberOfEdges = 100;

	/**
	 * Rigourous Test :-)
	 */
	public void testDijkstra() {
		// Create the graph object; it is null at this point
		DefaultDirectedWeightedGraph<Integer, DefaultEdge> dag = new DefaultDirectedWeightedGraph<Integer, DefaultEdge>(
				DefaultEdge.class);

		// add a number of vertices
		for (int i = 0; i < this.numberOfEdges; i++) {
			Integer nextV = new Integer(i);
			dag.addVertex(nextV);
		}

		Set<Integer> vertices = dag.vertexSet();
		// fill in edges
		Random rand = new Random();
		for (Integer one : vertices)
			for (Integer other : vertices) {
				if (other != one) {
					DefaultWeightedEdge edge = new DefaultWeightedEdge();
					dag.addEdge(one, other, edge);
					dag.setEdgeWeight(edge, rand.nextDouble());
				}
			}

		// get shortest path in two ways
		Iterator<Integer> iter = vertices.iterator();
		Integer startVertex = iter.next();
		Integer endVertex = iter.next();

		DijkstraShortestPath<Integer, DefaultEdge> path1 = new DijkstraShortestPath<Integer, DefaultEdge>(
				dag, startVertex, endVertex);

		Dijkstra path2 = new Dijkstra(dag, startVertex, endVertex);

		assertEquals(path1.getPathLength(), path2.getPathLength());

	}
};

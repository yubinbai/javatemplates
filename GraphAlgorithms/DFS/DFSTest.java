package algorithm.clrs.DFS;

import java.util.Random;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

/**
 * Unit test for simple App.
 */
public class DFSTest extends TestCase {
	int numOfVertices = 10;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public DFSTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(DFSTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testDFS() {

		DefaultDirectedGraph<Vertex, Double> graph = new DefaultDirectedGraph<Vertex, Double>(
				Double.class);
		Vertex v = null;
		for (int i = 0; i < this.numOfVertices; i++) {
			v = new Vertex("v" + i);
			graph.addVertex(v);
		}

		Vertex lastVertex = v;

		Set<Vertex> vertices = graph.vertexSet();

		Random rand = new Random();
		Double nextEdge = 0.0;
		for (Vertex start : vertices)
			for (Vertex end : vertices)
				if (!start.equals(end) && !graph.containsEdge(start, end)) {
					nextEdge = rand.nextDouble();
					if (nextEdge > 0.9)
						graph.addEdge(start, end, nextEdge);
				}

		System.out.println("Vertices: " + vertices.size());
		System.out.println("Edges: " + graph.edgeSet().size());

		DepthFirstIterator<Vertex, Double> dfsIter = new DepthFirstIterator<Vertex, Double>(
				graph, lastVertex);

		DFS<Double> dfs = new DFS<Double>(graph);
		Vertex[] dfsResult = dfs.depthFirstTree(lastVertex);

		for (int i = 0; i < this.numOfVertices && dfsIter.hasNext(); i++) {
			System.out.println(dfsResult[i] + " " + dfsIter.next());
		}
	}
}

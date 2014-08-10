package algorithm.lp.networkflow;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jgrapht.alg.EdmondsKarpMaximumFlow;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

/**
 * Unit test for simple App.
 */
public class NetworkFlowTest extends TestCase {
	int numOfVertices = 30;
	int vertexNumMultiplier = 4;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public NetworkFlowTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(NetworkFlowTest.class);
	}

	public void testGLPKNative() {
		DefaultDirectedGraph<String, Double> graph = new DefaultDirectedGraph<String, Double>(
				Double.class);
		for (int i = 0; i < this.numOfVertices; i++)
			graph.addVertex("v" + i);

		Set<String> vertices = graph.vertexSet();

		Random rand = new Random();
		for (String start : vertices)
			for (String end : vertices)
				if (!start.equals(end) && !graph.containsEdge(start, end)) {
					graph.addEdge(start, end, rand.nextDouble());
				}

		// System.out.println("Vertices: " + vertices.size());
		// System.out.println("Edges: " + graph.edgeSet().size());
		// System.out.println(graph.vertexSet().toString());

		NetworkFlow<String, Double> nf = new NetworkFlow<String, Double>(graph);
		Iterator<String> iter = vertices.iterator();
		String start = iter.next();
		String end = iter.next();

		nf.calculateMaximumFlow(start, end);
		// System.out.println("The flow is " + nf.getMaximumFlowValue());
		assertTrue(true);
	}

	public void testCompareWithEdmondsKarp() {
		DirectedWeightedMultigraph<String, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		for (int i = 0; i < this.numOfVertices; i++)
			graph.addVertex("v" + i);

		Set<String> vertices = graph.vertexSet();

		Random rand = new Random();
		for (String start : vertices)
			for (String end : vertices)
				if (!start.equals(end) && !graph.containsEdge(start, end)) {
					Double newDouble = new Double(rand.nextDouble());
					DefaultWeightedEdge e = graph.addEdge(start, end);
					graph.setEdgeWeight(e, newDouble.doubleValue());

					// System.out.println(graph.getEdgeWeight(e));
				}

		// System.out.println(graph.vertexSet().toString());

		NetworkFlow<String, DefaultWeightedEdge> lpnf = new NetworkFlow<String, DefaultWeightedEdge>(
				graph);
		Iterator<String> iter = vertices.iterator();
		String source = iter.next();
		String sink = iter.next();
		lpnf.calculateMaximumFlow(source, sink);

		// compare with the Edmonds Karp method
		EdmondsKarpMaximumFlow<String, DefaultWeightedEdge> eknf = new EdmondsKarpMaximumFlow<String, DefaultWeightedEdge>(
				graph);
		eknf.calculateMaximumFlow(source, sink);

		// assertEquals(nf.getMaximumFlowValue(), eknf.getMaximumFlowValue(),
		// 1E-6);

		Map<DefaultWeightedEdge, Double> m = eknf.getMaximumFlow();
		Map<DefaultWeightedEdge, Double> m2 = lpnf.getMaximumFlow();
		for (DefaultWeightedEdge e : graph.edgeSet()) {
			assertTrue(m.get(e) >= 0);
			assertTrue(m.get(e) <= graph.getEdgeWeight(e));
			assertTrue(m2.get(e) >= 0);
			assertTrue(m2.get(e) <= graph.getEdgeWeight(e));
		}

		System.out.println("The flow is " + lpnf.getMaximumFlowValue());
		System.out.println("The flow is " + eknf.getMaximumFlowValue());
	}
	
	public void testLPEfficiency() {
		DirectedWeightedMultigraph<String, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		for (int i = 0; i < this.numOfVertices * this.vertexNumMultiplier; i++)
			graph.addVertex("v" + i);

		Set<String> vertices = graph.vertexSet();

		Random rand = new Random();
		for (String start : vertices)
			for (String end : vertices)
				if (!start.equals(end) && !graph.containsEdge(start, end)) {
					Double newDouble = new Double(rand.nextDouble());
					DefaultWeightedEdge e = graph.addEdge(start, end);
					graph.setEdgeWeight(e, newDouble.doubleValue());

					// System.out.println(graph.getEdgeWeight(e));
				}

		// System.out.println(graph.vertexSet().toString());

		NetworkFlow<String, DefaultWeightedEdge> lpnf = new NetworkFlow<String, DefaultWeightedEdge>(
				graph);
		Iterator<String> iter = vertices.iterator();
		String source = iter.next();
		String sink = iter.next();
		lpnf.calculateMaximumFlow(source, sink);
		System.out.println("The flow is " + lpnf.getMaximumFlowValue());
	}

	public void testEKEfficiency() {
		DirectedWeightedMultigraph<String, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		for (int i = 0; i < this.numOfVertices * this.vertexNumMultiplier; i++)
			graph.addVertex("v" + i);

		Set<String> vertices = graph.vertexSet();

		Random rand = new Random();
		for (String start : vertices)
			for (String end : vertices)
				if (!start.equals(end) && !graph.containsEdge(start, end)) {
					Double newDouble = new Double(rand.nextDouble());
					DefaultWeightedEdge e = graph.addEdge(start, end);
					graph.setEdgeWeight(e, newDouble.doubleValue());

					// System.out.println(graph.getEdgeWeight(e));
				}

		// System.out.println(graph.vertexSet().toString());

		Iterator<String> iter = vertices.iterator();
		String source = iter.next();
		String sink = iter.next();

		// compare with the Edmonds Karp method
		EdmondsKarpMaximumFlow<String, DefaultWeightedEdge> eknf = new EdmondsKarpMaximumFlow<String, DefaultWeightedEdge>(
				graph);
		eknf.calculateMaximumFlow(source, sink);
		
		System.out.println("The flow is " + eknf.getMaximumFlowValue());

	}

}

package algorithm.lp.networkflow;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.GlpkException;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;
import org.jgrapht.DirectedGraph;

/**
 * Calculate the max flow in a network using linear programmings
 * 
 */
public class NetworkFlow<V, E> {
	DirectedGraph<V, E> graph, flowGraph;
	Set<V> vertices;
	Map<E, Double> maximumFlow; // optimized maximum flow
	Double maximumFlowValue; // current maximum flow value
	int numOfEdges;

	public NetworkFlow(DirectedGraph<V, E> graph) {
		// init the flow graph
		this.graph = graph;
		this.vertices = graph.vertexSet();
		this.numOfEdges = graph.edgeSet().size();
		this.maximumFlow = new HashMap<E, Double>();
	}

	/**
	 * Same as calcMaxFlow above, just using glpk native interface
	 * 
	 * @param source
	 * @param sink
	 * @return
	 */
	public void calculateMaximumFlow(V source, V sink) {

		// use glpk's own java interface
		glp_prob lp;
		glp_smcp parm;
		SWIGTYPE_p_int ind;
		SWIGTYPE_p_double val;

		try {
			// Create problem
			lp = GLPK.glp_create_prob();
			// System.out.println("Problem created");
			GLPK.glp_set_prob_name(lp, "MaxFlow");

			// define actual flow as variables for the linear optimization
			// columns as flow on each edge
			// flow can be 0 < f < capacity of edge

			Hashtable<E, Integer> colIDTable = new Hashtable<E, Integer>();
			for (E e : graph.edgeSet()) {
				int colID = GLPK.glp_add_cols(lp, 1);
				// record the ids and names
				colIDTable.put(e, colID);
				GLPK.glp_set_col_name(lp, colID, e.toString());
				GLPK.glp_set_col_kind(lp, colID, GLPKConstants.GLP_CV);
				GLPK.glp_set_col_bnds(lp, colID, GLPKConstants.GLP_DB, 0,
						graph.getEdgeWeight(e));
			}

			int numOfCol = graph.edgeSet().size();

			// flow conservation
			// for each vertex, the flow in == flow out
			for (V v : vertices)
				if (!v.equals(source) && !v.equals(sink)) {

					int rowID = GLPK.glp_add_rows(lp, 1);
					GLPK.glp_set_row_name(lp, rowID, "row" + rowID);
					GLPK.glp_set_row_bnds(lp, rowID, GLPKConstants.GLP_FX, 0, 0);

					ind = GLPK.new_intArray(numOfCol + 1);
					val = GLPK.new_doubleArray(numOfCol + 1);
					for (int i = 1; i <= numOfCol; i++) {
						GLPK.intArray_setitem(ind, i, i);
						GLPK.doubleArray_setitem(val, i, 0.0);
					}

					for (V other : vertices)
						if (!v.equals(other)) {
							GLPK.doubleArray_setitem(val,
									colIDTable.get(graph.getEdge(v, other)),
									-1.0);
							GLPK.doubleArray_setitem(val,
									colIDTable.get(graph.getEdge(other, v)),
									1.0);
						}

					GLPK.glp_set_mat_row(lp, rowID, numOfCol, ind, val);
				}

			// Define objective
			// (maximize the flow out of source - flow into source)
			GLPK.glp_set_obj_name(lp, "flow");
			GLPK.glp_set_obj_dir(lp, GLPKConstants.GLP_MAX);
			for (V other : vertices)
				if (!source.equals(other)) {
					GLPK.glp_set_obj_coef(lp,
							colIDTable.get(graph.getEdge(source, other)), 1.0);
					GLPK.glp_set_obj_coef(lp,
							colIDTable.get(graph.getEdge(other, source)), -1.0);
				}

			// Solve model
			parm = new glp_smcp();
			GLPK.glp_init_smcp(parm);
			int ret = GLPK.glp_simplex(lp, parm);

			// Retrieve solution
			if (ret == 0) {
				// save results
				this.maximumFlowValue = GLPK.glp_get_obj_val(lp);
				for (E e : graph.edgeSet()) {
					this.maximumFlow.put(e,
							GLPK.glp_get_col_prim(lp, colIDTable.get(e)));
				}

			} else {
				System.err.println("The problem could not be solved");
			}
			// Free memory
			GLPK.glp_delete_prob(lp);

		} catch (GlpkException ex) {
			ex.printStackTrace();
		}

	}

	public Double getMaximumFlowValue() {
		//
		return this.maximumFlowValue;
	}

	public Map<E, Double> getMaximumFlow() {
		//
		return this.maximumFlow;
	}

}

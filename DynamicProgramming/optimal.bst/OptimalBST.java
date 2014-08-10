package algorithm.clrs.optimal.bst;

/**
 * Get array of keys and dummy keys, with their probability of being searched,
 * use dynamic programming to calculate for a optimal binary search tree
 * 
 */
public class OptimalBST {
	double[] p, q;
	int numOfKeys;
	double[][] e, w;
	int[][] root;

	public OptimalBST(double[] p, double[] q, int numOfKeys) {
		this.p = p; // real keys
		this.q = q; // dummy keys
		this.numOfKeys = numOfKeys;
		e = new double[numOfKeys + 2][numOfKeys + 1]; // expectation memo
		w = new double[numOfKeys + 2][numOfKeys + 1]; // weight memo
		root = new int[numOfKeys + 1][numOfKeys + 1]; // decisions
	}

	/**
	 * Use dynamic programming to solve the optimal bst
	 * 
	 * Build the solution triangle bottom-up, save the root values in array
	 * root[][]
	 * 
	 * @return the cost expectation of searching the tree
	 */
	public double getExpectation() {
		for (int i = 1; i <= numOfKeys + 1; i++)
			e[i][i - 1] = w[i][i - 1] = q[i - 1];

		// sub tree with 'l' number of real keys
		for (int l = 1; l <= numOfKeys; l++)
			// iterate over this layer of the solution triangle
			for (int i = 1; i <= numOfKeys - l + 1; i++) {
				// i..j sub tree
				int j = i + l - 1;
				e[i][j] = Double.MAX_VALUE;
				w[i][j] = w[i][j - 1] + p[j] + q[j];

				// find the best root position r
				for (int r = i; r <= j; r++) {
					double t = e[i][r - 1] + e[r + 1][j] + w[i][j];
					if (t < e[i][j]) {
						e[i][j] = t;
						root[i][j] = r;
					}
				}

			}

		return e[1][numOfKeys];
	}

	/**
	 * Call the tree constructor
	 * 
	 * @return Optimized binary search tree
	 */
	public BSTree<Double> constructTree() {

		Node<Double> r = constructTree(1, numOfKeys);
		BSTree<Double> tree = new BSTree<Double>(r, numOfKeys);
		return tree;
	}

	/**
	 * Construct the optimal bst from the previous results
	 * 
	 * @param i
	 *            left most key in the sub tree
	 * @param j
	 *            right most key in the sub tree
	 * @return constructed binary search tree from the root[][] decision
	 */
	private Node<Double> constructTree(int i, int j) {

		if (i <= j) {
			int r = root[i][j];
			Node<Double> rt = new Node<Double>((double) r);
			rt.leftChild = constructTree(i, r - 1);
			rt.rightChild = constructTree(r + 1, j);
			return rt;
		} else
			return null;
	}
}

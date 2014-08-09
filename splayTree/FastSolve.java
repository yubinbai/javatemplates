package algorithm.splay.splayTree;

class FastSolve {
	int[] A;
	int n;

	Node[] ptr;

	FastSolve(int[] aa) {
		A = aa;
		n = A.length;
	}

	void build_initial_tree() {
		Node built = null;
		ptr = new Node[n];
		for (int i = 0; i < n; i++) {
			// build tree to linear left
			// ptr[i] stores the pointer to number i
			built = ptr[A[i]] = new Node(built, null, A[i]);
		}
	}

	int[] solve() {
		build_initial_tree();

		int[] moves = new int[2 * n];
		int m = 0;

		for (int k = n - 1; k >= 0; k--) {
			Node x = ptr[k];
			Splay.splay(x);
			// j is the current position of k
			int j = (x.l == null) ? 0 : x.l.size;
			if (j != k) { // if j=k then k is already in place
				if (j > 0) { // move k to the front
					moves[m++] = j + 1;
					Node rightSub = x.r;
					rightSub.p = null;
					x.r = null;
					x.updateSize();
					x.flip = !x.flip;
					Splay.join(x, rightSub);
					Splay.splay(x);
				}
				moves[m++] = k + 1;
				x.flip = !x.flip; // move k to the back
			}
			Splay.delete(x);
		}
		int[] ret = new int[m];
		for (int i = 0; i < m; i++)
			ret[i] = moves[i];
		return ret;
	}
}
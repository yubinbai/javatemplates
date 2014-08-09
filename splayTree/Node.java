package algorithm.splay.splayTree;

class Node {
	int size;
	boolean flip;
	Node l, r, p;
	int id;

	Node(Node ll, Node rr, int iidd) {
		id = iidd;
		l = ll;
		r = rr;
		p = null;
		if (l != null)
			l.p = this;
		if (r != null)
			r.p = this;
		flip = false;
		this.updateSize();
	}

	boolean isroot() {
		return p == null;
	}

	/*
	 * If this node is flipped, we unflip it, and push the change down the tree,
	 * so that it represents the same thing.
	 */
	void discharge() {
		if (flip) {
			flip = false;
			if (l != null)
				l.flip = !l.flip;
			if (r != null)
				r.flip = !r.flip;
			Node t = l;
			l = r;
			r = t;
		}
	}

	/*
	 * The tree structure has changed in the vicinity of this node (for example,
	 * if this node is linked to a different left child in a rotation). This
	 * function fixes up the data fields in the node to maintain invariants.
	 */
	void updateSize() {
		size = 1;
		if (l != null)
			size += l.size;
		if (r != null)
			size += r.size;
	}
}
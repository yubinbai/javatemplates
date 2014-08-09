package algorithm.datastructure.linkcut;

class Node {
	int s, my_s, on, id;
	boolean flip, my_flip;
	Node l, r, p;

	Node(int c, int i) {
		id = i;
		s = my_s = c;
		on = 0;
		l = r = p = null;
		flip = my_flip = false;
	}

	boolean isroot() {
		return p == null || (p.l != this && p.r != this);
	}

	/*
	 * If this node is flipped, we unflip it, and push the change down the tree,
	 * so that it represents the same thing.
	 */
	void normalize() {
		if (flip) {
			flip = false;
			on = s - on;
			my_flip = !my_flip;
			if (l != null)
				l.flip = !l.flip;
			if (r != null)
				r.flip = !r.flip;
		}
	}

	/*
	 * The tree structure has changed in the vicinity of this node (for example,
	 * if this node is linked to a different left child in a rotation). This
	 * function fixes up the data fields in the node to maintain invariants.
	 */
	void update() {
		s = my_s;
		on = (my_flip) ? my_s : 0;
		if (l != null) {
			s += l.s;
			if (l.flip)
				on += l.s - l.on;
			else
				on += l.on;
		}
		if (r != null) {
			s += r.s;
			if (r.flip)
				on += r.s - r.on;
			else
				on += r.on;
		}
	}
}
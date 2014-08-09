package algorithm.datastructure.linkcut;

/**
 * Hello world!
 * 
 */
class LinkCut {
	static void rotR(Node p) {
		Node q = p.p;
		Node r = q.p;
		q.normalize();
		p.normalize();
		if ((q.l = p.r) != null)
			q.l.p = q;
		p.r = q;
		q.p = p;
		if ((p.p = r) != null) {
			if (r.l == q)
				r.l = p;
			else if (r.r == q)
				r.r = p;
		}
		q.update();
	}

	static void rotL(Node p) {
		Node q = p.p;
		Node r = q.p;
		q.normalize();
		p.normalize();
		if ((q.r = p.l) != null)
			q.r.p = q;
		p.l = q;
		q.p = p;
		if ((p.p = r) != null) {
			if (r.l == q)
				r.l = p;
			else if (r.r == q)
				r.r = p;
		}
		q.update();
	}

	static void splay(Node p) {
		while (!p.isroot()) {
			Node q = p.p;
			if (q.isroot()) {
				if (q.l == p)
					rotR(p);
				else
					rotL(p);
			} else {
				Node r = q.p;
				if (r.l == q) {
					if (q.l == p) {
						rotR(q);
						rotR(p);
					} else {
						rotL(p);
						rotR(p);
					}
				} else {
					if (q.r == p) {
						rotL(q);
						rotL(p);
					} else {
						rotR(p);
						rotL(p);
					}
				}
			}
		}
		p.normalize(); // only useful if p was already a root.
		p.update(); // only useful if p was not already a root
	}

	/*
	 * This makes node q the root of the virtual tree, and also q is the
	 * leftmost node in its splay tree
	 */
	static void expose(Node q) {
		Node r = null;
		for (Node p = q; p != null; p = p.p) {
			splay(p);
			p.l = r;
			p.update();
			r = p;
		}
		;
		splay(q);
	}

	/*
	 * assuming p and q are nodes in different trees and that p is a root of its
	 * tree, this links p to q
	 */
	static void link(Node p, Node q) throws MyException {
		expose(p);
		if (p.r != null)
			throw new MyException("non-root link");
		p.p = q;
	}

	/*
	 * Toggle all the edges on the path from p to the root return the count
	 * after - count before
	 */
	static int toggle(Node p) {
		expose(p);
		int before = p.on;
		p.flip = !p.flip;
		p.normalize();
		int after = p.on;
		return after - before;
	}

	/* this returns the id of the node that is the root of the tree containing p */
	static int rootid(Node p) {
		expose(p);
		while (p.r != null)
			p = p.r;
		splay(p);
		return p.id;
	}
}

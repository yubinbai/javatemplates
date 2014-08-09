package algorithm.splay.splayTree;

class Splay {
	static void rotR(Node p) { // right rotation of p above its parent
		Node q = p.p;
		Node r = q.p;
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
		q.updateSize();
	}

	static void rotL(Node p) { // left rotation of p above its parent
		Node q = p.p;
		Node r = q.p;
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
		q.updateSize();
	}

	static void splay(Node p) {
		while (!p.isroot()) {
			Node q = p.p;
			if (q.isroot()) {
				q.discharge();
				p.discharge();
				if (q.l == p)
					rotR(p);
				else
					rotL(p);
			} else {
				Node r = q.p;
				r.discharge();
				q.discharge();
				p.discharge();
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
		p.discharge(); // only useful if p was already a root.
		p.updateSize(); // only useful if p was not already a root
	}

	static Node findRightmost(Node a) {
		a.discharge();
		while (a.r != null) {
			a = a.r;
			a.discharge();
		}
		return a;
	}

	static Node delete(Node p) {
		splay(p);
		if (p.l != null)
			p.l.p = null;
		if (p.r != null)
			p.r.p = null;
		return join(p.l, p.r);
	}

	static Node join(Node a, Node b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		Node c = findRightmost(a);
		splay(c);
		c.r = b;
		b.p = c;
		c.updateSize();
		return c;
	}
}

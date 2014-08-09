package algorithms.AvlTree;

import java.util.LinkedList;

import algorithms.AvlTree.Node;

/**
 * Hello world!
 * 
 */

public class AvlTree<Key extends Comparable<? super Key>> {

	Node<Key> root;
	int length;

	public AvlTree() {
		this.root = null;
		this.length = 0;
	}

	public Boolean insertKey(Key keyValue) {
		Node<Key> node = new Node<Key>(keyValue);
		insertAVL(this.root, node);
		return true;
	}

	private void insertAVL(Node<Key> current, Node<Key> node) {
		// If node to compare is null, the node is inserted. If the root is
		// null, it is the root of the tree.
		if (current == null) {
			this.root = node;
		} else {

			// If compare node is smaller, continue with the left node
			if (node.getValue().compareTo(current.getValue()) < 0) {
				if (current.getLeftChild() == null) {
					current.setLeftChild(node);
					node.setParent(current);
					this.length++;
					// Node is inserted now, continue checking the balance
					recursiveBalance(current);
				} else {
					insertAVL(current.getLeftChild(), node);
				}
			} else {
				if (current.getRightChild() == null) {
					current.setRightChild(node);
					node.setParent(current);
					this.length++;
					// Node is inserted now, continue checking the balance
					recursiveBalance(current);
				} else {
					insertAVL(current.getRightChild(), node);
				}
			}
		}

	}

	/**
	 * Check the balance for each node recursively and call required methods for
	 * balancing the tree until the root is reached.
	 * 
	 * @param cur
	 *            : The node to check the balance for, usually you start with
	 *            the parent of a leaf.
	 */
	private void recursiveBalance(Node<Key> current) {
		// we do not use the balance in this class, but the store it anyway
		setBalance(current);
		int balance = current.getBalance();

		// check the balance
		// the left sub-tree is higher:
		if (balance == -2) {

			if (height(current.getLeftChild().getLeftChild()) >= height(current
					.getLeftChild().getRightChild())) {
				current = rotateRight(current);
			} else {
				current = doubleRotateLeftRight(current);
			}
		}
		// the right sub-tree is higher:
		else if (balance == 2) {
			if (height(current.getRightChild().getRightChild()) >= height(current
					.getRightChild().getLeftChild())) {
				current = rotateLeft(current);
			} else {
				current = doubleRotateRightLeft(current);
			}
		}

		// we did not reach the root yet
		if (current.getParent() != null) {
			recursiveBalance(current.getParent());
		} else {
			this.root = current;
			// System.out.println("———— Balancing finished —————-");
		}

	}

	private Node<Key> doubleRotateRightLeft(Node<Key> u) {
		u.setRightChild(u.getRightChild());
		return rotateLeft(u);
	}

	private Node<Key> rotateLeft(Node<Key> n) {

		Node<Key> v = n.getRightChild();
		v.setParent(n.getParent());

		n.setRightChild(v.getLeftChild());

		if (n.getRightChild() != null) {
			n.getRightChild().setParent(n);
		}

		v.setLeftChild(n);
		n.setParent(v);

		if (v.getParent() != null) {
			if (v.getParent().getRightChild() == n) {
				v.getParent().setRightChild(v);
			} else if (v.getParent().getLeftChild() == n) {
				v.getParent().setLeftChild(v);
			}
		}

		setBalance(n);
		setBalance(v);

		return v;
	}

	private void setBalance(Node<Key> current) {
		current.setBalance(height(current.getRightChild())
				- height(current.getLeftChild()));

	}

	private Node<Key> doubleRotateLeftRight(Node<Key> u) {
		u.setLeftChild(rotateLeft(u.getLeftChild()));
		return rotateRight(u);
	}

	private Node<Key> rotateRight(Node<Key> n) {
		Node<Key> v = n.getLeftChild();
		v.setParent(n.getParent());

		n.setLeftChild(v.getRightChild());

		if (n.getLeftChild() != null) {
			n.getLeftChild().setParent(n);
		}

		v.setRightChild(n);
		n.setParent(v);

		if (v.parent != null) {
			if (v.getParent().getRightChild() == n) {
				v.getParent().setRightChild(v);
			} else if (v.getParent().getLeftChild() == n) {
				v.getParent().setLeftChild(v);
			}
		}

		setBalance(n);
		setBalance(v);

		return v;
	}

	private int height(Node<Key> current) {
		if (current == null) {
			return -1;
		}
		if (current.getLeftChild() == null && current.getRightChild() == null) {
			return 0;
		} else if (current.getLeftChild() == null) {
			return 1 + height(current.getRightChild());
		} else if (current.getRightChild() == null) {
			return 1 + height(current.getLeftChild());
		} else {
			return 1 + Math.max(height(current.getLeftChild()),
					height(current.getRightChild()));
		}
	}

	public Node<Key> search(Key keyValue) {
		Node<Key> current = root;

		while ((current != null)
				&& (keyValue.compareTo(current.getValue()) != 0)) {
			if (keyValue.compareTo(current.getValue()) < 0)
				current = current.getLeftChild();
			else
				current = current.getRightChild();
		}
		return current;
	}

	/**
	 * Removes a node from a AVL-Tree, while balancing will be done if
	 * necessary.
	 * 
	 * @param q
	 *            The node to be removed.
	 */
	public Boolean deleteNode(Node<Key> target) {

		// use swap to temporary store the node to be deleted
		Node<Key> swap;

		// Fix children
		// with only one child, target will be removed directly
		if (target.getLeftChild() == null || target.getRightChild() == null) {
			// the root is deleted
			if (target.getParent() == null) {
				this.root = null;
				target = null;
				return true;
			}
			swap = target;
		} else {
			// q has two children –> will be replaced by successor
			swap = successor(target);
			target.setValue(swap.getValue());
		}

		// Find parent
		Node<Key> link;
		if (swap.getLeftChild() != null) {
			link = swap.getLeftChild();
		} else {
			link = swap.getRightChild();
		}

		if (link != null) {
			link.setParent(swap.getParent());
		}

		// fix the child links in the parent node
		if (swap.getParent() == null) {
			this.root = link;
		} else {
			if (swap == swap.getParent().getLeftChild()) {
				swap.getParent().setLeftChild(link);
			} else {
				swap.getParent().setRightChild(link);
			}
			// balancing must be done until the root is reached.
			recursiveBalance(swap.getParent());
		}

		// release swap
		swap = null;
		return true;
	}

	private Node<Key> successor(Node<Key> target) {
		if (target.getRightChild() != null) {
			Node<Key> temp = target.getRightChild();
			while (temp.getLeftChild() != null) {
				temp = temp.getLeftChild();
			}
			return temp;
		} else {
			Node<Key> parent = target.getParent();
			while (parent != null && target == parent.getRightChild()) {
				target = parent;
				parent = target.getParent();
			}
			return parent;
		}
	}

	public Node<Key> treeMax() {
		// algo on page 45
		Node<Key> current = root;
		while (current.getRightChild() != null)
			current = current.getRightChild();
		return current;
	}

	public Node<Key> treeMin() {
		Node<Key> currP = root;
		while (currP.getLeftChild() != null)
			currP = currP.getLeftChild();
		return currP;
	}

	public LinkedList<Key> inOrderWalk() {
		LinkedList<Key> returnData = new LinkedList<Key>();
		inOrderWalk(root, returnData);
		return returnData;
	}

	private void inOrderWalk(Node<Key> e, LinkedList<Key> list) {
		if (e != null) {
			inOrderWalk(e.getLeftChild(), list);
			list.add(e.getValue());
			inOrderWalk(e.getRightChild(), list);
		}
	}

}

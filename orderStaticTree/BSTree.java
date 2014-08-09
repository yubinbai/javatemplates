package algorithm.ostree.ostree;

import java.util.LinkedList;

/**
 * Implement the binary search tree
 * 
 */

/**
 * @author Administrator
 *
 * @param <Key>
 */
/**
 * @author Administrator
 *
 * @param <Key>
 */
abstract class BSTree<Key extends Comparable> {

	protected Node<Key> root;
	protected int length;

	public BSTree() {
		this.setRoot(null);
		this.length = 0;
	}

	public Node<Key> search(Key keyValue) {
		Node<Key> currP = getRoot();

		while ((currP != null) && (keyValue.compareTo(currP.getValue()) != 0)) {
			if (keyValue.compareTo(currP.getValue()) < 0)
				currP = currP.getLeftChild();
			else
				currP = currP.getRightChild();
		}
		return currP;
	}

	public Node<Key> treeMax() {
		// algo on page 45
		Node<Key> currP = this.root;
		while (currP.getRightChild() != null)
			currP = currP.getRightChild();
		return currP;
	}

	public Node<Key> treeMin() {
		// algo on page 45
		Node<Key> currP = this.root;
		while (currP.getLeftChild() != null)
			currP = currP.getLeftChild();
		return currP;
	}

	/**
	 * @param e is the subtree 
	 * @return the minimum node in the subtree
	 */
	protected Node<Key> treeMinimum(Node<Key> e) {
		Node<Key> currP = e;
		while (currP.getLeftChild() != null)
			currP = currP.getLeftChild();
		return currP;
	}

	// private Node<Key> successorNode(Node<Key> e) {
	// // algo on page 46
	// if (e.getRightChild() != null)
	// return treeMinimum(e);
	// Node<Key> goUp = e.getParent();
	// Node<Key> currP = e;
	// while (goUp != null && currP == goUp.getRightChild()) {
	// currP = goUp;
	// goUp = goUp.getParent();
	// }
	// return goUp;
	// }

	abstract public Boolean insertKey(Key keyValue);

	abstract public Boolean deleteNode(Node<Key> e);

	public LinkedList<Key> inOrderWalk() {
		// algo on page 43
		LinkedList<Key> returnData = new LinkedList<Key>();
		inOrderWalk(getRoot(), returnData);
		return returnData;

	}

	private void inOrderWalk(Node<Key> e, LinkedList<Key> list) {
		if (e != null) {
			inOrderWalk(e.getLeftChild(), list);
			list.add(e.getValue());
			inOrderWalk(e.getRightChild(), list);
		}
	}

	/**Occupy the position of 'source' with node 'target'
	 * @param source
	 * @param target
	 */
	protected void transplant(Node<Key> source, Node<Key> target) {
		// algo on page 48
		if (source.getParent() == null) {
			this.setRoot(target);
		} else if (source == source.getParent().getLeftChild()) {
			source.getParent().setLeftChild(target);
		} else {
			source.getParent().setRightChild(target);
		}
		if (target != null)
			target.setParent(source.getParent());
	}

	public Node<Key> getRoot() {
		return root;
	}

	public void setRoot(Node<Key> root) {
		this.root = root;
	}
	
	abstract public int rankOf(Node<Key> node);
}

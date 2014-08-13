package algorithm.clrs.optimal.bst;

/**
 * Binary search tree
 * 
 */

public class BSTree<Key extends Comparable<? super Key>> {

	Node<Key> root;
	int length;

	public BSTree(Node<Key> root, int length) {
		this.root = root;
		this.length = length;
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
	 * Return the cost of searching for a key value in the bs tree
	 * 
	 * @param keyValue
	 * @return the integer number of data comparison operations
	 */
	public int searchCost(Key keyValue) {
		int cost = 1;
		Node<Key> current = root;

		while ((current != null)
				&& (keyValue.compareTo(current.getValue()) != 0)) {
			if (keyValue.compareTo(current.getValue()) < 0)
				current = current.getLeftChild();
			else
				current = current.getRightChild();
			cost++;
		}
		return cost;
	}

}

package algorithm.clrs.optimal.bst;

public class Node<Key> {

	Key value;
	Node<Key> parent, leftChild, rightChild;

	/**
	 * Use a key value to construct a tree node
	 * @param value
	 */
	public Node(Key value) {
		super();
		this.value = value;
		// blank all connections
		parent = leftChild = rightChild = null;
	}

	public Key getValue() {
		return value;
	}

	public void setValue(Key value) {
		this.value = value;
	}

	public Node<Key> getParent() {
		return parent;
	}

	public void setParent(Node<Key> parent) {
		this.parent = parent;
	}

	public Node<Key> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node<Key> leftChild) {
		this.leftChild = leftChild;
	}

	public Node<Key> getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node<Key> rightChild) {
		this.rightChild = rightChild;
	}
}

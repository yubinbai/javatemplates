/**
 * 
 */
package algorithm.ostree.ostree;

/**
 * @author Administrator
 * 
 */
public class OSTree<Key extends Comparable> extends BSTree {

	@Override
	public Boolean insertKey(Comparable keyValue) {
		if (keyValue == null)
			return false;
		Node<Key> insertPos = null;
		Node<Key> searchPos = this.root;
		while (searchPos != null) {
			insertPos = searchPos;
			if (keyValue.compareTo(searchPos.getValue()) < 0)
				searchPos = searchPos.getLeftChild();
			else
				searchPos = searchPos.getRightChild();
		}
		OSNode<Key> newNode = new OSNode<Key>((Key) keyValue);
		newNode.setParent(insertPos);
		if (insertPos == null) {
			this.root = newNode;
		} else if (keyValue.compareTo(insertPos.getValue()) < 0) {
			insertPos.setLeftChild(newNode);
		} else {
			insertPos.setRightChild(newNode);
		}
		this.length++;

		// set the size of related nodes
		this.adjustSize((OSNode) insertPos);
		return true;
	}

	@Override
	public Boolean deleteNode(Node e) {
		if (e == null)
			return false;
		
		if (e.getLeftChild() == null) {
			transplant(e, e.getRightChild());
			this.adjustSize((OSNode) e);
		} else if (e.getRightChild() == null) {
			transplant(e, e.getLeftChild());
			this.adjustSize((OSNode) e);
		} else {
			Node<Key> successor = treeMinimum(e.getRightChild());
			Node sizeMod = successor;
			if (successor.getParent() != e) {
				sizeMod = successor.getParent();
				transplant(successor, successor.getRightChild());
				successor.setRightChild(e.getRightChild());
				successor.getRightChild().setParent(successor);
				
			}
			transplant(e, successor);
			successor.setLeftChild(e.getLeftChild());
			successor.getLeftChild().setParent(successor);
			this.adjustSize((OSNode) sizeMod);
		}
		this.length--;

		return true;
	}

	private void adjustSize(OSNode node) {
		OSNode parent = node;
		while (parent != null) {
			int size = 1;
			if (parent.getLeftChild() != null)
				size += ((OSNode<Key>) parent.getLeftChild()).getSize();
			if (parent.getRightChild() != null)
				size += ((OSNode<Key>) parent.getRightChild()).getSize();
			parent.setSize(size);
			parent = (OSNode) parent.getParent();
		}
	}

	@Override
	public int rankOf(Node node) {
		int r = 1;
		r += OSNode.sizeOf((OSNode) node.getLeftChild());
		Node parent = node;
		while (parent != this.root) {
			if (parent.getParent() != null
					&& parent == parent.getParent().getRightChild())
				r += OSNode.sizeOf((OSNode) parent.getParent().getLeftChild()) + 1;
			parent = parent.getParent();
		}

		return r;
	}

	private static class OSNode<Key extends Comparable> extends Node {
		private int size;

		public OSNode(Object keyValue) {
			super(keyValue);
			this.setSize(1);
		}

		public int getSize() {
			return size;
		}

		public void setSize(int Size) {
			this.size = Size;
		}

		public static int sizeOf(OSNode node) {
			if (node == null)
				return 0;
			else
				return node.getSize();
		}
	}
}

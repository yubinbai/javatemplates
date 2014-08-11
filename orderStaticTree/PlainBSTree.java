package orderStaticTree;

import java.util.LinkedList;

public class PlainBSTree<Key extends Comparable> extends BSTree {

	@Override
	public Boolean deleteNode(Node e) {
		if (e == null)
			return false;
		// algo on page 49
		if (e.getLeftChild() == null) {
			transplant(e, e.getRightChild());
		} else if (e.getRightChild() == null) {
			transplant(e, e.getLeftChild());
		} else {
			Node<Key> successor = treeMinimum(e.getRightChild());
			if (successor.getParent() != e) {
				transplant(successor, successor.getRightChild());
				successor.setRightChild(e.getRightChild());
				successor.getRightChild().setParent(successor);
			}
			transplant(e, successor);
			successor.setLeftChild(e.getLeftChild());
			successor.getLeftChild().setParent(successor);
		}
		this.length--;
		return true;
	}

	@Override
	public int rankOf(Node node) {
		LinkedList<Key> inOrder = this.inOrderWalk();
		
		// as the rank of linked list start from 0
		return inOrder.indexOf(node.getValue()) + 1;
	}

	@Override
	public Boolean insertKey(Comparable keyValue) {
		//
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
		Node<Key> newNode = new Node<Key>((Key) keyValue);
		newNode.setParent(insertPos);
		if (insertPos == null) {
			this.root = newNode;
		} else if (keyValue.compareTo(insertPos.getValue()) < 0) {
			insertPos.setLeftChild(newNode);
		} else {
			insertPos.setRightChild(newNode);
		}
		this.length++;
		return true;
	}
	
}

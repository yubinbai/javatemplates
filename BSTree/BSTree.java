package BSTree;

import java.util.LinkedList;

/**
 * Implement the binary search tree
 *
 */

public class BSTree<Key extends Comparable<? super Key>> {

    private Node<Key> root;
    int length;

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
        Node<Key> currP = getRoot();
        while (currP.getRightChild() != null) {
            currP = currP.getRightChild();
        }
        return currP;
    }

    public Node<Key> treeMin() {
        // algo on page 45
        Node<Key> currP = getRoot();
        while (currP.getLeftChild() != null)
            currP = currP.getLeftChild();
        return currP;
    }

    private Node<Key> treeMinimum(Node<Key> e) {
        Node<Key> currP = e;
        while (currP.getLeftChild() != null)
            currP = currP.getLeftChild();
        return currP;
    }

    public Boolean insertKey(Key keyValue) {
        // algo on page 47
        if (keyValue == null)
            return false;
        Node<Key> insertPos = null;
        Node<Key> searchPos = this.getRoot();
        while (searchPos != null) {
            insertPos = searchPos;
            if (keyValue.compareTo(searchPos.getValue()) < 0)
                searchPos = searchPos.getLeftChild();
            else
                searchPos = searchPos.getRightChild();
        }
        Node<Key> newNode = new Node<Key>(keyValue);
        newNode.setParent(insertPos);
        if (insertPos == null) {
            this.setRoot(newNode);
        } else if (keyValue.compareTo(insertPos.getValue()) < 0) {
            insertPos.setLeftChild(newNode);
        } else {
            insertPos.setRightChild(newNode);
        }
        this.length++;
        return true;
    }

    public Boolean deleteNode(Node<Key> e) {
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

    private void transplant(Node<Key> source, Node<Key> target) {
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

    /**
     * @return the root
     */
    public Node<Key> getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(Node<Key> root) {
        this.root = root;
    }
}

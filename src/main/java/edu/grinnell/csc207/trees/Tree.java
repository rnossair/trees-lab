package edu.grinnell.csc207.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A generic binary tree implementation.
 */
public class Tree<T extends Comparable<? super T>> {

    ///// From the reading

    // N.B., the Node<T> class is made public for this lab, so that you can
    // construct trees without an insert-style method!

    private Node<T> root;

    /**
     * Constructs a new, empty binary tree.
     */
    public Tree() {
        root = null;
    }

    /**
     * Constructs a new binary tree with the given root node.
     * 
     * @param root the root node of the tree
     */
    public Tree(Node<T> root) {
        this.root = root;
    }

    /**
     * @param node the root of the tree
     * @return the number elements found in this tree rooted at node
     */
    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /** @return the number of elements in the tree */
    public int size() {
        return sizeH(root);
    }

    ///// Part 1: Contains

    public boolean containsHelper(Node<T> curr, T val) {
        if (curr == null) {
            return false;
        }
        if (curr.value == val) {
            return true;
        }
        boolean left = containsHelper(curr.left, val);
        boolean right = false;
        if (!left) {
            right = containsHelper(curr.right, val);
        }

        return left || right;
    }

    /**
     * @param value the value to search for
     * @return true iff the tree contains <code>value</code>
     */
    public boolean contains(T value) {
        return containsHelper(root, value);
    }

    ///// Part 2: toString
    ///
    ///

    // public T toStringBFS(Node<T> root) {

    // return res;
    // }

    /**
     * @return a string represent of this tree in the form, "[x1, ..., xk]."
     *         The order of the elements is left unspecified.
     */
    @Override
    public String toString() {

        StringBuffer buf = new StringBuffer("[");
        Node<T> curr = root;
        Queue nodeQueue = new LinkedList<Node<T>>();
        boolean first = true;
        nodeQueue.add(curr);
        while (nodeQueue.size() != 0) {
            curr = (Node<T>) nodeQueue.poll();
            if (curr == null) {
                continue;
            }
            nodeQueue.add(curr.left);
            nodeQueue.add(curr.right);
            if (!first) {
                buf.append(", ");
                buf.append(curr.value.toString());
                System.out.println(curr.value.toString());
                // first = false;
            } else {
                first = false;
                buf.append(curr.value.toString());
            }
        }
        buf.append("]");
        return buf.toString();
    }

    ///// Part 3: Traversals
    ///
    ///

    public void inOrderDFS(Node<T> root, List<T> lst) {
        if (root == null) {
            return;
        }
        inOrderDFS(root.left, lst);
        lst.add(root.value);
        inOrderDFS(root.right, lst);
    }

    /**
     * @return the elements of this tree collected via an in-order traversal
     */
    public List<T> toListInorder() {
        List<T> lst = new LinkedList<>();
        inOrderDFS(root, lst);
        return lst;
    }

    public void preOrderDFS(Node<T> root, List<T> lst) {
        if (root == null) {
            return;
        }
        lst.add(root.value);
        preOrderDFS(root.left, lst);
        preOrderDFS(root.right, lst);
    }

    /**
     * @return the elements of this tree collected via a pre-order traversal
     */
    public List<T> toListPreorder() {
        List<T> lst = new LinkedList<>();
        preOrderDFS(root, lst);
        return lst;
    }

    public void postOrderDFS(Node<T> root, List<T> lst) {
        if (root == null) {
            return;
        }
        
        postOrderDFS(root.left, lst);
        postOrderDFS(root.right, lst);
        lst.add(root.value);
    }

    /**
     * @return the elements of this tree collected via a post-order traversal
     */
    public List<T> toListPostorder() {
        List<T> lst = new LinkedList<>();
        postOrderDFS(root, lst);
        return lst;
    }

    ///// Extra: Pretty Printing

    /**
     * @return a string represent of this tree in bulleted list form.
     */
    public String toPrettyString() {
        throw new UnsupportedOperationException();
    }

    /**
     * The main driver for this program
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Nothing to do. 'Run' via the JUnit tests instead!");
    }
}

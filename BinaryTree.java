/*
CMPT 225 Homework 3
Part 1 Binary Trees
Written by Nathan Omana
nao3@sfu.ca
301596662
*/
package binarytree;

import java.util.*;

public class BinaryTree<T> {

    private BTNode<T> root;

    public BinaryTree(BTNode<T> root) {
        this.root = root;
    }

    public BTNode<T> getRoot() {
        return root;
    }

    public int size() {
        return (root == null) ? 0 : root.size();
    }

    public int height() {
        return (root == null) ? -1 : root.height();
    }

    public void printInOrder() {
        if (root != null) root.printInOrder();
    }

    public void printPreOrder() {
        if (root != null) root.printPreOrder();
    }

    public void printPostOrder() {
        if (root != null) root.printPostOrder();
    }

    /**************** Assignment 3 *************************/


 // returns the number of leaves in the tree
    public int numberOfLeaves() {
        return numberOfLeavesHelper(root);
    }

    // recursive helper for numberOfLeaves
    private int numberOfLeavesHelper(BTNode<T> node) {
        if (node == null) return 0;              
        if (node.isLeaf()) return 1;             
        return numberOfLeavesHelper(node.getLeftChild()) 
            + numberOfLeavesHelper(node.getRightChild()); 
    }

    // checks if two trees are equal (structure and data)
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof BinaryTree)) return false;
        BinaryTree<?> otherTree = (BinaryTree<?>) other;

        Stack<BTNode<?>> S1 = new Stack<>();
        Stack<BTNode<?>> S2 = new Stack<>();

        S1.push(this.root);
        S2.push(otherTree.root);

        while (!S1.isEmpty() && !S2.isEmpty()) {
            BTNode<?> N1 = S1.pop();
            BTNode<?> N2 = S2.pop();

            if (N1 == null && N2 == null) continue;
            if (N1 == null || N2 == null) return false;
            if (!Objects.equals(N1.getData(), N2.getData())) return false;

            // push right then left so left is processed first
            S1.push(N1.getRightChild());
            S1.push(N1.getLeftChild());
            S2.push(N2.getRightChild());
            S2.push(N2.getLeftChild());
        }

        return S1.isEmpty() && S2.isEmpty();
    }

    // returns the number of nodes at depth k
    public int countDepthK(int k) {
        if(k < 0) throw new IllegalArgumentException("negative depth");
        return countDepthK(root, k);
    }

    // recursive helper for countDepthK
    private int countDepthK(BTNode<T> node, int depth) {
        if(node == null) return 0;
        if(depth == 0) return 1;

        return countDepthK(node.getLeftChild(), depth - 1) + 
               countDepthK(node.getRightChild(), depth - 1);
    }

    // returns a dynamic preorder iterator
    public Iterator<T> preOrderIterator() {
        return new PreOrderIterator();
    }

    // named inner class for dynamic preorder traversal
    class PreOrderIterator implements Iterator<T> {
        private Stack<BTNode<T>> stack = new Stack<>();

        public PreOrderIterator() {
            if (root != null) stack.push(root); // start from root
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            BTNode<T> node = stack.pop();
            // push right first so left is processed next
            if (node.getRightChild() != null) stack.push(node.getRightChild());
            if (node.getLeftChild() != null) stack.push(node.getLeftChild());
            return node.getData();
        }
    }
}







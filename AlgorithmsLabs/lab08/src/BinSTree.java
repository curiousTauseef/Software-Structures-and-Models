/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class BinSTree<T extends Comparable<T>> {
    
    private int counter;

    private class BinaryTreeNode {

        private T data;
        private BinaryTreeNode leftChild, rightChild, parent;

        public BinaryTreeNode(T data, BinaryTreeNode leftChild,
                BinaryTreeNode rightChild, BinaryTreeNode parent) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;
        }
    }

    private BinaryTreeNode root;

    public BinSTree() {

    }
    
    public void print(){
        printTree(root, 0);
    }
    
    private void printTree(BinaryTreeNode Node, int indent) {
        if (Node != null) {
            System.out.print("          ");

            printTree(Node.rightChild, ++indent);
            System.out.println();
            for (int i = 0; i < indent-1; i++) {
                System.out.print("          ");
            }
            System.out.println(Node.data);

            for (int i = 0; i < indent; i++) {
                System.out.print("          ");
            }

            printTree(Node.leftChild, indent);

        } else {
            System.out.print("#");
            System.out.println();
        }
    }

    public BinaryTreeNode insert_to_tree(T data) {
        if (root == null) {
            root = new BinaryTreeNode(data, null, null, null);
            return root;
        }
        return findInsertPlace(root, data);
    }

    private BinaryTreeNode findInsertPlace(BinaryTreeNode currentNode, T data) {
        if (data.compareTo(currentNode.data) >= 0) {
            if (currentNode.rightChild == null) {
                currentNode.rightChild = new BinaryTreeNode(data, null, null, currentNode);
                return currentNode.rightChild;
            }
            return findInsertPlace(currentNode.rightChild, data);
        } else {
            if (currentNode.leftChild == null) {
                currentNode.leftChild = new BinaryTreeNode(data, null, null, currentNode);
                return currentNode.leftChild;
            }
            return findInsertPlace(currentNode.leftChild, data);
        }
    }

    public boolean isInTree(T data) {
        // counter = 0;
        boolean isIntree = isInSubtree(root, data);
        // System.out.println(data + " is in tree? " +isIntree + ":: It took " + counter + " comparison(s) to find this out.");
        return isIntree;
    }

    private boolean isInSubtree(BinaryTreeNode currentNode, T data) {
        
        if (data.compareTo(currentNode.data) > 0) {
            // counter++;
            if (currentNode.rightChild == null) {
                return false;
            }
            return isInSubtree(currentNode.rightChild, data);
        } else if (data.compareTo(currentNode.data) < 0){
            // counter++;
            if (currentNode.leftChild == null) {
                return false;
            }
            return isInSubtree(currentNode.leftChild, data);
        }
        return true;
    }
}

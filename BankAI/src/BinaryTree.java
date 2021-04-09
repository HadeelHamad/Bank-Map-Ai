import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    // Root of Binary Tree
    Node originalroot;
    /*
     * List contains the IDs of the nodes containing 'V' or '0' indicating that they
     * are qualified to be occupied by a camera
     */
    List<Integer> zerosRefrences;
    // idCounter is used while creating the tree node (ensuring that all nodes have
    // unique ids)
    int idCounter = 0;

    // --------------------------------------------------------------------------------------------------------------------
    // First consructor (used for creating all states except the initail state)
    BinaryTree() {
        originalroot = null;
        zerosRefrences = new ArrayList<Integer>();
    }

    // --------------------------------------------------------------------------------------------------------------------
    // Third consructor (used only for the initail state)
    BinaryTree(Character[] floorArray) {
        originalroot = fromArrayToBinaryTree(floorArray);
        zerosRefrences = new ArrayList<Integer>();
    }

    // --------------------------------------------------------------------------------------------------------------------
    // recursive method to print tree nodes in Postorder
    private void printTreePostorderRecursive(Node node) {
        if (node == null)
            return;
        // print the value of node children then print its value .
        printTreePostorderRecursive(node.left);
        printTreePostorderRecursive(node.right);
        System.out.print(node.value);

    }

    // --------------------------------------------------------------------------------------------------------------------
    public void printTreePostorder() {
        printTreePostorderRecursive(originalroot);
    }

    // --------------------------------------------------------------------------------------------------------------------

    // Convert from array to tree representation, returns the root of the tree
    public Node fromArrayToBinaryTree(Character[] array) {
        // null or empty array => return null
        if (array == null || array.length == 0) {
            return null;
        }

        Queue<Node> treeQueue = new LinkedList<>();// Create Queue of nodes

        // prepare the root of tree
        Node root;
        if (array[0] == '0')
            root = new Node('0', 0);

        else
            return null; // empty tree

        treeQueue.offer(root);// insert into the treeNode

        int id = 1;
        for (int j = 1; j < array.length; j++) {

            Node current = treeQueue.poll();
            if (array[j] != null) {// if the left node is not null
                Node left = new Node('0', id++);
                current.left = left;
               // left.parent = current;
                treeQueue.offer(left);
            }
            j++;
            if (j < array.length) {
                if (array[j] != null) {// if the right node is not null
                    Node right = new Node('0', id++);
                    current.right = right;
                   // right.parent = current;
                    treeQueue.offer(right);
                }
            } else
                break;

        }

        return root;
    }

    // --------------------------------------------------------------------------------------------------------------------
   // assigning a camera in one of the qualified nodes(their ids are in the given
    // list)
    // note: this method will return the updated list (after removing the id of the
    // node which was occupied by a camera)
    public List<Integer> assignPossibleCamera(Node refrence, List<Integer> list) {
        if (refrence == null)
            return null;
        List<Integer> l = assignPossibleCamera(refrence.right, list);
        if (l != null)
            return l;
        l = assignPossibleCamera(refrence.left, list);
        if (l != null)
            return l;
        // if the id of the current node is one of these in the list
        if (list.contains(refrence.id)) {
            // assign camera to that node
            refrence.value = 'C';
            // IMPORTANT >> remove the id from the list
            list.remove(Integer.valueOf(refrence.id));
            // assign views (‘V’) indicating that the camera that is recently added can
            // monitior these ‘V’ nodes
            if (refrence.left != null && refrence.left.value != 'C')
                // camera can monitor the left child if it is exist
                refrence.left.value = 'V';
            if (refrence.right != null && refrence.right.value != 'C')
                // camera can monitor the right child if it is exist
                refrence.right.value = 'V';

                refrence.parent = findMyParentNode(originalroot, refrence);
            if (refrence.parent != null && refrence.parent.value != 'C')
                // camera can monitor the parent if it is exist (parent is null only when the
                // current node is the root)
                refrence.parent.value = 'V';
            return list;
        }
        return null;
    }

    // --------------------------------------------------------------------------------------------------------------------
    // Given a node, this method counts the number of nodes containing 'V' or '0'
    // starting from the given node
    public int countPossibleChildren(Node node) {
        int possibleChildrenCounter = 0;
        if (node.value == '0')
            possibleChildrenCounter++;
        if (node.left != null)
            possibleChildrenCounter += countPossibleChildren(node.left);
        if (node.right != null)
            possibleChildrenCounter += countPossibleChildren(node.right);
        return possibleChildrenCounter;
    }

    // --------------------------------------------------------------------------------------------------------------------
    // Given a node, this method counts the number of nodes containing 'C' starting
    // from the given node
    public int countCameras(Node node) {
        int num = 0;
        if (node.value == 'C')
            num++;
        if (node.left != null)
            num += countCameras(node.left);
        if (node.right != null)
            num += countCameras(node.right);
        return num;
    }

    // --------------------------------------------------------------------------------------------------------------------
    // Given a node, this method examin the binary tree state wither or not it is a
    // goal state
    public boolean isGoal(Node node) {
        if (node == null) {
            return true;
        }
        // the state (BT) is NOT a goal if it still contaons unmonitored nodes (these
        // with '0' value)
        if (node.value == '0') {
            return false;
        }
        return isGoal(node.left) && isGoal(node.right);
    }

    // --------------------------------------------------------------------------------------------------------------------
    // update the "zerosRefrences" list of nodes IDs that are qualified be
    // occupied by a camera)
    public void findZeroes(Node node) {
        if (node == null)
            return;
        if (node.value == '0')
            // the node can be occupied by a camera if it is free '0'
            zerosRefrences.add(node.id);
        findZeroes(node.left);

        findZeroes(node.right);
    }

    // --------------------------------------------------------------------------------------------------------------------
    // this method is used to find & return the parent of the given child starting
    // from the root node prameter
    public Node findMyParentNode(Node root, Node child) {
        if (root == null || child == null)
            return null;
        // if the root is the parent of the child node
        else if ((root.right != null && root.right.equals(child) || root.left != null && root.left.equals(child)))
            // then return it
            return root;
        else {
            Node found = findMyParentNode(root.right, child);
            if (found == null) {
                found = findMyParentNode(root.left, child);
            }
            return found;// either the found parent node or null if the given child is the root of the
                         // tree
        }
    }

    // --------------------------------------------------------------------------------------------------------------------
   
}

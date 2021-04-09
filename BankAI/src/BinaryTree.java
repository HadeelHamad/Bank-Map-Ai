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
    List<Integer> viewsAndZerosRefrences;
    // idCounter is used while creating the tree node (ensuring that all nodes have
    // unique ids)
    int idCounter = 0;

    // --------------------------------------------------------------------------------------------------------------------
    // First consructor (used for creating all states except the initail state)
    BinaryTree() {
        originalroot = null;
        viewsAndZerosRefrences = new ArrayList<Integer>();
    }

    // --------------------------------------------------------------------------------------------------------------------
    // Second consructor (used only for the initail state)
    // BinaryTree(String[] floorArray) {
    //    // originalroot = fromArrayToBinaryTree(floorArray, null, 0);
    //     viewsAndZerosRefrences = new ArrayList<Integer>();
    // }

    // --------------------------------------------------------------------------------------------------------------------
    // Third consructor (used only for the initail state)
    BinaryTree(Character[] floorArray) {
        originalroot = fromArrayToBinaryTree(floorArray);
        viewsAndZerosRefrences = new ArrayList<Integer>();
    }

    // --------------------------------------------------------------------------------------------------------------------
    // recursive method to print tree nodes in preorder
    private void printTreePreorderRecursive(Node node) {
        if (node == null)
            return;
        // then print the value of node first then print its children values
        System.out.print(node.value);
        printTreePreorderRecursive(node.left);
        printTreePreorderRecursive(node.right);
    }

    // --------------------------------------------------------------------------------------------------------------------
    public void printTreePreorder() {
        printTreePreorderRecursive(originalroot);
    }

    // --------------------------------------------------------------------------------------------------------------------
    // public Node fromArrayToBinaryTree(String[] arr, Node root, int i) {
    // // Base case for recursion
    // if (i < arr.length && !arr[i].equals("null")) {
    // Node temp = new Node(arr[i].charAt(0), idCounter++);
    // root = temp;
    // // find parent of this node
    // root.parent = findMyParentNode(originalroot, root);
    // // insert left child
    // root.left = fromArrayToBinaryTree(arr, root.left, 2 * i + 1);
    // // insert right child
    // root.right = fromArrayToBinaryTree(arr, root.right, 2 * i + 2);
    // }
    // return root;
    // }

    // Convert from array to tree representation, returns the root of the tree
    public Node fromArrayToBinaryTree(Character[] array) {
        // null or empty array => return null
        if (array == null || array.length == 0) {
            return null;
        }
        Queue<Node> treeNodeQueue = new LinkedList<>();// Create Queue of nodes
        Queue<Character> charQueue = new LinkedList<>();// Create Queue of integer
        // loop to insert the element of the in the integerQueue
        for (int i = 1; i < array.length; i++) {
            charQueue.offer(array[i]);
        }
        Node treeNode;
        if (array[0] == 0)
            treeNode = new Node('0', 0);
        else
            return null;
        treeNodeQueue.offer(treeNode);// insert into the treeNode
        int i = 1;
        while (!charQueue.isEmpty()) {
            // if the queue is empty the value will be assigned as null otherwise it will
            // take the first element in the queue
            Character leftVal = charQueue.isEmpty() ? null : charQueue.poll();
            Character rightVal = charQueue.isEmpty() ? null : charQueue.poll();
            Node current = treeNodeQueue.poll();
            if (leftVal != null) {// if the left node is not null
                Node left = new Node('0', i++);
                current.left = left;
                treeNodeQueue.offer(left);
            }
            if (rightVal != null) {// if the right node is not null
                Node right = new Node('0', i++);
                current.right = right;
                treeNodeQueue.offer(right);
            }
        }
        return treeNode;
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
            // assign views ('V') indicating that the camera that is recently added can
            // monitior these 'V' nodes
            if (refrence.left != null && refrence.left.value != 'C')
                // camera can monitor the left child if it is exist
                refrence.left.value = 'V';
            if (refrence.right != null && refrence.right.value != 'C')
                // camera can monitor the right child if it is exist

                refrence.right.value = 'V';

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
        if (node.value == '0' || node.value == 'V')
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
    private int countCameras(Node node) {
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
    // update the "viewsAndZerosRefrences" list of nodes IDs that are qualified be
    // occupied by a camera)
    public void findViewsAndZeroes(Node node) {
        if (node == null)
            return;
        if (node.value == '0' || node.value == 'V')
            // the node can be occupied by a camera if it is free '0' or viewed
            viewsAndZerosRefrences.add(node.id);
        findViewsAndZeroes(node.left);

        findViewsAndZeroes(node.right);
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
    public void printOutput() {
        System.out.println(
                "\n-According to DFS strategy the number of surveillance camerasƒ needed to monitor the whole floor is "
                        + countCameras(originalroot));
    }
    // --------------------------------------------------------------------------------------------------------------------
}

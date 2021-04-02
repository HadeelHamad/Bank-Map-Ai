import java.util.ArrayList;
import java.util.List;


public class BinaryTree {

    // Root of Binary Tree
    Node originalroot;
    List<Integer> viewsAndZerosRefrences;
    int idCounter=0;
    BinaryTree() {
        originalroot = null;
        viewsAndZerosRefrences = new ArrayList<Integer>();
    }

    BinaryTree(String[] floorArray) {
        originalroot = fromArrayToBinaryTree(floorArray, null, 0);
        viewsAndZerosRefrences = new ArrayList<Integer>();
        
    }

    /* Given a binary tree, print its nodes in Preorder */
    void printPreorder(Node node) {
        if (node == null)
            return;
        /* then print the data of node */
        System.out.print(node.value);
        /* first recur on left child */
        printPreorder(node.left);

        /* now recur on right child */
        printPreorder(node.right);
    }

    // Wrappers over above recursive functions
    void printPreorder() {
        printPreorder(originalroot);
    }

    // Driver method

    public Node fromArrayToBinaryTree(String[] arr, Node root, int i) {
        // Base case for recursion
        if (i < arr.length && !arr[i].equals("null")) {
            Node temp = new Node(arr[i].charAt(0),idCounter++);
            root = temp;
            root.parent = FindParent(originalroot, root);

            // insert left child
            root.left = fromArrayToBinaryTree(arr, root.left, 2 * i + 1);

            // insert right child
            root.right = fromArrayToBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    // public void DFSWithBinaryTree(Node node) {
    // if (node == null)
    // return;
    // if(isGoal(originalroot))
    // return;
    // if (node.value == '0'|| node.value == 'V') {
    // node.value = 'C';
    // cameraCount++;
    // if (node.left != null && node.left.value != 'C')
    // node.left.value = 'V';
    // if (node.right != null && node.right.value != 'C')
    // node.right.value = 'V';
    // if (node.parent!=null && node.parent.value != 'C')
    // node.parent.value = 'V';
    // }
    public List<Integer> assignPossibleCamera(Node refrence, List<Integer> list) {
        if (refrence == null)
            return null;
            List<Integer> l = assignPossibleCamera(refrence.right,list);
            if(l!=null)
            return l;
             l = assignPossibleCamera(refrence.left,list);
            if(l!=null)
            return l;          
        if (list.contains(refrence.id)) {
            refrence.value = 'C';            
            list.remove(Integer.valueOf(refrence.id));
            if (refrence.left != null && refrence.left.value != 'C')
                refrence.left.value = 'V';
            if (refrence.right != null && refrence.right.value != 'C')
                refrence.right.value = 'V';
            if (refrence.parent != null && refrence.parent.value != 'C')
                refrence.parent.value = 'V';
   return list;
    }
    return null;
    }
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
    
    public boolean isGoal(Node node) {
        if (node == null) {
            return true;
        }
        if (node.value == '0') {
            return false;
        }
        return isGoal(node.left) && isGoal(node.right);
    }
  
    public void findViewsAndZeroes(Node node) {
        if (node == null)
            return ;
        if (node.value == '0' || node.value == 'V')
            viewsAndZerosRefrences.add(node.id);
        findViewsAndZeroes(node.left);

        findViewsAndZeroes(node.right);
    }

    public Node FindParent(Node root, Node child) {
        if (root == null || child == null)
            return null;

        else if ((root.right != null && root.right.equals(child) || root.left != null && root.left.equals(child)))
            return root;
        else {
            Node found = FindParent(root.right, child);
            if (found == null) {
                found = FindParent(root.left, child);
            }
            return found;
        }
    }

    public void printOutput() {
        System.out.println("\nThe solution found by the search DFS strategy \nMinimum number of cameras required is "
                + countCameras(originalroot));
    }

}

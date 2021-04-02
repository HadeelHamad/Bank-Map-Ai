import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

    // Root of Binary Tree
    Node originalroot;
    int cameraCount=0;
    List<Node> viewsAndZerosRefrences;
    BinaryTree() {
        originalroot = null;
        viewsAndZerosRefrences = new ArrayList<Node>();
    }
    BinaryTree(String[] floorArray) {
        originalroot = fromArrayToBinaryTree(floorArray, null, 0);
        viewsAndZerosRefrences = new ArrayList<Node>();
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
            Node temp = new Node(arr[i].charAt(0));
            root = temp;
            root.parent = FindParent(originalroot,root);

            // insert left child
            root.left = fromArrayToBinaryTree(arr, root.left, 2 * i + 1);

            // insert right child
            root.right = fromArrayToBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    // public void DFSWithBinaryTree(Node node) {
    //     if (node == null)
    //         return;
    //     if(isGoal(originalroot))
    //     return;
    //     if (node.value == '0'|| node.value == 'V') {
    //         node.value = 'C';
    //         cameraCount++;
    //     if (node.left != null && node.left.value != 'C')
    //         node.left.value = 'V';
    //     if (node.right != null && node.right.value != 'C')
    //         node.right.value = 'V';
    //     if (node.parent!=null && node.parent.value != 'C')
    //         node.parent.value = 'V';
    //     }
        public void assignPossibleCamera(Node refrence) {
            if (refrence == null)
                return;
            if (viewsAndZerosRefrences.contains(refrence)) {
                refrence.value = 'C';
                viewsAndZerosRefrences.remove(refrence);
       if (refrence.left != null && refrence.left.value != 'C')
            refrence.left.value = 'V';
            if (refrence.right != null && refrence.right.value != 'C')
            refrence.right.value = 'V';
            if (refrence.parent!=null && refrence.parent.value != 'C')
            refrence.parent.value = 'V';

            }else{
            assignPossibleCamera(refrence.left);
            assignPossibleCamera(refrence.right);
            }
    }

public int countPossibleChildren(Node node){
    int possibleChildrenCounter=0;
    if (node.value == '0'|| node.value == 'V')
    possibleChildrenCounter++;
    if (node.left != null)
    possibleChildrenCounter+= countPossibleChildren(node.left);
if (node.right != null)
possibleChildrenCounter+= countPossibleChildren(node.right);
return possibleChildrenCounter;


}
public boolean isGoal(Node node) {
    if (node == null) {
        return true;
    }
    if (node.value=='0') {
        return false;
    }
    return isGoal(node.left) && isGoal(node.right);
}

public List<Node> findViewsAndZeroes(Node node){
    if (node==null)
    return null;
    if (node.value == '0'|| node.value == 'V')
viewsAndZerosRefrences.add(node);
        findViewsAndZeroes(node.left);

        findViewsAndZeroes(node.right);
        return viewsAndZerosRefrences;
}


    public Node FindParent(Node root, Node child)
    {
        if (root == null || child == null)
             return null;
        
        else if ( (root.right != null && root.right.equals(child) || root.left != null && root.left.equals(child)))
        return root;
        else
        {
            Node found = FindParent(root.right, child);
            if (found == null)
            {
                found = FindParent(root.left, child);
            }
            return found;
        }
    }
    public void printOutput(){
        System.out.println("\nThe solution found by the search DFS strategy \nMinimum number of cameras required is "+cameraCount);
    }
   

}

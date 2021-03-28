
public class BinaryTree {

    // Root of Binary Tree
    Node originalroot;

    BinaryTree(String[] floorArray) {
        originalroot = fromArrayToBinaryTree(floorArray, null, 0);
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

    public void DFSWithBinaryTree(Node node) {
        if (node == null)
            return;
        if(isGoal(originalroot))
        return;
        if (node.value == '0'|| node.value == 'V') {
            node.value = 'C';
        if (node.left != null && node.left.value != 'C')
            node.left.value = 'V';
        if (node.right != null && node.right.value != 'C')
            node.right.value = 'V';
        if (node.parent!=null && node.parent.value != 'C')
            node.parent.value = 'V';
        }

        DFSWithBinaryTree(node.left);

        DFSWithBinaryTree(node.right);
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
   

}

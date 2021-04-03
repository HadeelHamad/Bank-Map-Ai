public class Node {
    char value;// either '0','V', or 'C'
    Node left, right, parent;
    int id;// node identifier
    // --------------------------------------------------------------------------------------------------------------------

    public Node(char val, int num) {
        value = val;
        left = right = null;
        id = num;
    }
    // --------------------------------------------------------------------------------------------------------------------
}

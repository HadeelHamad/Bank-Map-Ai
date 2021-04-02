import java.util.List;
import java.util.Stack;

public class DFSwithStack {
    Stack<BinaryTree> frontierStack;

    public DFSwithStack() {
        frontierStack = new Stack<BinaryTree>();
    }
    public BinaryTree search(BinaryTree root) {

        frontierStack.add(root);
        while (!frontierStack.isEmpty()) {

            BinaryTree state = frontierStack.pop();
            if (state.isGoal(state.originalroot)){
                return state;
            }
            // expand
            int numOfChildren = state.countPossibleChildren(state.originalroot);
            // prepare list
            state.findViewsAndZeroes(state.originalroot);// prepare list
            List<Integer> list = state.viewsAndZerosRefrences;
            for (int i = 0; i < numOfChildren; i++) {
               list= expand(state,list);
            }

        }
        return null;
    }

    public List<Integer> expand(BinaryTree state,List<Integer> list) {

        BinaryTree newState = copyTree(state.originalroot);
        // recursive
        // search by reference
        list = newState.assignPossibleCamera(newState.originalroot,list);
        // add to frontier
        frontierStack.push(newState);
        newState.printPreorder();
        return list;
    }

    public BinaryTree copyTree(Node focusNode) {
        BinaryTree bt = new BinaryTree();
        bt.originalroot = preOrderCopy(focusNode);
        return bt;
    }

    private Node preOrderCopy(Node focusNode) {
        if (focusNode == null) {
            // base case
            return null;
        }
        Node copy = new Node(focusNode.value,focusNode.id);
        copy.left = preOrderCopy(focusNode.left);
        copy.right = preOrderCopy(focusNode.right);
        return copy;
    }
}

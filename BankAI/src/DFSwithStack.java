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
            System.out.println("frontier stack is  not empty");

            BinaryTree state = frontierStack.pop();

            if (state.isGoal(state.originalroot))
                return state;

            // expand
            int numOfChildren = state.countPossibleChildren(state.originalroot);
            System.out.println("return of countPossibleChildren() is " + numOfChildren);
            // List<Node> refrencesList = state.findViewsAndZeroes(state.originalroot);//
            // prepare list
            System.out.println("return of findViewsAndZeroes() is " + refrencesList.size());

            for (int i = 0; i < numOfChildren; i++) {
                expand(state, refrencesList);
            }

        }
        System.out.println("frontier stack is empty");
        return null;
    }

    public void expand(BinaryTree state, List<Node> refrencesList) {

        System.out.println("inside expand method");

        BinaryTree newState = copyTree(state.originalroot);
        // recursive
        // newState.findViewsAndZeroes(newState.originalroot);// prepare list
        // search by reference
        List<Node> refrencesList = state.findViewsAndZeroes(state.originalroot);// prepare list

        newState.viewsAndZerosRefrences = refrencesList;
        newState.assignPossibleCamera(newState.originalroot);
        refrencesList = newState.viewsAndZerosRefrences;
        // add to frontier
        frontierStack.push(newState);
    }

    public BinaryTree copyTree(Node focusNode) {
        System.out.println("inside copyTree method");
        BinaryTree bt = new BinaryTree();
        bt.originalroot = preOrderCopy(focusNode);
        return bt;
    }

    private Node preOrderCopy(Node focusNode) {
        System.out.println("inside preOrderCopy method");
        if (focusNode == null) {
            // base case
            return null;
        }
        Node copy = new Node(focusNode.value);
        copy.left = preOrderCopy(focusNode.left);
        copy.right = preOrderCopy(focusNode.right);
        return copy;
    }
}

import java.util.List;
import java.util.Stack;

public class DFSwithStack {
    // Defining the frotier as a stack of binary tree
    Stack<BinaryTree> frontierStack;

    // --------------------------------------------------------------------------------------------------------------------
    // Constructor
    public DFSwithStack() {
        frontierStack = new Stack<BinaryTree>();
    }

    // --------------------------------------------------------------------------------------------------------------------
    public BinaryTree search(BinaryTree initialState) {
        // adding the initial state to the frontier
        frontierStack.add(initialState);
        // continue looping until the frontier is empty (all nodes have been expanded)
        // OR the solution state was found
        while (!frontierStack.isEmpty()) {
            // remove node(state) from the frontier
            BinaryTree state = frontierStack.pop();
            // examin the removed state => is it a goal state?
            if (state.isGoal(state.originalroot)) {
                // in case of goal state then return it => serach strategy ends successfully :)
                return state;
            }
            // the removed state is NOT a goal state => expand it (generate its children
            // states)
            // First, count the number of possible childern
            int numOfChildren = state.countPossibleChildren(state.originalroot);
            /*
             * Second, run findViewsAndZeroes method to update the list of nodes IDs that
             * are qualified to be children of the current state(nodes that can be occupied
             * by a camera)
             */
            state.findViewsAndZeroes(state.originalroot);
            // This list will keep a refrence of the updated list from the previous step
            List<Integer> list = state.viewsAndZerosRefrences;
            // Third, generate the children and add them to the frontier
            for (int i = 0; i < numOfChildren; i++) {
                // use the last updated list every time you generate a new child (to avoid
                // duplicate children)
                list = expand(state, list);
            }

        }
        // the frontier is empty (all nodes have been expanded) AND no solution found!
        // => return null as a goal state indicating failure
        return null;
    }

    // --------------------------------------------------------------------------------------------------------------------
    public List<Integer> expand(BinaryTree state, List<Integer> list) {
        // take a copy of the parent state
        BinaryTree newState = makeCopyOfBinaryTree(state.originalroot);
        // running assignPossibleCamera method resulting in assigning a camera in one of
        // the qualified nodes(their ids are in the given list)
        // note: this method will return the updated list (after removing the id of the
        // node which was occupied by a camera)
        list = newState.assignPossibleCamera(newState.originalroot, list);
        // adding the new chlid to the frotier
        frontierStack.push(newState);
        // return the updated list to be used in the next iteration
        return list;
    }

    // --------------------------------------------------------------------------------------------------------------------
    public BinaryTree makeCopyOfBinaryTree(Node nodeToBeCopied) {
        // create a new BT object
        BinaryTree copyBT = new BinaryTree();
        copyBT.originalroot = recursiveCopy(nodeToBeCopied);
        return copyBT;
    }

    // --------------------------------------------------------------------------------------------------------------------
    private Node recursiveCopy(Node nodeToBeCopied) {
        if (nodeToBeCopied == null) {
            return null;
        }
        // create a new node with the same data of the nodeToBeCopied
        Node copy = new Node(nodeToBeCopied.value, nodeToBeCopied.id);
        copy.left = recursiveCopy(nodeToBeCopied.left);
        copy.right = recursiveCopy(nodeToBeCopied.right);
        return copy;
    }
    // --------------------------------------------------------------------------------------------------------------------
}

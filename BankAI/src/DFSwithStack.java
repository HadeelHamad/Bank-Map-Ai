import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class DFSwithStack {
    // Defining the frotier as a stack of binary tree
    Stack<BinaryTree> frontierStack;
BinaryTree goalState ;
int m,b;

    // --------------------------------------------------------------------------------------------------------------------
    // Constructor
    public DFSwithStack() {
        frontierStack = new Stack<BinaryTree>();
        goalState = null;
    }

    // --------------------------------------------------------------------------------------------------------------------
    public BinaryTree search(BinaryTree initialState) {
        // adding the initial state to the frontier
        frontierStack.add(initialState);
        //the maximum branching factor is the number of chilldren for the inital state since it has more possipolites for camera places
        b = initialState.countPossibleChildren(initialState.originalroot);
        // continue looping until the frontier is empty (all nodes have been expanded)
        // OR the solution state was found
        while (!frontierStack.isEmpty()) {
            // remove node(state) from the frontier
            BinaryTree state = frontierStack.pop();
            // examin the removed state => is it a goal state?
            if (state.isGoal(state.originalroot)) {
                // in case of goal state then return it => serach strategy ends successfully :)
                if(goalState==null){
                goalState = state;
                /*initalize m with the number of cameras for the first goal state found
                Why did we consider the value of m (maximum length of any path in the state space) 
                is the number of cameras of the goal stete?
                Because in this problem all goal states are leafs in the search tree, 
                and the depth increases by adding a camera*/
                m = goalState.countCameras(goalState.originalroot);
                }else{
                    if(goalState.countCameras(goalState.originalroot) > state.countCameras(state.originalroot))
                    goalState = state;
                    else{
                        /* Each time we found a goal state that is worse than previous found goal state, worese means one with more cameras, 
                        we will consider its depth(num of cameras) as a value of m since m is the MAXIMUM depth
                        */
                        m = state.countCameras(state.originalroot);
                    }
                    
                }
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
            state.findZeroes(state.originalroot);
            // This list will keep a refrence of the updated list from the previous step
            List<Integer> list = state.zerosRefrences;
            // Third, generate the children and add them to the frontier
            for (int i = 0; i < numOfChildren; i++) {
                // use the last updated list every time you generate a new child (to avoid
                // duplicate children)
                list = expand(state, list);
            }
        }
        
        // the frontier is empty (all nodes have been expanded) AND no solution found!
        // => return null as a goal state indicating failure
        return goalState;
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
        //System.out.println("Before pushing to frontier ");
        //TreeVisualizer.printNode(newState.originalroot);
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
    public void printOutput(BinaryTree state) {
        System.out.println(
                "\n-According to DFS strategy, at least "+ state.countCameras(state.originalroot)+" surveillance cameras needed to monitor the whole floor\n-Performance of DFS: ");
                System.out.println("Time Complexity is O(b^m) where b (branching factor) = "+b+" and m (maximum length of any path in the state space) = "+m);
                System.out.println("O(b^m) = ("+b+"^"+m+") = O("+Math.pow(b, m)+")");
                System.out.println("Space Complexity is O(bm) where b (branching factor) = "+b+" and m (maximum length of any path in the state space) = "+m);
                System.out.println("O(bm) = ("+b+"*"+m+") = O("+(b*m)+")");
            }
    // --------------------------------------------------------------------------------------------------------------
}

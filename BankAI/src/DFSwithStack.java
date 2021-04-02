import java.util.List;
import java.util.Stack;

public class DFSwithStack {
    Stack<BinaryTree> frontierStack;
public DFSwithStack(){
    frontierStack  = new Stack<BinaryTree>();
}

    public BinaryTree search(BinaryTree root) {
        frontierStack.add(root);
        while (!frontierStack.isEmpty()) {
        BinaryTree state = frontierStack.pop();
        if(state.isGoal(state.originalroot))
            return state;    
        //expand
int numOfChildren = state.countPossibleChildren(state.originalroot);

List<Node> refrencesList = state.findViewsAndZeroes(state.originalroot);// prepare list
for(int i =0; i<numOfChildren;i++){
    expand(state,refrencesList);
}
       		
                }
                return null;
            }

    public void expand(BinaryTree state,List<Node> refrencesList){
        BinaryTree newState = copyTree(state.originalroot);
//recursive
//newState.findViewsAndZeroes(newState.originalroot);// prepare list
//search by reference
newState.viewsAndZerosRefrences = refrencesList;
newState.assignPossibleCamera(newState.originalroot);
refrencesList = newState.viewsAndZerosRefrences;
//add to frontier
frontierStack.push(newState);
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
        Node copy = new Node(focusNode.value);
        copy.left = preOrderCopy(focusNode.left);
        copy.right = preOrderCopy(focusNode.right);
        return copy;
    }
}

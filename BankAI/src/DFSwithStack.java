import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.stream.events.StartElement;

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

            if (state.isGoal(state.originalroot)){
            System.out.println("4444444444444444444444444444 WE REACH THE GOAL");
                return state;
            }
            // expand
            System.out.println("We Did NOT REACH THE GOAL!!!!!!!!!!!!!!!!!");
            int numOfChildren = state.countPossibleChildren(state.originalroot);
            System.out.println("return of countPossibleChildren() is " + numOfChildren);
            // prepare list
            state.findViewsAndZeroes(state.originalroot);// prepare list
            List<Integer> list = state.viewsAndZerosRefrences;// prepare list
            for (int i = 0; i < numOfChildren; i++) {
               list= expand(state,list);
            }

        }
        System.out.println("frontier stack is empty");
        return null;
    }

    public List<Integer> expand(BinaryTree state,List<Integer> list) {

        System.out.println("inside expand method");

        BinaryTree newState = copyTree(state.originalroot);
        // recursive
        // search by reference
        System.out.println("==================================================The size of list is"+list.size());
        //newState.viewsAndZerosRefrences= list;
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

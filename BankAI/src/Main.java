import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner object to read input from the user
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the file's name that contains the floor map of the bank");
        // Read the file name
        String fileName = s.nextLine();
        // Read from the specified file, then return an array of strings representing
        // the tree elements
        // --- String[] floorArray = FileReader.fromFileToArray(fileName);
        Character[] floorCharArray = FileReader.fromFileToCharacterArray(fileName);
        // --------------------------------------------------------------------------------------------------------------------
        // Creating new binary tree object representing the initial state => all
        // elements are '0'
        BinaryTree initaialState = new BinaryTree(floorCharArray);
        TreeVisualizer.printNode(initaialState.originalroot);

        // Creating the object of the search strategy "DFS"
        DFSwithStack search = new DFSwithStack();
        // Run the DFS algorithim staring by the initial state
        BinaryTree goal = search.search(initaialState);
        // --------------------------------------------------------------------------------------------------------------------
        // if the goal was found
        if (goal != null) {
            System.out.println("Preorder traversal of binary tree is ");
            goal.printTreePostorder();
            /*
             * printing the output (number of cameras required to monitor the floor +
             * performance of the search strategy)
             */
            goal.printOutput();
            // Printing the tree representation of the map floor after placing the
            // surveillance cameras
            TreeVisualizer.printNode(goal.originalroot);
        } else {
            System.out.println("No goal was found !");

        }
        s.close();
    }

}

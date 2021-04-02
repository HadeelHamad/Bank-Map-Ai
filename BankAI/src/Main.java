import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the file name ");
        String fileName = s.nextLine();
        String[] floorArray = FileReader.fromFileToArray(fileName);
        BinaryTree initaialState = new BinaryTree(floorArray);
        DFSwithStack search = new DFSwithStack();
        BinaryTree goal = search.search(initaialState);

        if(goal!=null){
       // floorBinaryTree.DFSWithBinaryTree(floorBinaryTree.originalroot);
        System.out.println("Preorder traversal of binary tree is ");
        goal.printPreorder();
        goal.printOutput();
        }else{
            System.out.println("No goal!!!");

        }
    }

}

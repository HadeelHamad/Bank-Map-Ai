import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the file name ");
        String fileName = s.nextLine();
        String[] floorArray = FileReader.fromFileToArray(fileName);
        BinaryTree floorBinaryTree = new BinaryTree(floorArray);
        floorBinaryTree.DFSWithBinaryTree(floorBinaryTree.originalroot);
        System.out.println("Preorder traversal of binary tree is ");
        floorBinaryTree.printPreorder();
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//The original code was written by [michal.kreuzman] and was found in this link https://stackoverflow.com/a/4973083

class TreeVisualizer {
    // This class is used to visualize tree elements in java
    public static <T extends Comparable<?>> void printNode(Node root) {
        int maxLevel = TreeVisualizer.maxLevel(root);
        System.out.println("max level is "+maxLevel);
        printNodeInternal((List<Node>) Collections.singletonList(root), 1, maxLevel);
    }

    // --------------------------------------------------------------------------------------------------------------------
    private static <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || TreeVisualizer.isAllElementsNull(nodes))
            return;
        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
        TreeVisualizer.printWhitespaces(firstSpaces);
        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            TreeVisualizer.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                TreeVisualizer.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    TreeVisualizer.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    TreeVisualizer.printWhitespaces(1);

                TreeVisualizer.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    TreeVisualizer.printWhitespaces(1);

                TreeVisualizer.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    // --------------------------------------------------------------------------------------------------------------------
    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    // --------------------------------------------------------------------------------------------------------------------
    private static <T extends Comparable<?>> int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(TreeVisualizer.maxLevel(node.left), TreeVisualizer.maxLevel(node.right)) + 1;
    }

    // --------------------------------------------------------------------------------------------------------------------
    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
    // --------------------------------------------------------------------------------------------------------------------
}

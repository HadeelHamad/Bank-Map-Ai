import java.util.Stack;

public class App {

    static class Coordinate {
        int i, j;

        public Coordinate(int first, int second) {
            this.i = first;
            this.j = second;
        }
    }

    public void DFS(char[][] grid) {
        int h = grid.length;
        if (h == 0)
            return;
        int l = grid[0].length;
        boolean[][] visited = new boolean[h][l];
        Stack<Coordinate> stack = new Stack<>();
        stack.push(new Coordinate(0, 0));
        System.out.println("Depth-First Traversal: ");
        while (!stack.empty()) {
            Coordinate x = stack.pop();
            int row = x.i;
            int col = x.j;
            if (row < 0 || col < 0 || row >= h || col >= l || visited[row][col])
                continue;
            visited[row][col] = true;
            System.out.print(grid[row][col] + " ");
            stack.push(new Coordinate(row, (col - 1))); // go left
            stack.push(new Coordinate(row, (col + 1))); // go right
            stack.push(new Coordinate(row - 1, col)); // go up
            stack.push(new Coordinate(row + 1, col)); // go down
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[][] { { '-', '-', '-', '-', '-', '-' }, { '-', 'x', 'x', '-', '-', '-' },
                { '-', '-', 'x', '-', '-', '-' }, { '-', '-', '-', '-', '-', '-' }, { '-', '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-', '-' } }; // map
                                                    // from
                                                    // file
        App d = new App();
        d.DFS(grid);
    }
}
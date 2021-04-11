
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the file name ");
        String fileName = s.nextLine();
        char[][] grid = FileReader.readFile(fileName);
        System.out.println("Coordinates of Cameras:");
        traverseDiagonally(grid);
        System.out.println("Bank's Map:");
        printMatrix(grid);
        s.close();
    }

    // assign cameras in diagonal order
    static void traverseDiagonally(char matrix[][]) {
        // grid size
        int n = matrix.length;
        int row = 0, column = 0;
        boolean row_increment = false;

        for (int len = 1; len <= n; ++len) {
            for (int i = 0; i < len; ++i) {

                if (matrix[row][column] == '-') {
                    matrix[row][column] = 'c';
                    System.out.println("     " + (row+1) + " " + (1+column));
                    // set views in both directions vertically, horizontally and diagonally
                    setDownViews(matrix, row + 1, column);
                    setUpViews(matrix, row - 1, column);
                    setLeftViews(matrix, row, column - 1);
                    setRightViews(matrix, row, column + 1);
                    setLeftUpViews(matrix, row - 1, column - 1);
                    setLeftDownViews(matrix, row + 1, column - 1);
                    setRightDownViews(matrix, row + 1, column + 1);
                    setRightUpViews(matrix, row - 1, column + 1);
                }

                if (i + 1 == len)
                    break;
                if (row_increment) {
                    ++row;
                    --column;
                } else {
                    --row;
                    ++column;
                }
            }

            if (len == n)
                break;

            if (row_increment) {

                ++row;
                row_increment = false;

            } else {

                ++column;
                row_increment = true;

            }
        }

        if (row == 0) {
            if (column == n - 1)
                ++row;
            else
                ++column;
            row_increment = true;
        } else {
            if (row == n - 1)
                ++column;
            else
                ++row;
            row_increment = false;
        }
        int MAX = n - 1;
        for (int len, diag = MAX; diag > 0; --diag) {

            if (diag > n)
                len = n;
            else
                len = diag;

            for (int i = 0; i < len; ++i) {
                if (matrix[row][column] == '-') {
                    matrix[row][column] = 'c';
                    System.out.println("     " + (row+1) + " " + (1+column));
                    // set views in both directions vertically, horizontally and diagonally
                    setDownViews(matrix, row + 1, column);
                    setUpViews(matrix, row - 1, column);
                    setLeftViews(matrix, row, column - 1);
                    setRightViews(matrix, row, column + 1);
                    setLeftUpViews(matrix, row - 1, column - 1);
                    setLeftDownViews(matrix, row + 1, column - 1);
                    setRightDownViews(matrix, row + 1, column + 1);
                    setRightUpViews(matrix, row - 1, column + 1);
                }
                if (i + 1 == len)
                    break;

                if (row_increment) {
                    ++row;
                    --column;

                } else {
                    ++column;
                    --row;

                }
            }
            if (row == 0 || column == n - 1) {
                if (column == n - 1)
                    ++row;
                else
                    ++column;
                row_increment = true;
            }

            else if (column == 0 || row == n - 1) {
                if (row == n - 1)
                    ++column;
                else
                    ++row;

                row_increment = false;
            }

        }
    }

    // prints the matrix values
    static void printMatrix(char matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.print("\n");
        }
    }

    // set views to the right down diagonal of an added camera
    static void setRightDownViews(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid.length || i < 0 || j < 0)
            return;
        if (grid[i][j] == 'x')
            return;
        grid[i][j] = 'v';
        setRightDownViews(grid, i + 1, j + 1);
    }

    // set views to the right up diagonal of an added camera
    static void setRightUpViews(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid.length || i < 0 || j < 0)
            return;
        if (grid[i][j] == 'x')
            return;
        grid[i][j] = 'v';
        setRightUpViews(grid, i - 1, j + 1);
    }

    // set views to the right side of an added camera
    static void setRightViews(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid.length || i < 0 || j < 0)
            return;
        if (grid[i][j] == 'x')
            return;
        grid[i][j] = 'v';
        setRightViews(grid, i, j + 1);
    }

    // set views to the left up diagonal of an added camera
    static void setLeftUpViews(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid.length || i < 0 || j < 0)
            return;
        if (grid[i][j] == 'x')
            return;
        grid[i][j] = 'v';
        setLeftUpViews(grid, i - 1, j - 1);
    }

    // set views to the left down diagonal of an added camera
    static void setLeftDownViews(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid.length || i < 0 || j < 0)
            return;
        if (grid[i][j] == 'x')
            return;
        grid[i][j] = 'v';
        setLeftDownViews(grid, i + 1, j - 1);
    }

    // set views to the left side of an added camera
    static void setLeftViews(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid.length || i < 0 || j < 0)
            return;
        if (grid[i][j] == 'x')
            return;
        grid[i][j] = 'v';
        setLeftViews(grid, i, j - 1);
    }

    // set views to the up side of an added camera
    static void setUpViews(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid.length || i < 0 || j < 0)
            return;
        if (grid[i][j] == 'x')
            return;
        grid[i][j] = 'v';
        setUpViews(grid, i - 1, j);
    }

    // set views to the down side of an added camera
    static void setDownViews(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid.length || i < 0 || j < 0)
            return;
        if (grid[i][j] == 'x')
            return;
        grid[i][j] = 'v';
        setDownViews(grid, i + 1, j);
    }

    // public static void DFS(char[][] grid) {
    // int l = grid[0].length;
    // boolean[][] visited = new boolean[h][l];
    // Stack<Coordinate> stack = new Stack<>();
    // stack.push(new Coordinate(0, 0));
    // System.out.println("Depth-First Traversal: ");
    // while (!stack.empty()) {
    // Coordinate x = stack.pop();
    // int row = x.i;
    // int col = x.j;
    // if (row < 0 || col < 0 || row >= h || col >= l || visited[row][col])
    // continue;
    // visited[row][col] = true;
    // System.out.print(grid[row][col] + " ");
    // stack.push(new Coordinate(row, (col - 1))); // go left
    // stack.push(new Coordinate(row, (col + 1))); // go right
    // stack.push(new Coordinate(row - 1, col)); // go up
    // stack.push(new Coordinate(row + 1, col)); // go down
    // }
    // }
}
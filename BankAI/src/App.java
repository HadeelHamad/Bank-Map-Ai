
import java.util.Scanner;
public class App {

    static class Coordinate {
        int i, j;

        public Coordinate(int first, int second) {
            this.i = first;
            this.j = second;
        }
    }
    
    
 
    // A utility function to find min
    // of two integers
    static int min(int a, int b) 
    { 
        return (a < b) ? a : b; 
    }
 
    // A utility function to find min
    // of three integers
    static int min(int a, int b, int c)
    {
        return min(min(a, b), c);
    }
 
    // A utility function to find max
    // of two integers
    static int max(int a, int b) 
    { 
         return (a > b) ? a : b; 
    }
 
    // The main function that prints given
    // matrix in diagonal order
    static void traverseDiagonally(char matrix[][])
    {
        int n = matrix.length;
        // There will be ROW+COL-1 lines in the output
        for (int line = 1;line <= (n + n - 1);line++) {
 
            // Get column index of the first 
            // element in this line of output.
            // The index is 0 for first ROW
            // lines and line - ROW for remaining lines
            int start_col = max(0, line - n);
 
            // Get count of elements in this line. 
            // The count of elements is equal to 
            // minimum of line number, COL-start_col and ROW
            int count = min(line, (n - start_col), 
                            n);
 
            // Print elements of this line
            for (int j = 0; j < count; j++){
            int row = min(n, line) - j- 1;
            int col = start_col + j;

                if(matrix[row][col]=='-'){
                    matrix[row][col]= 'c';
                    setDownViews(matrix, row+1, col);
                    setUpViews(matrix, row-1, col);
                    setLeftViews(matrix, row, col-1);
                    setRightViews(matrix, row, col+1);
                    setLeftUpViews(matrix,row-1,col-1);
                    setLeftDownViews(matrix,row+1,col-1);
                    setRightDownViews(matrix,row+1,col+1);
                    setRightUpViews(matrix,row-1,col+1);
                }}
 
            // Print elements of next diagonal on next line
                 }
    }
 
    // Utility function to print a matrix
    static void printMatrix(char matrix[][])
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.print("\n");
        }
    }static void setRightDownViews(char[][] grid,int i, int j){
        if(i>=grid.length || j>=grid.length || i<0|| j<0)
        return ;
        if(grid[i][j]=='x')
        return ;
        grid[i][j]= 'v';
        setRightDownViews(grid, i+1, j+1);
            }
            static void setRightUpViews(char[][] grid,int i, int j){
                if(i>=grid.length || j>=grid.length || i<0|| j<0)
                return ;
                if(grid[i][j]=='x')
                return ;
                grid[i][j]= 'v';
                setRightUpViews(grid, i-1, j+1);
                    }
    static void setRightViews(char[][] grid,int i, int j){
if(i>=grid.length || j>=grid.length || i<0|| j<0)
return ;
if(grid[i][j]=='x')
return ;
grid[i][j]= 'v';
setRightViews(grid, i, j+1);
    }
    static void setLeftUpViews(char[][] grid,int i, int j){
        if(i>=grid.length || j>=grid.length || i<0|| j<0)
        return ;
        if(grid[i][j]=='x')
        return ;
        grid[i][j]= 'v';
        setLeftUpViews(grid, i-1, j-1);
            }
            static void setLeftDownViews(char[][] grid,int i, int j){
                if(i>=grid.length || j>=grid.length || i<0|| j<0)
                return ;
                if(grid[i][j]=='x')
                return ;
                grid[i][j]= 'v';
                setLeftDownViews(grid, i+1, j-1);
                    }
    static void setLeftViews(char[][] grid,int i, int j){
        if(i>=grid.length || j>=grid.length || i<0|| j<0)
        return ;
        if(grid[i][j]=='x')
        return ;
        grid[i][j]= 'v';
        setLeftViews(grid, i, j-1);
            }
            static void setUpViews(char[][] grid,int i, int j){
                if(i>=grid.length || j>=grid.length || i<0|| j<0)
                return ;
                if(grid[i][j]=='x')
                return ;
                grid[i][j]= 'v';
                setUpViews(grid, i-1, j);
                    }
                    static void setDownViews(char[][] grid,int i, int j){
                        if(i>=grid.length || j>=grid.length || i<0|| j<0)
                        return ;
                        if(grid[i][j]=='x')
                        return ;
                        grid[i][j]= 'v';
                        setDownViews(grid, i+1, j);
                            }


    // public static void DFS(char[][] grid) {
    //     int l = grid[0].length;
    //     boolean[][] visited = new boolean[h][l];
    //     Stack<Coordinate> stack = new Stack<>();
    //     stack.push(new Coordinate(0, 0));
    //     System.out.println("Depth-First Traversal: ");
    //     while (!stack.empty()) {
    //         Coordinate x = stack.pop();
    //         int row = x.i;
    //         int col = x.j;
    //         if (row < 0 || col < 0 || row >= h || col >= l || visited[row][col])
    //             continue;
    //         visited[row][col] = true;
    //         System.out.print(grid[row][col] + " ");
    //         stack.push(new Coordinate(row, (col - 1))); // go left
    //         stack.push(new Coordinate(row, (col + 1))); // go right
    //         stack.push(new Coordinate(row - 1, col)); // go up
    //         stack.push(new Coordinate(row + 1, col)); // go down
    //     }
    // }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("please enter the file name ");
        String fileName = s.nextLine();
        System.out.println(fileName);
        char[][] grid = FileReader.readFile(fileName);
        traverseDiagonally(grid);
        printMatrix(grid);
    }
   
}
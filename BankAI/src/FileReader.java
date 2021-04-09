import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
  // --------------------------------------------------------------------------------------------------------------------
  /*
   * The method is used to read the grid information from a file, then returns 2D
   * aaray of char elements each element is either '-' or 'x' at the begining
   * Note: This methos was used for the first version of the project
   */
  public static char[][] readFile(String fileName) {
    int gridSize = 0;
    char[][] grid = null;
    try {
      File file = new File("src/" + fileName + ".txt");
      Scanner scanner = new Scanner(file);
      if (scanner.hasNextLine()) {
        // the first line of the text file contains the grid size
        gridSize = Integer.parseInt(scanner.nextLine());
        System.out.println("The grid size is " + gridSize + " x " + gridSize);
        // creating the grid
        grid = new char[gridSize][gridSize];
        // assigning '-' for all elements of the grid
        for (int row = 0; row < grid.length; row++) {
          for (int col = 0; col < grid[row].length; col++) {
            grid[row][col] = '-';
          }
        }
        /*
         * Now, we have a grid with '-' for all its elements So, continue looping to
         * read all lines in the Each line represents the index of an obstacle => 'x'
         */
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          int i = Integer.parseInt(line.substring(0, line.indexOf(' '))) - 1;
          int j = Integer.parseInt(line.substring(line.indexOf(' ') + 1)) - 1;
          grid[i][j] = 'x';
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("There is an error while reading the " + fileName + " file !");
      e.printStackTrace();
    } catch (NumberFormatException e) {
      System.out.println("The read input is not a number!");
      e.printStackTrace();
    }
    return grid;
  }

  // --------------------------------------------------------------------------------------------------------------------
  /*
   * The method is used to read the tree elements from a file, then returns an
   * aaray of string elements each element is either "0" or "null" at the begining
   * Note: This methos was used for the second version of the project
   */
  public static String[] fromFileToArray(String fileName) {
    String[] floor = null;
    try {
      File file = new File("src/" + fileName + ".txt");
      Scanner scanner = new Scanner(file);
      if (scanner.hasNextLine()) {
        String str = scanner.nextLine();
        String floorMapString = str.substring(1, str.length() - 1);
        floor = floorMapString.split(",");
      }
      scanner.close();

    } catch (FileNotFoundException e) {
      System.out.println("There is an error while reading the " + fileName + " file !");
      e.printStackTrace();
    }
    return floor;
  }


  // --------------------------------------------------------------------------------------------------------------------
  
  public static Character[] fromFileToCharacterArray(String fileName) {
    String[] floorString = null;
    try {
      File file = new File("src/" + fileName + ".txt");
      System.out.println(file.getAbsolutePath());
      Scanner scanner = new Scanner(file);
      if (scanner.hasNextLine()) {
        String str = scanner.nextLine();
        String floorMapString = str.substring(1, str.length() - 1);
        floorString = floorMapString.split(",");
      }
      scanner.close();

    } catch (FileNotFoundException e) {
      System.out.println("There is an error while reading the " + fileName + " file !");
      e.printStackTrace();
    }
    Character[] floor = new Character[floorString.length];
for(int i = 0; i< floor.length;i++){
    try{
     
  floor[i] = floorString[i].charAt(0);
      
    }catch(NullPointerException e){
      floor[i]=null;

    }
  }
    return floor;
  }

}

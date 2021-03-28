import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
  public static char[][] readFile(String fileName) {
    int gridSize = 0;
    char[][] grid = null;
    try {
      File file = new File("src/" + fileName + ".txt");
      Scanner scanner = new Scanner(file);
      if (scanner.hasNextLine()) {
        gridSize = Integer.parseInt(scanner.nextLine());
        System.out.println("The grid size is " + gridSize + " x " + gridSize);
        grid = new char[gridSize][gridSize];
        for (int row = 0; row < grid.length; row++) {
          for (int col = 0; col < grid[row].length; col++) {
            grid[row][col] = '-';
          }
        }
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          int i = Integer.parseInt(line.substring(0, line.indexOf(' '))) - 1;
          int j = Integer.parseInt(line.substring(line.indexOf(' ') + 1)) - 1;
          System.out.println("i = " + i + " ,j= " + j);
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

  public static String[] fromFileToArray(String fileName) {
    String[] floor = null;
    try {
      File file = new File("src/" + fileName + ".txt");
      Scanner scanner = new Scanner(file);
      if (scanner.hasNextLine()) {
        String str = scanner.nextLine();
        String floorMapString = str.substring(1, str.length() - 1);
        System.out.println("The floor map string is " + floorMapString);
        floor = floorMapString.split(",");
        System.out.println("The first element in floor[] is " + floor[0]);

      }
      scanner.close();

    } catch (FileNotFoundException e) {
      System.out.println("There is an error while reading the " + fileName + " file !");
      e.printStackTrace();
    }
    return floor;
  }

}

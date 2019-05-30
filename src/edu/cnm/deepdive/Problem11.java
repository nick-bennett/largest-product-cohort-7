package edu.cnm.deepdive;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class Problem11 {

  private static int[][] readFile(String filename) throws IOException {
    try (
        InputStream input = new FileInputStream(filename);
        Reader reader = new InputStreamReader(input);
        BufferedReader buffer = new BufferedReader(reader)
    ) {
      List<int[]> workList = new LinkedList<>();
      String line;
      while ((line = buffer.readLine()) != null) {
        String[] parts = line.trim().split("\\s+");
        int[] row = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
          row[i] = Integer.parseInt(parts[i]);
        }
        workList.add(row);
      }
      return workList.toArray(new int[workList.size()][]);
    }
  }

  private static long getMaxProduct(int[][] data, int productLength) {
    long maxProduct = 0;
    for (int row = 0; row < data.length; row++) {
      for (int col = 0; col < data[row].length; col++) {
        for (Direction direction : Direction.values()) {
          try {
            int rowOffset = direction.getRowOffset();
            int colOffset = direction.getColOffset();
            long product = 1;
            for (int step = 0; step < productLength; step++) {
              product *= data[row + step * rowOffset][col + step * colOffset];
            }
            if (product > maxProduct) {
              maxProduct = product;
            }
          } catch (ArrayIndexOutOfBoundsException e) {
            // DO NOTHING - GET ON WITH LIFE!
          }
        }
      }
    }
    return maxProduct;
  }

  public static void main(String[] args) throws IOException {
    int[][] data = readFile("largest-product-data.txt");
    long maxProduct = getMaxProduct(data, 4);
    System.out.printf("%,d%n", maxProduct);
  }

  private enum Direction {

    NORTH_EAST(-1, 1),
    EAST(0, 1),
    SOUTH_EAST(1, 1),
    SOUTH(1, 0);

    private final int rowOffset;
    private final int colOffset;

    Direction(int rowOffset, int colOffset) {
      this.rowOffset = rowOffset;
      this.colOffset = colOffset;
    }

    public int getRowOffset() {
      return rowOffset;
    }

    public int getColOffset() {
      return colOffset;
    }

  }

}

package edu.cnm.deepdive;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
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

  public static void main(String[] args) throws IOException {
    int[][] data = readFile("largest-product-data.txt");
    System.out.println(Arrays.toString(data));
  }

}

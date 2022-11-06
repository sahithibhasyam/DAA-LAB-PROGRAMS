import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

/**
 *
 */
public class sorted {
  public static void display(int size, int[] elem, int[] x) {
    for (int i = 0; i < size; i++) {
      if (x[i] != 0) {
        System.out.print(elem[i] + " ");
      }
    }
    System.out.println();
  }
  public static void findElements(int size, int[] elem, int[] x,
                                  int index, int currsum, int tgtsum) {
    if (currsum == tgtsum) {
      display(size, elem, x);
      x[index] = 0;
      return;
    }
    if (size == 0 && tgtsum != 0) {
      return;
    }
    if (currsum < tgtsum && index != size - 1) {
      x[index + 1] = 1;
      findElements(size, elem, x, index+1, currsum + elem[index+1], tgtsum);
    }
    if (x[index] != 0) {
      x[index] = 0;
      currsum = currsum - elem[index];
      if (index != size - 1) {
        if (currsum < tgtsum) {
          x[index + 1] = 1;
          findElements(size, elem, x, index, currsum, tgtsum);
        }
      }
    }
  }
  public static void main(String[] args) throws FileNotFoundException {
    String filename = args[0];
    File inFile = new File(filename);
    Scanner in = new Scanner(inFile);
    int n = in.nextInt();
    int sum = in.nextInt();
    int[] elem = new int[n];
    int[] x = new int[n];
    int total = 0;
    for (int i = 0; i < n; i++) {
      elem[i] = in.nextInt();
      total += elem[i];
    }
    Arrays.sort(elem);
    if (total < sum) {
      System.out.println("No solution possible");
      return;
    }
    x[0] = 1;
    findElements(n, elem, x, 0, elem[0], sum);
    display(n, elem, x);
  }
}


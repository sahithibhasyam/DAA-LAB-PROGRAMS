
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Sum of subset problem using backtracking.
 * First all elements are sorted. This helps reducing our search futher.
 * Essentially if including ith element exceeds sum, then
 * it is true for all remaining one as those have higher or equal value
 * Start from root of the tree and keep exploring
 */

/**
 * input file format
 * <n> <sum># first line contains number of elements, and sum
 * v1, v2, ..., vn # n values
 */


public class unsorted {

  public static void display(int size, int[] elem, int[] x) {
    // display all the elements counted
    for (int i = 0; i < size; i++) {
      if (x[i] != 0) {
        System.out.print(elem[i] + " ");
      }
    }
    System.out.println();
  }

  /**
   * it is assumed that elements are in sorted order.
   * The soring helps in limiting the tree growth to feasible solutions only
   *
   * @param size:    size of element array
   * @param elem:    value of each element
   * @param x:       vector array indicating which elements are present
   * @param index:   index of current element array included in sum
   * @param currsum: current sum including next element
   * @param tgtsum:  target sum to be achieve
   * @return: true or false
   */

  public static void findElements(int size, int[] elem, int[] x,
                                  int index, int currsum, int tgtsum) {
    if (index >= 0 && currsum == tgtsum) {// solution found with current index
      // display the current subset.
      x[index] = 1;//insert code here; // no need to explore       								//further with this element
      display(size, elem, x);
      return;
    }
    // if this is the last element, no more exploration
    if (index == size - 1) {
      return;
    }
    // explore if next element can be included
    if (currsum < tgtsum) {
      x[index + 1] = 1;
      currsum += elem[index+1];
      findElements(size, elem, x, index+1, currsum, tgtsum);
    }
    // explore by excluding next element
    //insert code here
    if (x[index + 1] != 0) {
      x[index + 1] = 0;
      currsum = currsum - elem[index + 1];
      if (currsum < tgtsum) {
        findElements(size, elem, x, index + 1, currsum, tgtsum);
      }
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    String filename = args[0];
    File inFile = new File(filename);
    Scanner in = new Scanner(inFile);
    int n = in.nextInt(); // number of elements
    int sum = in.nextInt(); // sum value
    int[] elem = new int[n]; //holds element values
    int[] x = new int[n]; // 1- indicating if element is counted, 0 otherwise
    int total = 0; // total of all elements
    for (int i = 0; i < n; i++) {
      elem[i] = in.nextInt();
      total += elem[i];
    }
    // check feasibility of solution
    if (total < sum) {
      System.out.println("No solution possible");
      return;
    }
    // start with root i.e. no element included
    findElements(n, elem, x, -1, 0, sum);
  } // end main
} // end class

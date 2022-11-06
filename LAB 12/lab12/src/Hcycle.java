/**
 * For a given graph of n nodes, find all hamiltonian cycles
 * Input data
 * first line: <n>: number of nodes in the graph
 * next n lines provides adjacency matrix of graph
 * <p>
 * Graph basic algorithm depends upon checking (x[k]+1)%(n+1)==0,
 * we avoid having a node number 0. Thus node number starts from 1.
 * Thus when allocating adj matrix, it is of size [n+1][n+1]
 */
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Hcycle {
  public static void nextvalue(int n, int[][] graph, int[] x, int k) {
    int j;
    while (true) {
      x[k] = (x[k] + 1) % (n+1);
      if (x[k] == 0) {
        return;
      }
      if (graph[x[k-1]][x[k]] == 1) {
        for (j = 1; j <= k - 1; j++) {
          if (x[j] == x[k]) {
            break;
          }
        }
        if (j == k) {
          if ((k < n) || ((k == n) && (graph[x[n]][x[1]] == 1))) {
            return;
          }
        }
      }
    }
  }

  public static void HC(int n, int[][] graph, int[] x, int k) {

    while (true) {
      nextvalue(n, graph, x, k);
      if (x[k] == 0) {
        return;
      }
      if ( k == n ) {
        System.out.println("\n");
        for (int i = 1; i <= n; i++) {
          System.out.print(x[i] + "-->");
        }
        System.out.print(x[1]);
      } else {
        HC(n, graph, x, k+1);
      }
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    String filename = args[0];
    File inFile = new File(filename);
    Scanner in = new Scanner(inFile);

    int n;
    n = in.nextInt();
    int[] x = new int[n + 1];
    int[][] graph = new int[n + 1][n + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        graph[i][j] = in.nextInt();
        x[i] = 0;
      }
    }
    x[1] = 1;
    HC(n, graph, x, 2);
    System.out.println();
  }
}

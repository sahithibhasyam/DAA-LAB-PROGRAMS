import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Floyd {
  static void allpairss(int n, int[][] graph) {
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if ( graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE
                  && graph[i][k] + graph[k][j]  < graph[i][j] ) {
            graph[i][j] = graph[i][k] + graph[k][j];
          }
        }
      }
    }
  }
  static void display(int n, int[][] graph) {
    System.out.println("Shortest Distance between all pairs:");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.println("distance[" + i + "][" + j + "] = " + graph[i][j]);
      }
    }
  }
  public static void main(String[] args) throws FileNotFoundException {
    int i, j;
    int V;
    String filename = args[0];
    File inFile = new File(filename);
    Scanner in = new Scanner(inFile);
    V = in.nextInt();
    int graph[][] = new int[V][V];
    for (i = 0; i < V; i++) {
      for (j = 0; j < V; j++) {
        graph[i][j] = in.nextInt();
        if (graph[i][j] == 0) {
          graph[i][j] = Integer.MAX_VALUE;
        }
      }
      graph[i][i] = 0;
    }
    allpairss(V, graph);
    display(V, graph);
  }
}
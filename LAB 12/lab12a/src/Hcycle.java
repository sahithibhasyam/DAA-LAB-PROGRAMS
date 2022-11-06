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
  // x[1],…,x[k-1] is a path of k-1 distinct vertices
  // x[k]=0 implies no vertex is assigned to x[k]
  //  Initially, all x[i]=0
  // x[k] is a vertex not in x[1],…,x[k-1], and connected to x[k-1]
  public static void nextvalue(int n, int[][] graph, int[] x, int k) {
    int j;
    while (true) {
      x[k] = (x[k] + 1) % (n+1);//insert code here
      if (x[k] == 0) {
        return; // implies all n vertices have been explored
      }
      if (graph[x[k-1]][x[k]] == 1) { // edge x[k-1]-->x[k] exists
        for (j = 1; j <= k - 1; j++) {
          if (x[j] == x[k]) // vertex already in the path
            break;
        }
        if (j == k) { //if last vertex, check for edge with x[1]
          if ((k < n) || ((k == n) && (graph[x[n]][x[1]] == 1))) {
            return;
          }
        }
      } // end if G[x[k-1]][x[k]]
    } // end while true
  } // end Next_Vertex

  public static void HC(int n, int[][] graph, int[] x, int k) {
    // uses recursive formulation of backtracking to find all HCs of G
    // Graph is stored as adjacency matrix G[1:n][1:n]
    //All cycles start at node 1. Initially, all x[i]=0
    while (true) {
      // generate values for kth node i.e. x[k]
      nextvalue(n, graph, x, k);//assign a legal value to x[k]
      if (x[k] == 0) {
        return;
      }
      if ( k == n ) { // if last node reached, print path
        System.out.println("\n");
        for (int i = 1; i <= n; i++)
          System.out.print(x[i] + "-->");
        System.out.print(x[1]);
      } else {
        HC(n, graph, x, k+1); // discover next node in the path
      }
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    String filename = args[0];
    File inFile = new File(filename);
    Scanner in = new Scanner(inFile);

    int n; // num of vertices in the graph
    n = in.nextInt();
    int[] x = new int[n + 1];
    int[][] graph = new int[n + 1][n + 1]; // adjacency matrix for graph

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        graph[i][j] = in.nextInt();
        x[i] = 0;
      }
    }
    x[1] = 1;
    HC(n, graph, x, 2); // find Hamilotonian cycles
    System.out.println();
  }// end main
}// end class

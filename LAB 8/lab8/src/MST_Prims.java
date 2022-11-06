import java.util.*;
import java.io.*;
public class MST_Prims {
  static int vertex_mincost_edge(int[] weight, Boolean[] VT, int V) {
    int minwt = Integer.MAX_VALUE;
    int min_index = -1;

    for (int v = 0; v < V; v++)
      if (!VT[v] && weight[v] <= minwt) {
		minwt = weight[v];
        	min_index = v;
      }
    return min_index;
  }
  static void MST_print(int[] parent, int[][] graph, int V, int start) {
    int MSTcost = 0;
    System.out.println("Edge \tCost");
    for (int i = 0; i < V; i++) {
      if (parent[i] != -1) {
        System.out.println(parent[i] + " -- " + i + "\t: " + graph[i][parent[i]]);

        MSTcost += graph[i][parent[i]];
      }
    }
    System.out.println("Cost of MST is:" + MSTcost);
  }
  static Boolean findPrimsMST(int[][] graph, int V, int start, int[] parent) {
    boolean MSTexists = true;
    int[] weight = new int[V];
    Boolean[] VT = new Boolean[V];
    for (int i = 0; i < V; i++) {
      weight[i] = Integer.MAX_VALUE;
      VT[i] = false;
      parent[i] = -1;
    }
    weight[start] = 0;
    for (int count = 0; count < V - 1; count++) {
      int u = vertex_mincost_edge(weight, VT, V);
      if (u == -1) {
        MSTexists = false;
        break;
      } else {
		VT[u] = true;
        for (int v = 0; v < V; v++) {
          if (graph[u][v] != 0 && !VT[v] && graph[u][v] < weight[v]) {
            parent[v] = u;
            weight[v] = graph[u][v];
          }
        }
      }
    }
  return MSTexists;
  }
  public static void main(String[] args) throws FileNotFoundException {
    int i, j;
    int V;
    int startvertex;
    String filename = args[0];
    File inFile = new File(filename);
    Scanner in = new Scanner(inFile);
    V = in.nextInt();
    startvertex = in.nextInt();
    int[][] graph = new int[V][V];
    int[] parent = new int[V];
    for (i = 0; i < graph.length; i++) {
      for (j = 0; j < graph[i].length; j++) {
        graph[i][j] = in.nextInt();
        if (graph[i][j] == 0) {
          graph[i][j] = Integer.MAX_VALUE;
        }

      }
    }
    for (int m = 0; m < graph.length; m++) {
      for (int n = 0; n < graph.length; n++ ) {
        if (graph[m][n] != graph[n][m]) {
          System.out.println("Invalid. Directed Graph. Prim's Algorithm cannot be performed.");
          System.exit(-1);
        }
      }
    }
    if (findPrimsMST(graph, V, startvertex, parent)) {
		MST_print(parent, graph, V, startvertex);
    } else {
      System.out.println("Graph is disconnected and does not have a Spanning Tree");
    }
  }
}

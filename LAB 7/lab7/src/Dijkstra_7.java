import java.util.*;
import java.io.*;

class Dijkstra_7 {
    static int min_Distance(int[] dist, Boolean[] V_included, int V) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++)
            if (!V_included[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }
    static void print_distance(int[] dist, int n) {
        System.out.println("Vertex number   Distance from Source");
        for (int i = 0; i < n; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                System.out.println("      " + i + "     ----->     " + dist[i]);
            } else {
                System.out.println("      " + i + "     ----->     -");
            }
        }
    }
    static void Dijkstra(int[][] cost, int src, int V) {
        int[] dist = new int[V];
        Boolean[] V_included = new Boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            V_included[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = min_Distance(dist, V_included, V);
            V_included[u] = true;
            for (int v = 0; v < V; v++) {
                if (!V_included[v]) {
                    if (cost[u][v] != 0 && dist[u] + cost[u][v] < dist[v]) {
                        dist[v] = dist[u] + cost[u][v];
                    }
                }
            }
            print_distance(dist, V);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        int i, j, n, src;
        Scanner read = new Scanner(System.in);
        String filename = args[0];
        File inFile = new File(filename);
        Scanner in = new Scanner(inFile);
        n = in.nextInt();
        int[][] cost = new int[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                cost[i][j] = in.nextInt();
            }
        }
        System.out.println("Enter the source vertex:");
        src = read.nextInt();
        Dijkstra( cost, src, n);
    }
}


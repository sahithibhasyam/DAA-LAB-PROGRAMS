
/**
 * Find Minimum Cost Spanning Tree of a given connected 
 * undirected graph using Kruskal'salgorithm. Use Union-Find 
 * algorithms in your program.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Edge implements Comparable<Edge> {
  int src;
  int dst;
  int cost;

  public int compareTo(Edge otherEdge) {
    return this.cost - otherEdge.cost;
  }
  
  public Edge(int src, int dst, int cost) {
    this.src = src;
    this.dst = dst;
    this.cost = cost;
  }
  
  public int getSrc() {
    return this.src;
  }
  
  public int getDst() {
    return this.dst;
  }
  
  public int getCost() {
    return this.cost;
  }
  
  public void display() {
    System.out.println(this.getSrc() + ", " + this.getDst() + ", " + this.getCost());
  }
}

public class KruskalMCST {
  int[] parent;
  int[] childrencnt;
  
  public KruskalMCST(int n) {
    parent = new int[n];
    childrencnt = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      childrencnt[i] = 1;
    }
  }
  
  public int getParent(int u) {
    return this.parent[u];
  }
  
  public void display() {
    for (int i = 0; i < parent.length; i++) {
      System.out.println("Parent[" + i + "] =" + parent[i] + ", " +
                         "Childrencnt[" + i + "] =" + childrencnt[i]);
    }
  }
  
  public int find(int u) {
    while (parent[u] != u) {
      parent[u] = find(parent[u]);
    }
    return u;
  }
  
  public void union(int u, int v) {
    if (childrencnt[u] <= childrencnt[v]) {
      find(u);
      parent[u] = v;
      childrencnt[v] += childrencnt[u];
    } else {
      find(v);
      parent[v] = u;
      childrencnt[u] += childrencnt[v];
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int e = in.nextInt();
    long starttime = 0;
    long endtime = 0;
	
    ArrayList<Edge> edges = new ArrayList<Edge>();
    ArrayList<Edge> mcst = new ArrayList<Edge>();
	
    for (int i = 0; i < e; i++) {
      int src = in.nextInt();
      int dst = in.nextInt();
      int cost = in.nextInt();
      Edge edge = new Edge(src, dst, cost);
      edges.add(edge);
    }
	
    Collections.sort(edges);
    KruskalMCST kruskal = new KruskalMCST(n);
    starttime = System.nanoTime();
    int edgecount = 0;
    int curredge = 0;
    int mincost = 0;
	
    while ((edgecount < n - 1) && (curredge < e)) {
      Edge edge = edges.get(curredge);
      int u = edge.getSrc();
      int v = edge.getDst();
      int parentu = kruskal.getParent(u);
      int parentv = kruskal.getParent(v);
      if (parentu != parentv) {
        mincost += edge.getCost();
        mcst.add(edge);
        kruskal.union(parentu, parentv);
        edgecount++;
      }
      curredge++;
    }
    endtime = System.nanoTime();
    for (Edge edge : mcst) {
      edge.display();
    }
    System.out.println("Execution time = " + (endtime - starttime) / 1000 + "us; " +
            "\nThe cost of Kruskal MCST = " + mincost);
  }
}

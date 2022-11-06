import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Input
 * It reads the graph as input and prints minimum cost spanning tree
 * Graph is described as follows
 * first line contains two values indicating number of nodes and number of edges
 * n e
 * It is followed by e lines where each line is of the form
 * src dst cost
 */

/**
 * Algorithm
 * mcst ← Ø; edgecount ← 0; curredge ← 0
 * while edgecount < |V| - 1 do
 * curredge ← curredge+1
 * if mcst U {curreddge} is acyclic
 * mcst ← mcst U curredge
 * edgecount ← edgecount+1
 * // end while
 * return mcst
 */
// define edge
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

    // define getter and setter methods
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
        System.out.println(this.getSrc() + ", " +
                this.getDst() + ", " +
                this.getCost());
    }
}

public class KruskalMCST {
    int[] parent;
    int[] childrencnt;

    public KruskalMCST(int n) {
        parent = new int[n];
        childrencnt = new int[n];
        // initialize each node to be its parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
                    // initialize that parent has no children except itself
            childrencnt[i] = 1;
        }
    }

    public int getParent(int u) {
        return this.parent[u];
    }

    public void display() {
        for (int i = 0; i < parent.length; i++) {
            System.out.println("parent[" + i + "] =" + parent[i] + ", " +
                    "Childrencnt[" + i + "] =" + childrencnt[i]);
        }
    }

    // TBD: enhance it to implement path compression
    public int find(int u) {
        // traverse the tree from the node till the root of the tree
        while (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return u;
    }

    // this union does not do path compression.
    // the tree with smaller nodes as child of other tree
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

        ArrayList<Edge> edges = new ArrayList<Edge>(); // original edges of graph
        ArrayList<Edge> mcst = new ArrayList<Edge>(); // edges in Kruskal MCST

        // read all the input edges and build the graph
        for (int i = 0; i < e; i++) {
            int src = in.nextInt();
            int dst = in.nextInt();
            int cost = in.nextInt();
            Edge edge = new Edge(src, dst, cost);
            edges.add(edge);
        } // end for read all edges

        // sort the edges as per their cost in ascending order.
        Collections.sort(edges);

        // create the Kruskal MCST
        KruskalMCST kruskal = new KruskalMCST(n);
        starttime = System.nanoTime();

        //Build kruskal tree till n-1 edges are included
        int edgecount = 0;
        int curredge = 0; // current edge being considered
        int mincost = 0; // mincost of MST
        //while ((edgecount < n - 1) && (curredge < e)) { //optimization
        // consider each edge if it can be included in MCST
        while ((edgecount < n - 1) && (curredge < e)) {
            Edge edge = edges.get(curredge);
            // get the src and destination of edge
            int u = edge.getSrc();
            int v = edge.getDst();
            // get the parent to see if they belong to same tree
            int parentu = kruskal.getParent(u);
            int parentv = kruskal.getParent(v);
            // if both parrents are same, adding this edge will create cycle.
            if (parentu != parentv) {
                // the edge to be included in MCST
                mincost += edge.getCost();
                mcst.add(edge);
                // make the tree with lesser children the child of other tree
                kruskal.union(parentu, parentv);
                edgecount++;
            } // end if
            curredge++;
        } // end while
        endtime = System.nanoTime();

        // display the MCST and its cost
        for (Edge edge : mcst) {
            edge.display();
        }
        System.out.println("Execution time = " + (endtime - starttime) / 1000 + "us; " +
                "\nThe cost of Kruskal MCST = " + mincost);
    } // end main
} // end class KruskalMCST

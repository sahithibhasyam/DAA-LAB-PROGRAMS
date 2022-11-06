import java.util.*;
import java.io.*;

//Generate weighted edge graph
class PrimsInput {

    public static void main (String[] args) {
        int n; // number of nodes
        int avgconn; // Average number of edges per node
        if (args.length != 2) {
            System.out.println("Usage: java program <num of nodes> <avg number of connected nodes");
            System.exit(-1);
        }

        n = Integer.parseInt(args[0]);
        avgconn = Integer.parseInt(args[1]);
        Random random = new Random();

        System.out.println(n + " " + random.nextInt(n));
        for (int i=0; i<n; i++) {
            int edgecnt = random.nextInt(2*avgconn);
            int[] edge = new int[n];
            for (int j=0; j< edgecnt; j++) {
                int node = random.nextInt(n);
                int cost = random.nextInt(n+100);
                edge[node] = cost;
            } // end for j
            System.out.print(edge[0]);
            for (int j=1; j<n; j++){
                System.out.print(" " + edge[j]);
            } // end for j
            System.out.println();
        } // end for i
    } // end main
} //end class

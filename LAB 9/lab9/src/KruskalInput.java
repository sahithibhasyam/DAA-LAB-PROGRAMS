import java.util.*;
import java.io.*;

//Generate weighted edge graph
class KruskalInput {

    // $1 - number of nodes
    // $2 - number of edges
    public static void main (String[] args) throws FileNotFoundException {
        int n; // number of nodes
        int e; // number of edges
        int avgconn; // Average number of edges per node
        if (args.length != 2) {
            System.out.println("Usage: java program <num of nodes> <avg number of connected nodes");
            System.exit(-1);
        }

        n = Integer.parseInt(args[0]);
        e = Integer.parseInt(args[1]);

        System.out.println(n + " " + e);
        int cnt = 0;
        int[][] adjmatrix = new int[n][n];
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++)
                adjmatrix[i][j] = 0;

        while (cnt < e) {
            Random random = new Random();
            int src = random.nextInt(n);
            int dst = random.nextInt(n);
            if (src == dst) {
                continue;
            }
            int cost = random.nextInt(n)+1;
            if (adjmatrix[src][dst] != 0){
                continue;
            }
            adjmatrix[src][dst] = cost;
            adjmatrix[dst][src] = cost;
            cnt++;

            System.out.println(src + " " + dst + " " + cost);
        } // end while
    } // end main
} //end class

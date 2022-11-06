import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class TSPDP {
    public static int[][] dpmem;
    public static int power2(int p) {
        int pow = 1;
        for (int i = 0; i < p; i++) {
            pow *= 2;
        }
        return pow;
    }
    public static void initDpmem(int V, int setsize, int[][] graph) {
        dpmem = new int[V][setsize];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < setsize; j++) {
                dpmem[i][j] = Integer.MAX_VALUE;
            }
            dpmem[i][0] = graph[i][0];
        }
    }
    public static int getDpmem(int ii, int jj) {
        return dpmem[ii][jj];
    }
    public static void setDpmem(int ii, int jj, int val) {
        dpmem[ii][jj] = val;
    }
    public static int mincosttsp(int ii, int V, int[][] graph, int[] vnodes) {
        int col = 0;
        for (int j = 1; j < vnodes.length; j++) {
            col += vnodes[j] * power2(j - 1);
        }
        int dpval = getDpmem(ii, col);
        if (dpval != Integer.MAX_VALUE) {
            return dpval;
        }
        int[] nodeset = new int[V];
        for (int k = 0; k < V; k++) {
            nodeset[k] = vnodes[k];
        }
        int mincost = Integer.MAX_VALUE;
        for (int j = 1; j < V; j++) {
            if (nodeset[j] == 0) {
                continue;
            }
            int temp = nodeset[j];
            nodeset[j] = 0;
            int val = graph[ii][j] + mincosttsp(j, V, graph, nodeset);
            if (val < mincost) {
                mincost =  val;
            }
            nodeset[j] = temp;
            setDpmem(ii, j, mincost);
        }
        return mincost;
    }
    public static void main(String[] args) throws FileNotFoundException {
        int V;
        String filename = args[0];
        File inFile = new File(filename);
        Scanner in = new Scanner(inFile);
        V = in.nextInt();
        int[][] graph = new int[V][V];
        int[] explore = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = in.nextInt();
                if (graph[i][j] == 0) {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
            graph[i][i] = 0;
        }
        initDpmem(V, power2(V - 1), graph);
        for (int i = 1; i < V; i++) {
            explore[i] = 1;
        }
        int cost = mincosttsp(0, V, graph, explore);
        System.out.println("Min cost for TSP = " + cost);
    }
}
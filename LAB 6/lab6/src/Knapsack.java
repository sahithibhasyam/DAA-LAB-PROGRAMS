import java.io.*;
import java.util.*;

public class Knapsack {
    static int i,j;
    static int GreedyKnapSack(int n, int[] w, int[] p, int W) {
        int c=W;
        int max_profit=0;
        sort(n, w, p);
        for(i=0;i<n;i++) {
            if(w[i]<=c) {
                System.out.println("\tAdd Item  with Weight = " + w[i] + " and Value = " + p[i]);
                c = c - w[i];
                max_profit = max_profit + p[i];
            }
        }
        return max_profit;
    }
    static void sort(int n,int[] w,int[] p) {
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                    if (p[j] / w[j] < p[j+1] / w[j+1]) {
                        swap(w, j, j+1);
                        swap(p, j, j+1);

                    }
            }
        }
    }
    static void swap (int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static int DynamicKnapSack(int n, int[] wt, int[] val, int W)
    {
        int[][] T =new int[n+1][W+1];
        for (i=0; i<=n; i++) {
            for (j=0; j<=W; j++) {
                if (i==0 || j==0) {
                    T[i][j] = 0;
                } else if (wt[i-1] <= j) {
                    T[i][j] = Math.max(val[i-1] + T[i-1][j-wt[i-1]], T[i-1][j]);
                } else {
                    T[i][j] = T[i-1][j];
                }

            }
        }
        System.out.println("Dynamic Knapsack Solution Table : \n");
        for(i=0;i<=n;i++) {
            for (j = 0; j <= W; j++) {
                System.out.print(T[i][j] + "\t");
            }
            System.out.println("\n");
        }
        i=n;j=W;
        while(i!=0) {
            if(T[i][j]!=T[i-1][j]) {
                System.out.println("\tAdd Item with Weight = " + wt[i - 1] + " and Value = " + val[i - 1]);
                j = j - wt[i-1];
            }
            i--;
        }
        return T[n][W];
    }
    public static void main(String[] args) throws FileNotFoundException {
        int n,W;
        String filename = args[0];
        System.out.println(filename);
        File infile = new File(filename);
        Scanner in1 = new Scanner(infile);
        n=Integer.parseInt(in1.nextLine());
        W=Integer.parseInt(in1.nextLine());
        int[] val =new int[n];
        int[] wt =new int[n];
        long starttime, endtime;
        for (int i = 0; i < n; i++) {
            wt[i] = Integer.parseInt(in1.nextLine());
        }
        for (int i = 0; i < n; i++) {
            val[i] = Integer.parseInt(in1.nextLine());
        }
        System.out.println("DYNAMIC KNAPSACK");
        starttime = System.nanoTime();
        System.out.println("Optimal Solution for Dynamic 0/1 Knapsack: "+ DynamicKnapSack(n, wt, val, W) );
        endtime = System.nanoTime();
        System.out.println("Time taken for Dynamic 0/1 Knapsack :" + (endtime - starttime) + " nsecs");
        System.out.println("--------------------------------------------------\n");

        System.out.println("GREEDY KNAPSACK");
        starttime = System.nanoTime();
        System.out.println("Optimal solution for Greedy 0/1 Knapsack : " + GreedyKnapSack(n, wt, val, W) );
        endtime = System.nanoTime();
        System.out.println("Time taken for Greedy 0/1 Knapsack :" + (endtime - starttime) + " nsecs");
        System.out.println("--------------------------------------------------\n");
    }
}
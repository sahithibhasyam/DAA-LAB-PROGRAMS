import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    public static class Pair {
        int i;
        int j;
        String psf;

        public Pair (int i, int j, String psf) {
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }

    public static void main (String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the no. of Elements");
        int n = 5;
        int[] arr = {4, 2, 7, 1, 6};
        System.out.println("Enter the elements to be added to the array");
//        for (int i = 0; i < n; i++) {
//            arr[i] = in.nextInt();
//        }
        System.out.println("Enter the target sum");
        int tar = 10;

        boolean[][] dp = new boolean[arr.length][tar + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <dp[0].length; j++) {
                if (j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else {
                    if (dp[i-1][j]) {
                        dp[i][j] = true;
                    } else if (j >= arr[i-1]) {
                        if (dp[i - 1][j - arr[i - 1]]) {
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }
        System.out.println(dp[arr.length][tar-1]);
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair (n, tar, ""));

        while (queue.size() > 0) {
            Pair rem = queue.removeFirst();
            if (rem.i == 0 || rem.j == 0) {
                System.out.println(rem.psf);
            } else {
                if (rem.j >= arr[rem.i - 1]) {
                    boolean inc = dp[rem.i - 1][rem.j - arr[rem.i - 1]];
                    if (inc) {
                        queue.add(new Pair(rem.i - 1, rem.j - arr[rem.i - 1], (rem.i - 1) + " " + rem.psf));
                    }
                }
                boolean exc = dp[rem.i - 1][rem.j];
                if (exc) {
                    queue.add(new Pair(rem.i - 1, rem.j, rem.psf));
                }
            }
        }
    }
}

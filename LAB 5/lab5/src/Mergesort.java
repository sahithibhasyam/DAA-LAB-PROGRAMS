import java.util.Random;

class Mergesort {
    static void merge(int[] a) {
        int n = a.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        System.arraycopy(a, 0, left, 0, mid);
        if (n - mid >= 0) {
            System.arraycopy(a, mid, right, 0, n - mid);
        }
        merge(left);
        merge(right);
        sort(a, left, right);
    }
    static void sort(int[] a, int[] left, int[] right) {
        int l = left.length;
        int r = right.length;
        int i = 0, j = 0, k = 0;
        while (i < l && j < r) {
            if (left[i] <= right[j]) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < l) {
            a[k] = left[i];
            i++;
            k++;
        }
        while (j < r) {
            a[k] = right[j];
            j++;
            k++;
        }
    }
    static void display(int[] arr) {
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        Random rand = new Random();
        int n;
        int cnt_sort;
        long starttime;
        long endtime;
        if (args.length != 2) {
            System.out.println("Usage: <prog> <num of elements> <num of sort cases");
            System.exit(-1);
        }
        n = Integer.parseInt(args[0]);
        cnt_sort = Integer.parseInt(args[1]);
        for (int j = 0; j < cnt_sort; j++){
            int[] a = new int [n];
            for (int i = 0; i < n; i++) {
                a[i] = rand.nextInt(n)+1;
            }
            System.out.println("Unsorted Array");
            display(a);
            starttime = System.nanoTime();
            merge(a);
            endtime = System.nanoTime();
            System.out.println("Time taken for "+ n +" elements :" + (endtime - starttime) + " nsecs");
            System.out.println("Sorted array");
            display(a);
            n *=2;
        }
    }
}
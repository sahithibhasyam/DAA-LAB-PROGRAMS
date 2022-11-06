import java.util.*;

class Quicksort{
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static int partition(int[] arr, int low, int high, int pivot) {
        int left = low;
        int right = high - 1;
        while (left < right) {
            while (arr[left] <= pivot && left < right) {
                left++;
            }
            while (arr[right] >= pivot && left < right) {
                right--;
            }
            swap(arr, left, right);
        }
        if (arr[left] > arr[high]) {
            swap (arr, left, high);
        } else {
            left = high;
        }
        return left;
    }
    static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = arr[high];
        int left = partition(arr, low, high, pivot);
        quickSort(arr, low, left-1);
        quickSort(arr, left+1, high);
    }
    static void quickSortMiddle(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low+high)/2;
        int pivot = arr[mid];
        int pivotIndex = partition(arr, low, high, pivot);
        quickSortMiddle(arr, low, pivotIndex-1);
        quickSortMiddle(arr, pivotIndex+1, high);
    }
    static void display(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
    public static void main(String[] args) {
        if(args.length < 2) {
            throw new IllegalArgumentException("Three args required");
        }
        try {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
            Integer.parseInt(args[2]);
        } catch(NumberFormatException e) {
            System.out.println("Invalid Input.Try Again.");
            System.exit(-1);
        }
        int n = Integer.parseInt(args[0]);
        int cnt_sort = Integer.parseInt(args[1]);
        int cnt_rpt = Integer.parseInt(args[2]);
        Random rand = new Random();
        long starttime;
        long endtime;
        double avg_t;
        int[] a = new int[n];
        quickSort(a);
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            a[i] = rand.nextInt(10);
        }
        System.out.println("Before:");
        display(a);
        quickSort(a);
        System.out.println();
        System.out.println("After:");
        display(a);
        System.out.println();
        //AVERAGE CASE ANALYSIS
        System.out.println("AVERAGE CASE ANALYSIS");
        for (int k = 0; k < cnt_sort; k++){ 
            a = new int[n];
            long sum_t=0;
            for(int j=0;j<cnt_rpt;j++) {
                for (int i = 0; i < n; i++){
                    a[i] = rand.nextInt(10);
                }
                starttime = System.nanoTime();
                quickSort(a);
                endtime = System.nanoTime();

                sum_t += (endtime - starttime);

            }
            avg_t = sum_t/cnt_rpt;
            System.out.println("Time taken for "+n+" elements :" + avg_t + " nsecs");
            n = n * 2;
        }

        n = (int) (n/(Math.pow(2, cnt_sort)));
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = rand.nextInt(10);
        }
        quickSort(a);
        // WORST CASE ANALYSIS
        System.out.println("WORST CASE ANALYSIS");
        try {
            starttime = System.nanoTime();
            quickSort(a);
            endtime = System.nanoTime();

            System.out.println("Time taken for "+ n +" elements :" + (endtime - starttime) + " nsecs");
        } catch(Exception e) {
            System.out.println(e);
        }
        // BEST CASE ANALYSIS
        System.out.println("BEST CASE ANALYSIS");
        try {
            quickSortMiddle(a, 0 , n-1);
            starttime = System.nanoTime();
            quickSortMiddle(a, 0 , n-1);
            endtime = System.nanoTime();

            System.out.println("Time taken for "+ n +" elements :" + (endtime - starttime) + " nsecs");
            display(a);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}



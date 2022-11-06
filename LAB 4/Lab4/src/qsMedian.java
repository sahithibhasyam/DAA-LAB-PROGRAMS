import java.util.*;

public class qsMedian {
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
            swap(arr, left, high);
        } else {
            left = high;
        }
        return left;
    }

    //QUICKSORT
    static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static int getMedian(int[] a, int left,int right){
        int center = (left+right)/2;

        if(a[left] > a[center]) {
            swap(a, left, center);
        }
        if(a[left] > a[right]) {
            swap(a, left, right);
        }
        if(a[center] > a[right]) {
            swap(a, center, right);
        }
        swap(a, center, right);
        return a[right];
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        /*int pivotIndex = new Random().nextInt(high - low) + low;
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, high);*/
        int pivot=getMedian(arr,low,high);
        int left = partition(arr, low, high, pivot);
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }

    // Print array elements
    static void display(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("One arg required");
        }
        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input.Try Again.");
            System.exit(-1);
        }
        int n = Integer.parseInt(args[0]);

        Random rand = new Random();

        int[] a = new int[n];


        long starttime;
        long endtime;
        quickSort(a);

        System.out.println(n);
        for (int i = 0; i < n; i++) {
            a[i] = rand.nextInt(10);
        }
        System.out.println("Before:");
        //display(a);
        starttime = System.nanoTime();
        quickSort(a);
        endtime = System.nanoTime();
        System.out.println();
        System.out.println("After:");
        //display(a);
        System.out.println();
        System.out.println("Time taken for "+ n +" elements :" + (endtime - starttime) + " nsecs");

    }
}
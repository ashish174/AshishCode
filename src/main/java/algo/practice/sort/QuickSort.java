package algo.practice.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * QuickSort is a divide and conquer algorithm
 * It picks an element as pivot and partitions the given array around the picked pivot.
 * For picking pivot, you can use any strategy : first element/last element/random element
 * Logic is
 * 1. pick a pivot element
 * 2. Put pivot element at correct position
 * i.e. all smaller elements before pivot element and all greater element after pivot element. Thus creating a partition.
 * 3. Repeat the same steps from Step 1 for left partition and right partition
 */
public class QuickSort {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuickSort.class);

    public static void main(String[] args) {
        int[] arr = new int[]{7, 5, 15, 6, 16, 2, 14};
        quickSort(arr, 0, arr.length - 1);
        LOGGER.info("Sorted Array by Quick Sort is {}", arr);
    }

    public static void quickSort(int[] arr, int low, int high) {
        int pi;
        if (low < high) {
            pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // Lets pick last element as pivot
        int pivot = arr[high];
        int temp;
        int partitionIndex = low - 1;
        for (int k = low; k < high; k++) {
            if (arr[k] < pivot) {
                partitionIndex++;
                //swap if index are not same, so as avoid redundant swaps
                swap(arr, partitionIndex, k);
            }
        }
        //swap pivot to correct position
        swap(arr, partitionIndex + 1, high);
        // return correct pivot index
        LOGGER.info("Placeed pivot {} : position {}", arr[partitionIndex + 1], partitionIndex + 1);
        return partitionIndex + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


}

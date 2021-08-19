package algo.practice.tree.binarytree.Heap;

import java.util.Arrays;

/**
 * Heap sort algorithm has limited uses because Quicksort and Mergesort are better in practice.
 */
public class HeapSort {
    public static int[] doHeapSort(MinHeapUsingArray minHeapUsingArray){
        int arrLength = minHeapUsingArray.heapSize;
        int[] sortedArr = new int[arrLength];

        for(int i =0; i < arrLength; i++){
            sortedArr[i] = minHeapUsingArray.extractMin();
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,9,4,10,6,2,7};
        MinHeapUsingArray minHeapUsingArray = CreateMinHeap.createMinHeap(arr);
        System.out.println("Original Array : " + Arrays.toString(arr));
        System.out.println("Min Heap Array : ");
        minHeapUsingArray.printHeapArr();
        System.out.println("Heap Sorted Array : "+ Arrays.toString(doHeapSort(minHeapUsingArray)));
    }
}

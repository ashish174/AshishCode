package algo.practice.tree.binarytree.Heap;

public class CreateMinHeap {
    public static MinHeapUsingArray createMinHeap(int[] arr){
        MinHeapUsingArray minHeapUsingArray = new MinHeapUsingArray(arr.length+10);
        minHeapUsingArray.heapArr = arr.clone();
        minHeapUsingArray.heapSize = arr.length;
        int i = (minHeapUsingArray.heapSize-1)/2;
        while(i >= 0){
            minHeapUsingArray.minHeapify(i);
            i--;
        }
        return minHeapUsingArray;
    }

    public static void main(String[] args) {
        int[] arr = {6,3,10,9,8,2};
        MinHeapUsingArray minHeap = createMinHeap(arr);
        minHeap.printHeapArr();
    }

}

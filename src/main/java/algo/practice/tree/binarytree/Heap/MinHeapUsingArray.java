package algo.practice.tree.binarytree.Heap;

public class MinHeapUsingArray {
    /**
     * parent = (i-1)/2
     * lChild = 2i+1
     * lChild = 2i+2
     */
    int maxSize;
    int heapSize;
    int[] heapArr;

    public MinHeapUsingArray(int maxSize) {
        this.maxSize = maxSize;
        this.heapSize = 0;
        heapArr = new int[maxSize];
    }

    int getMin() {
        if (heapSize <= 0) {
            return -1;
        }
        return heapArr[0];
    }


    int extractMin() {
        if (heapSize <= 0) {
            return -1;
        }
        int min = heapArr[0];
        heapArr[0] = heapArr[heapSize - 1];
        heapSize--;
        minHeapify(0);
        return min;
    }


    void insertKey(int key) {
        heapArr[++heapSize] = key;
        //Traverse up to verify if parents are not more than child
        decreaseKey(heapSize, key);
    }

    /**
     * Approach 1 :- replace key with Integer.MIN_VALUE & then call decrease key. Then it will reach top and do extract min.
     */
    void deleteKey(int index) {
        decreaseKey(index, Integer.MIN_VALUE);
        extractMin();
    }

    void decreaseKey(int index, int newVal) {
        heapArr[index] = newVal;
        int parent = (index - 1) / 2;
        while (parent >= 0 && heapArr[parent] > newVal) {
            int tmp = heapArr[parent];
            heapArr[parent] = newVal;
            heapArr[index] = tmp;
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    void minHeapify(int index) {
        if (index < 0 || index >= heapSize) {
            return;
        }
        int lChild = 2 * index + 1;
        int rChild = 2 * index + 2;
        int minIndex = index;
        if (lChild < heapSize && heapArr[lChild] < heapArr[minIndex]) {
            minIndex = lChild;
        }
        if (rChild < heapSize && heapArr[rChild] < heapArr[minIndex]) {
            minIndex = rChild;
        }
        //swap values
        int tmp = heapArr[index];
        heapArr[index] = heapArr[minIndex];
        heapArr[minIndex] = tmp;

        minHeapify(minIndex);
    }
}

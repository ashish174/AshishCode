package algo.practice.arrays;


public class BinarySearchInARotatedArrayCopy {

    public static void main(String[] args) {
        int[] arr = {6, 7, 1, 2, 4, 5};
        System.out.println("Index :" + binarySearchInARotatedTree(arr, 2));
    }

    public static int binarySearchInARotatedTree(int[] arr, int key) {
        int size = arr.length;
        if (key == arr[0])
            return 0;
        //int pivot = findPivot(arr);
        int pivot = findPivotEfficiently(arr, 0, arr.length - 1);
        if (pivot == -1)
            return -1;
        else if (key < arr[0])
            return binarySearch(arr, pivot + 1, size - 1, key);
        else
            return binarySearch(arr, 1, pivot, key);
    }

    public static int binarySearch(int[] arr, int i, int j, int key) {
        if (i <= j) {
            int mid = i + (j - i) / 2;
            if (key == arr[mid])
                return mid;
            else if (key < arr[mid])
                return binarySearch(arr, i, mid - 1, key);
            else {
                return binarySearch(arr, mid + 1, j, key);
            }
        }
        return -1;
    }

    /* Function to get pivot. For array
       3, 4, 5, 6, 1, 2 it returns
       3 (index of 6)
   */
    public static int findPivot(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    /* Function to get pivot. For array
       3, 4, 5, 6, 1, 2 it returns
       3 (index of 6)
   */
    public static int findPivotEfficiently(int[] arr, int low, int high) {
        int mid = (low + high) / 2;
        // Check right element of mid
        if (mid < high && arr[mid] > arr[mid + 1]) {
            return mid;
        }
        // check left element of mid
        if (mid > low && arr[mid] < arr[mid - 1]) {
            return mid - 1;
        }
        // Call recursively left part or right part
        if (arr[low] >= arr[mid]) {
            return findPivotEfficiently(arr, low, mid - 1);
        } else {
            return findPivotEfficiently(arr, mid + 1, high);
        }
    }

}

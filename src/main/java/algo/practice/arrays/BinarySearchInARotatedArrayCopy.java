package algo.practice.arrays;


import lombok.extern.slf4j.Slf4j;

/**
 * This class provides methods for performing binary search on a rotated sorted array.
 *
 * A rotated sorted array is an array that was initially sorted but has been shifted by some number of positions.
 * For example, the array [3, 4, 5, 6, 1, 2] is a rotation of the sorted array [1, 2, 3, 4, 5, 6].
 */
@Slf4j
public class BinarySearchInARotatedArrayCopy {

    public static void main(String[] args) {
        int[] arr = {6, 7, 1, 2, 4, 5};
        log.info("Index :" + binarySearchInARotatedTree(arr, 2));
    }

    /**############################################ V1 ############################################*/

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

  /**
   *  Formula for finding mid
   *  mid = low + (high - low) / 2
   *      = (low + high) / 2
   *
   */


    /**
     * Recursively performs a binary search on a portion of the array.
     *  Formula for finding mid
     *      mid = low + (high - low) / 2
     *          = (low + high) / 2
     *
     * @param arr the array to search
     * @param low the starting index of the search range
     * @param high the ending index of the search range
     * @param key the value to search for
     * @return the index of the key if found, or -1 otherwise
     */
  public static int binarySearch(int[] arr, int low, int high, int key) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            if (key == arr[mid])
                return mid;
            else if (key < arr[mid])
                return binarySearch(arr, low, mid - 1, key);
            else {
                return binarySearch(arr, mid + 1, high, key);
            }
        }
        return -1;
    }

    /**
     * Finds the pivot element index in a rotated sorted array.
     *
     * The pivot element is the first element that is greater than its next element.
     * For array {3, 4, 5, 6, 1, 2} it returns 3 (index of 6)
     *
     * @param arr the rotated sorted array
     * @return the index of the pivot element, or -1 if no pivot is found
     */
    public static int findPivot(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Efficiently finds the pivot element in a rotated sorted array using a recursive approach.
     * For array {3, 4, 5, 6, 1, 2} it returns 3 (index of 6)
     *
     * @param arr the rotated sorted array
     * @param low the starting index of the search range
     * @param high the ending index of the search range
     * @return the index of the pivot element, or -1 if no pivot is found
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

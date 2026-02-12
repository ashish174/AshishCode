package algo.practice.binarySearch;

public class BinarySearchInARotatedArray {
  /**
   * Searches for a target value in a sorted, rotated array using binary search.
   * The array was originally sorted and then rotated at some pivot.
   *
   * Input: nums = [3,4,5,6,1,2], target = 1
   * Output: 4
   *
   * Input: nums = [3,5,6,0,1,2], target = 4
   * Output: -1
   *
   */
  public int search(int[] nums, int target) {
        // Step 1: Find the index of the smallest element (pivot point where rotation happened)
        int k = findpivot(nums);

        // Step 2: Decide which sorted subarray to search
        // If target is between pivot and the last element, search in [k, end]
        if (target >= nums[k] && target <= nums[nums.length-1]) {
            return binarySearch(nums, target, k, nums.length-1);
        } else {
            // Otherwise, search in [0, k-1]
            return binarySearch(nums, target, 0, k-1);
        }
    }

    /**
     * Standard binary search algorithm.
     * Searches for target between indices l and r (inclusive).
     * Returns index if found, else -1.
     */
    int binarySearch(int[] nums, int target, int l, int r) {
        while (l <= r) {
            int mid = l + (r - l) / 2; // avoid overflow
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                return mid; // Found target
            }
        }
        return -1; // Target not found
    }

  /**
   * Finds the pivot (smallest element) in the rotated sorted array.
   * Returns the index where the smallest value is located.
   * If array is not rotated, returns 0.
   * Approach:
   * - At least two of l, mid, and r will always be in the same sorted segment.
   * - If nums[mid] < nums[r], then right half is sorted. So pivot is in the left half including mid.
   */
  int findpivot(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        // If the array is not rotated (already sorted), pivot is at index 0
        if (nums[l] < nums[r]) {
            return 0;
        }

        // Binary search for the inflection point
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[r]) {
                r = mid; // Pivot is at mid or to the left of mid
            } else {
                l = mid + 1; // Pivot is to the right of mid
            }
        }
        // At the end of loop, l == r and both point to the pivot index (smallest element)
        return l;
    }

}

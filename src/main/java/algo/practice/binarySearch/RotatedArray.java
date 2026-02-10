package algo.practice.binarySearch;

/**
 * You are given an array of length n which was originally sorted in ascending order. It has now been rotated between 1 and n times. For example, the array nums = [1,2,3,4,5,6] might become:
 *
 * [3,4,5,6,1,2] if it was rotated 4 times.
 * [1,2,3,4,5,6] if it was rotated 6 times.
 * Notice that rotating the array 4 times moves the last four elements of the array to the beginning. Rotating the array 6 times produces the original array.
 *
 * Assuming all elements in the rotated sorted array nums are unique, return the minimum element of this array.
 *
 * A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,6,1,2]
 *
 * Output: 1
 * Approach:
 *
 *  * 1. At least two of l, mid, and r will always be in the same sorted segment.
 *  * 2. condition can be to check if (nums[mid] < nums[r]). If yes, then minimum element will be in the left part,
 *  * else right part.
 *
 *      * - The array was originally sorted but might have been rotated at some pivot.
 *      * - Use binary search to efficiently locate the point of rotation, which corresponds to the minimum element.
 *      * - The subarray including the minimum element will always be unsorted (discontinuity).
 *      * - The minimum is the only element smaller than its previous element, or the leftmost if array is not rotated.
 *
 *
 *
 */
public class RotatedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        // If the array is sorted i.e. not rotated (the smallest is at the start), return it directly
        if(nums[left] < nums[right]){
            //sorted array
            return nums[left];
        }
        // Binary search: shrink bounds until left == right, which will be the minimum
        while(left < right){
            int mid = left + (right-left)/2;
            //if right sub-array is sorted
            // then the minimum must be at mid or to the left of mid.
            if(nums[mid] < nums[right]){
                right = mid; // include mid in search space, as it is smaller number
            } else {
                // Else, the minimum must be to the right of mid
                left = mid+1; //exclude mid, since nums[mid] > nums[right], means mid is larger number
            }
        }
        // After the loop, left == right and both point to the minimum element
        return nums[left];

    }
}

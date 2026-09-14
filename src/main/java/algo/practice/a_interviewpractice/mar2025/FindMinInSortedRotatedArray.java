package algo.practice.a_interviewpractice.mar2025;

/**
 * Example 1:
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 */
public class FindMinInSortedRotatedArray {
    public int findMin(int[] nums) {
        if(nums==null || nums.length==0){
            return -1;
        }
        int l = 0;
        int r = nums.length-1;
        //full sorted
        if(nums[l]<nums[r]){
            return nums[l];
        }
        return findMinInSortedArray(nums, l,r);

    }
    int findMinInSortedArray(int[] nums, int l, int r){
        if(l==r){
            return nums[l];
        }
        int mid = (l+r)/2;
        // If mid element is greater than right, min is in right half
        if(nums[mid] > nums[r]){
            return findMinInSortedArray(nums, mid+1, r);
        } else { // min is in left half (including mid)
            return findMinInSortedArray(nums, l, mid);
        }
    }
}

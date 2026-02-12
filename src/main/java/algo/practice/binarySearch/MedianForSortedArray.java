package algo.practice.binarySearch;


public class MedianForSortedArray {

    /**
     * Finds the median of two sorted arrays.
     * This approach use 2 pointers
     * This approach simulates merging the two arrays up to the median positions,
     * without actually creating the merged array.
     * Time Complexity: O(m + n) in the worst case, but stops once the median is found.
     */
    public double findMedianSortedArraysWithoutExtraSpace(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int total = m + n;

        // For zero-based indexing, mid1 is the left median (for even-length),
        // mid2 is the right median
        int mid1 = (total - 1) / 2;
        int mid2 = total / 2;

        // Pointers for traversing nums1 and nums2
        int l1 = 0, l2 = 0;
        // Count how many elements have been traversed in the merged order
        int count = 0;
        // Track current and previous merged values (for even median)
        int prev = 0, curr = 0;

        // Continue merging until we've reached the right median position
        while (count <= mid2) {
            prev = curr; // Save previous median candidate

            //This is highlights as we condensed logic in one boolean expression.
            // Pick the next smallest value from either nums1 or nums2
            // Be careful to not go out of bounds
            // - First, check if nums1 still has elements left to be used: (l1 < m)
            // - Next, we have two cases to allow picking from nums1:
            //    1. nums2 is exhausted (no elements left): (l2 >= n)
            //    2. The current value in nums1 is less than or equal to the current value in nums2: (nums1[l1] <= nums2[l2])
            // - So: if either condition is true, pick from nums1; otherwise, pick from nums2.
            if (l1 < m && (l2 >= n || nums1[l1] <= nums2[l2])) {
                curr = nums1[l1++];
            } else {
                curr = nums2[l2++];
            }
            count++;
        }

        // If total element count is even, median is average of mid1 and mid2 positions
        if (total % 2 == 0) {
            return (prev + curr) / 2.0;
        } else { // Odd: median is the mid2 position
            return curr;
        }
    }
}

/**
 *
 * public double findMedianSortedArrays(int[] nums1, int[] nums2) {
 *         int len1 = nums1.length, len2 = nums2.length;
 *         int i = 0, j = 0;
 *         int median1 = 0, median2 = 0;
 *
 *         for (int count = 0; count < (len1 + len2) / 2 + 1; count++) {
 *             median2 = median1;
 *             if (i < len1 && j < len2) {
 *                 if (nums1[i] > nums2[j]) {
 *                     median1 = nums2[j];
 *                     j++;
 *                 } else {
 *                     median1 = nums1[i];
 *                     i++;
 *                 }
 *             } else if (i < len1) {
 *                 median1 = nums1[i];
 *                 i++;
 *             } else {
 *                 median1 = nums2[j];
 *                 j++;
 *             }
 *         }
 *
 *         if ((len1 + len2) % 2 == 1) {
 *             return (double) median1;
 *         } else {
 *             return (median1 + median2) / 2.0;
 *         }
 *     }
 *
 */

/**
 *
 *
 * while(l1 < m && l2 < n){
 *             if(nums1[l1] <= nums2[l2]){
 *                 small = nums1[l1];
 *                 l1++;
 *             } else {
 *                 small = nums2[l2];
 *                 l2++;
 *             }
 *             count++;
 *             if(count==mid){
 *                 isFound = true;
 *                 assign(prevNext, small);
 *                 break;
 *             }
 *         }
 *         while(!isFound){
 *             //Process remianing frst array
 *             while(l1 < m){
 *                 l1++;
 *                 count++;
 *                 if(count==mid){
 *                     isFound = true;
 *                     assign(prevNext, nums1[l1]);
 *                     break;
 *                 }
 *             }
 *             //Process remianing snd array
 *             while(l2<n){
 *                 l2++;
 *                 count++;
 *                 if(count==mid){
 *                     assign(prevNext, nums2[l2]);
 *                     isFound = true;
 *                     break;
 *                 }
 *             }
 *         }
 *
 *         if(m+n % 2 != 0){
 *             return prevNext[1];
 *         } else{
 *             return (double)(prevNext[1]+prevNext[0])/2.0;
 *         }
 *
 *
 *
 *
 *
 *     }
 *
 *     void assign(int[] prevNext, int num){
 *         prevNext[0] = prevNext[1];
 *         prevNext[1] = num;
 *     }
 *
 */

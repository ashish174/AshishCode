package algo.practice.interviewpractice.mar2025.arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 */
@Slf4j
public class Sorting {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n==0) {
            return;
        }
        if(m==0) {
            nums1 = nums2;
        }
        int firstArrIndex = m-1;
        int secondArrIndex = n-1;
        int newArrayIndex = m+n-1;
        while(firstArrIndex >=0 && secondArrIndex >= 0 ) {
            if(nums1[firstArrIndex] >= nums2[secondArrIndex]) {
                nums1[newArrayIndex] = nums1[firstArrIndex];
                firstArrIndex--;
            } else {
                nums1[newArrayIndex] = nums2[secondArrIndex];
                secondArrIndex--;
            }
            newArrayIndex--;
        }

        // In case, first array index reach -1, which means second array still has more items
        while(secondArrIndex >= 0) {
            nums1[newArrayIndex] = nums2[secondArrIndex];
            secondArrIndex--;
            newArrayIndex--;
        }

        // In case, second array index reach -1, no need to do anything, it means all items are already in first array
    }

    public static void main(String[] args){
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        log.info("nums1 {} , nums2 {}", nums1, nums2);
        Sorting sortingObj = new Sorting();
        sortingObj.merge(nums1, m, nums2, n);
        log.info("nums1 {} , nums2 {}", nums1, nums2);


    }
}

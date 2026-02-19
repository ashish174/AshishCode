package algo.practice.a_interviewpractice.neetcode150.slidingwindow;

/**
 * Given a binary array nums and an integer k,
 * return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 * Example 1:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 *
 * Example 2:
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 *
 * Approach:
 * Uses the sliding window technique to maintain a window containing at most k zeros.
 * As the window expands, counts the number of zeros; if it exceeds k, moves the left pointer to shrink the window.
 * Continuously updates the maximum length found, ensuring an O(n) time solution.
 */
public class MaxConsecutiveOnesAfterUptoKFlips {

    public int longestOnes(int[] nums, int k) {
        int left = 0;      // Left end of the sliding window
        int zeros = 0;     // Count of zeros in current window
        int maxLen = 0;    // Maximum window size found

        for (int right = 0; right < nums.length; right++) {
            // If we see a zero, increment zero count
            if (nums[right] == 0) {
                zeros++;
            }
            // If zero count exceeds k, shrink window from left
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            // Current window has at most k zeros
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }


    public int longestOnes_V1(int[] nums, int k) {
        int left = 0;
        int maxLength = 0;
        int flipAllowed = k;
        for(int right=0; right <nums.length; right++){
            if(nums[right]==1){
                maxLength = Math.max(maxLength, right-left+1);
            } else {
                // 0 case
                if(flipAllowed>0){
                    flipAllowed--;
                    maxLength = Math.max(maxLength, right-left+1);
                } else {
                    while(left<right && nums[left]!=0){
                        left++;
                    }
                    //left is pointing at 0;
                    left++;
                    //flip replenished
                    //flip used as right is at 0. so need to increase flipAllowed
                    maxLength = Math.max(maxLength, right-left+1);
                }
            }
        }

        return maxLength;

    }


}

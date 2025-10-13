package algo.practice.greedy;

import lombok.extern.slf4j.Slf4j;

/**
 * You are given an integer array nums where each element nums[i] indicates your maximum jump length at that position. For example, if you are at nums[i], you can jump to any index i + j where:
 * j <= nums[i] && i + j < nums.length
 *
 * Return true if you can reach the last index starting from index 0, or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [1,2,0,1,0]
 *
 * Output: true
 * Explanation: First jump from index 0 to 1, then from index 1 to 3, and lastly from index 3 to 4.
 *
 * Example 2:
 *
 * Input: nums = [1,2,1,0,1]
 *
 * Output: false
 */
@Slf4j
public class JumpGame {

    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }

        return goal == 0;
    }

    public static void main(String[] args){
        JumpGame jumpGame = new JumpGame();
        int[] nums = {1,2,0,1,0};
        log.info("{}", jumpGame.canJump(nums));
    }
}

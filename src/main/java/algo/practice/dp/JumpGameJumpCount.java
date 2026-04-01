package algo.practice.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * You are given an array of integers nums, where nums[i] represents the maximum length of a jump towards the right from index i. For example, if you are at nums[i], you can jump to any index i + j where:
 *
 * j <= nums[i]
 * i + j < nums.length
 * You are initially positioned at nums[0].
 *
 * Return the minimum number of jumps to reach the last position in the array (index nums.length - 1). You may assume there is always a valid answer.
 *
 * Example 1:
 *
 * Input: nums = [2,4,1,1,1,1]
 *
 * Output: 2
 * Explanation: Jump from index 0 to index 1, then jump from index 1 to the last index.
 *
 * Example 2:
 *
 * Input: nums = [2,1,2,1,0]
 *
 * Output: 2
 *
 *
 * DP formula
 * dp[0] = 0
 * dp[i] = 1 + min{ dp[j] | j < i && j + nums[j] >= i }
 *
 */
@Slf4j
public class JumpGameJumpCount {

    private JumpGameJumpCount() {
        // utility class – prevent instantiation
    }

    /* --------------------------------------------------------------- *
     *  DP implementation (O(N²))                                      *
     * --------------------------------------------------------------- */
    /**
     * Returns the minimum number of jumps using the classic DP recurrence.
     *
     * <p>Recurrence explained again:
     *
     * <pre>
     *   dp[0] = 0                               // we start at index 0 → no jump yet
     *   dp[i] = 1 + min{ dp[j] }               // among all j that can reach i
     *          where 0 ≤ j < i and j + nums[j] ≥ i
     * </pre>
     *
     * The outer loop iterates `i` from left to right, the inner loop scans all
     * earlier positions `j`.  The inner loop stops early if `j + nums[j]` is
     * already less than `i` (cannot reach) – but for clarity we keep the
     * straightforward double‑loop version.
     *
     * @param nums jump‑length array (size ≥ 1)
     * @return minimum number of jumps needed to reach the last index
     */
    public static int minJumpsDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums must be non‑empty");
        }

        final int n = nums.length;
        // dp[i] = best count to land exactly on i
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;                       // start position needs 0 jumps

        for (int i = 1; i < n; i++) {
            // Scan all earlier positions that could reach i
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        // dp[n‑1] now holds the answer
        return dp[n - 1];
    }

}

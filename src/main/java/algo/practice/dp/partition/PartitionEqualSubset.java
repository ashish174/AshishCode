package algo.practice.dp.partition;

import java.util.Arrays;

/**
 * determine whether a given set can be partitioned into two subsets
 * such that the sum of elements in both subsets is the same.
 * [1,5,11,5] => [1, 5, 5] and [11].
 * You can reduce this problem to finding a subset whose sum = totalSum/2
 * isSubSetSum[j, sum]      = isSubSetSum[j-1, sum - num[j]] || isSubSetSum[j-1, sum]       if num[j] <= sum
 * = isSubSetSum[j-1, sum]                                         if num[j] > sum
 * = true                                                             if sum == 0
 */
public class PartitionEqualSubset {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 7, 12, 9};
        System.out.println(PartitionEqualSubset.canPartitionEfficient(nums));
    }

    public static boolean canPartitionEfficient(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        if (totalSum % 2 != 0) {
            return false;
        }
        int halfOfTotalSum = totalSum / 2;
        Arrays.sort(nums);
        Boolean[][] dp = new Boolean[nums.length + 1][halfOfTotalSum + 1];
        return isSubsetSumEfficient(nums, nums.length, halfOfTotalSum, dp);
    }

    private static boolean isSubsetSumEfficient(int[] nums, int n, int total, Boolean[][] dp) {
        if (n <= 0 && total != 0) {
            return false;
        }
        if (total == 0) {
            return true;
        }
        if (dp[n - 1][total - 1] == null) {
            if (nums[n - 1] > total) {
                dp[n - 1][total - 1] = isSubsetSumEfficient(nums, n - 1, total, dp);
            } else {
                dp[n - 1][total - 1] = isSubsetSumEfficient(nums, n - 1, total, dp)
                        || isSubsetSumEfficient(nums, n - 1, total - nums[n - 1], dp);
            }
        }
        return dp[n - 1][total - 1];
    }
}

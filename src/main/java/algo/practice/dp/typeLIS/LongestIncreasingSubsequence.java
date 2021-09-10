package algo.practice.dp.typeLIS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ex: For {10, 22, 9, 33, 21, 50, 41, 60, 80},
 * LIS length is 6 and LIS is {10, 22, 33, 50, 60, 80}.
 *
 * Optimal Substructure :
 * L(i) = 1 + max( L(j) ) where 0 < j < i and arr[j] < arr[i]; or
 * L(i) = 1, if no such j exists.
 */
public class LongestIncreasingSubsequence {
    public static final Logger LOGGER = LoggerFactory.getLogger(LongestIncreasingSubsequence.class);

    public static int findLISLength(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        int LIS = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxVal = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && maxVal < (1 + dp[j])) {
                    maxVal = 1 + dp[j];
                }
            }
            dp[i] = maxVal;
            if (LIS < dp[i]) {
                LIS = dp[i];
            }
        }
        return LIS;
    }


    public static int findLISOptimized(int[] seq) {
        if (seq.length < 1) {
            return 0;
        }
        int[] dp = new int[seq.length];
        for (int i = 0; i < seq.length; i++) {
            dp[i] = 1;
        }
        int LIS = 1;
        LOGGER.info("Sequence is : {}", seq);
        for (int i = 1; i < seq.length; i++) {
            int maxLISSoFar = 1;
            System.out.print("For "+seq[i]+" :");
            for (int j = 0; j < i; j++) {
                if ((seq[j] < seq[i]) && (dp[i] < dp[j] + 1)) {
                    dp[i] = 1 + dp[j];
                    System.out.print(" "+seq[j]);
                }
            }
            System.out.println();
            LIS = Math.max(LIS, dp[i]);
        }
        return LIS;
    }

    public static void  printLIS(int[] seq){
        if (seq.length < 1) {
            return ;
        }
        //find LIS Array
        int[] dp = new int[seq.length];
        for (int i = 0; i < seq.length; i++) {
            dp[i] = 1;
        }
        int LIS = 1;
        for (int i = 1; i < seq.length; i++) {
            int maxLISSoFar = 1;
            for (int j = i - 1; j >= 0; j--) {
                if ((seq[j] < seq[i]) && (dp[i] < dp[j] + 1)) {
                    dp[i] = 1 + dp[j];
                }
            }
            LIS = Math.max(LIS, dp[i]);
        }

        // LIS size = LIS,
        // Iterate over dp[] in increasing order i.e. 1,2,3,4
        for(int k=0; k<dp.length;k++){

        }

    }


    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        LOGGER.info("LIS for nums {} is of size : {}", nums, findLISOptimized(nums));
    }


}

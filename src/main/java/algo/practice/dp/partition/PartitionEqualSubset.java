package algo.practice.dp.partition;

import java.util.Arrays;

public class PartitionEqualSubset {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1};
        System.out.println(new PartitionEqualSubset().canPartition(nums));
    }

    public boolean canPartition(int[] nums) {
        if(nums.length==0){
            return false;
        }
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }
        if(totalSum%2!=0){
            return false;
        }
        int halfOfTotalSum = totalSum/2;
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return isSubsetSum(nums, nums.length, halfOfTotalSum, dp);
    }

    boolean isSubsetSum(int[] nums, int n, int total, int[] dp){
        if(n==0 && total!=0){
            return false;
        }
        if(total==0 || total==nums[n-1]){
            dp[n-1] = 1;
            return getBooleanVal(dp[n-1]);
        }
        if(dp[n-1]!=-1){
            return getBooleanVal(dp[n-1]);
        }


        if(nums[n-1]>total){
            dp[n-1] = isSubsetSum(nums, n-1, total, dp)?1:0;
            return getBooleanVal(dp[n-1]);
        }
        dp[n-1] = (isSubsetSum(nums, n-1, total, dp)
            ||isSubsetSum(nums, n-1, total-nums[n-1], dp))?1:0;
        return getBooleanVal(dp[n-1]);
    }

    boolean getBooleanVal(int num){
        return num==1?true:false;
    }
}

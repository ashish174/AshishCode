package algo.practice.dp.typeLIS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array the task is to find largest divisible subset in array.
 * A subset is called divisible if for every pair (x, y) in subset, either x divides y or y divides x.
 * Ex:
 * arr[] = {1, 16, 7, 8, 4} -> {16 ,8 ,4 ,1}
 * Sort all array elements in increasing order. The purpose of sorting is to make sure that all divisors of an element appear before it.
 *
 * isSubsetSum (arr, n, sum/2)  = isSubsetSum (arr, n-1, sum/2) ||  isSubsetSum (arr, n-1, sum/2 - arr[n-1])
 *                              = true , if sum == 0
 *                              = false, if sum < 0, or n < 0
 *
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> subsetList = new LinkedList<>();
        if(nums==null || nums.length==0){
            return subsetList;
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] trace = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(trace, -1);
        int maxSubsetSize = 1;
        int maxSubsetindx = 0;
        for(int i=0; i<nums.length; i++){
            int lastMaxIndex = -1;
            for(int j=0; j<i; j++){
                if(nums[i]%nums[j]==0 && 1+dp[j]>dp[i]){
                    dp[i]= dp[j]+1;
                    lastMaxIndex=j;
                }
            }
            trace[i]=lastMaxIndex;
            if(maxSubsetSize<dp[i]){
                maxSubsetSize = dp[i];
                maxSubsetindx = i;
            }
        }
        subsetList.add(0, nums[maxSubsetindx]);
        int i = maxSubsetindx;
        while(trace[i]>=0){
            subsetList.add(0, nums[trace[i]]);
            i = trace[i];
        }
        return subsetList;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,9,12};
        LargestDivisibleSubset l = new LargestDivisibleSubset();
        System.out.println(l.largestDivisibleSubset(nums));
    }
}

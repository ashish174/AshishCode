package algo.practice.dp.typeLIS;

import algo.practice.sort.TwoDimensionArraySort;

import java.util.Arrays;

public class RoomBooking {
    public static void main(String[] args) {
        int[][] meetingswithStartEndTime = new int[][]{
            {1,2},
            {2,3},
            {3, 4},
        };

        System.out.println(new TwoDimensionArraySort().sort(meetingswithStartEndTime));
    }

    public int findLongestChain(int[][] pairs) {
        if(pairs.length==0){
            return 0;
        }
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        sort(pairs);
        int maxLength = 1;
        for(int i=1; i<pairs.length; i++){
            for(int j=0;j<i;j++){
                if(pairs[j][1]<pairs[i][0] && dp[i]<dp[j]+1){
                    dp[i]=1+dp[j];
                }
            }
            if(maxLength<dp[i]){
                maxLength=dp[i];
            }
        }
        return maxLength;
    }

    public int[][] sort(int[][] nums){
        int x = -1;
        int y = -1;

        for(int i=0;i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if((nums[i][0]>nums[j][0]) || (nums[i][0]==nums[j][0] && nums[i][1]>nums[j][1]))
                {
                    x=nums[i][0];
                    y=nums[i][1];
                    nums[i][0]=nums[j][0];
                    nums[i][1]=nums[j][1];
                    nums[j][0]=x;
                    nums[j][1]=y;

                }
            }
        }
        return nums;
    }
}

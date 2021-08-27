package algo.practice.dp.typeLIS;

import java.util.Arrays;

public class RussianDolls {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length==0){
            return 0;
        }
        sort(envelopes);
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int maxNumOfRussianDoll = 1;
        for(int i=1; i<envelopes.length; i++){
            for(int j=0; j<i; j++){
                if((envelopes[i][0]>envelopes[j][0] && envelopes[i][1]>envelopes[j][1]) && dp[i]<dp[j]+1)
                {
                    dp[i]=dp[j]+1;

                }
            }
            if(maxNumOfRussianDoll<dp[i]){
                maxNumOfRussianDoll = dp[i];
            }
        }
        return maxNumOfRussianDoll;
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

    public static void main(String[] args) {
        int[][] dolls = new int[][]{
            {5,4},
            {6,4},
            {6,7},
            {2,3}
        };

        System.out.println(new RussianDolls().maxEnvelopes(dolls));
    }
}

package algo.practice.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LIS {

    int findLISLength(int[] nums){
        if(nums.length<1){
            return 0;
        }
        int[] dp = new int[nums.length];
        for(int i=0; i<nums.length; i++ ){
            dp[i]=1;
        }
        int LIS = 1;
        for(int i=1; i<nums.length;i++){
            int maxVal = 1;
            for(int j=0; j<i;j++){
                if(nums[j]<nums[i] && maxVal < (1+dp[j])){
                    maxVal = 1+dp[j];
                }
            }
            dp[i]=maxVal;
            if(LIS<dp[i]){
                LIS=dp[i];
            }
        }
        return LIS;
    }



    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(new LIS().findLISLength(nums));
    }


}

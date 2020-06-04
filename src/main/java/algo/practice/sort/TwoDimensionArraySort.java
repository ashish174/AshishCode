package algo.practice.sort;

import algo.practice.dp.RussianDolls;

public class TwoDimensionArraySort {
    public static void main(String[] args) {
        int[][] dolls = new int[][]{
            {5,4},
            {6,4},
            {6,7},
            {2,3}
        };

        System.out.println(new TwoDimensionArraySort().sort(dolls));
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

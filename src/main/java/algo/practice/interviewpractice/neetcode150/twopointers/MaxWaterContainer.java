package algo.practice.interviewpractice.neetcode150.twopointers;

import lombok.extern.slf4j.Slf4j;

/**
 * You are given an integer array heights where heights[i] represents the height of the ith bar
 * You may choose any two bars to form a container. Return the maximum amount of water a container can store.
 * Example 1:
 * Input: height = [1,7,2,5,4,7,3,6]
 * Output: 36
 *
 * Example 2:
 * Input: height = [2,2,2]
 * Output: 4
 *
 *
 */
@Slf4j
public class MaxWaterContainer {
    public int maxArea(int[] heights) {
        int leftPtr = 0;
        int rightPtr = heights.length-1;
        int maxVolume = 0;
        //these will give the final position
        int finalLeftPtr = 0;
        int finalRightPtr = 0;
        while(leftPtr<rightPtr) {
            //Check water at lptr,rptr & update if water volume is more
            // breadth x lower of two height
            int volume = (rightPtr-leftPtr) * Integer.min(heights[leftPtr], heights[rightPtr]);
            if(volume > maxVolume) {
                maxVolume = volume;
                finalLeftPtr = leftPtr;
                finalRightPtr = rightPtr;
            }
            //Among 2 heights, find the lesser height, and move ahead to so as to explore other options.
            // Move the pointer that is pointing to the shorter line towards the other pointer.
            // This is because the area is limited by the shorter line, so moving the taller line wouldn't increase the area.
            if(heights[leftPtr] < heights[rightPtr]){
                leftPtr++;
            } else {
                rightPtr--;
            }
        }
        return maxVolume;
    }

    public static void main(String[] args){
        MaxWaterContainer maxWaterContainer = new MaxWaterContainer();
        log.info("{}", maxWaterContainer.maxArea(new int[]{1,7,2,5,4,7,3,6}));
    }
}

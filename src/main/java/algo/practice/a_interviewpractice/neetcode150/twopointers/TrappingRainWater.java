package algo.practice.a_interviewpractice.neetcode150.twopointers;

/**
 *
 * You are given an array of non-negative integers height which represent an elevation map. Each value height[i] represents the height of a bar, which has a width of 1.
 *
 * Return the maximum area of water that can be trapped between the bars.
 *
 * Input: height = [0,2,0,3,1,0,1,3,2,1]
 *
 * Output: 9
 *
 *
 * This class provides a solution to the Trapping Rain Water problem.
 * The problem involves calculating the amount of water that can be trapped between bars of different heights.
 *
 *      * The approach used is to maintain two arrays, prefixMax and suffixMax,
 *      * where prefixMax[i] stores the maximum height to the left of index i,
 *      * and suffixMax[i] stores the maximum height to the right of index i.
 *      *
 *      * The amount of water that can be trapped at index i is then calculated as
 *      * min(prefixMax[i], suffixMax[i]) - height[i], if this value is positive.
 *
 */
public class TrappingRainWater {
    /**
     * find the amount of water that can be trapped at a specific position i.
     * For each position, the water trapped above it depends on the tallest bar to its left and the tallest bar to its right.
     * it will be, find the greater element to the left l and the greater element to the right r of the current position i.
     * The formula for the trapped water at index i is given by: min(height[l], height[r]) - height[i], as pillar are 1m thick.
     * In case pillar is not thick, we dont have to subtract the pillar height i.e. height[i]
     *
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int[] prefixMax = new int[height.length];
        int[] suffixMax = new int[height.length];
        int currPrefixMaxSoFar = 0;
        int currSuffixMaxSoFar = 0;
        // Populate the prefixMax and suffixMax arrays in a single pass.
        for(int i=0; i < height.length; i++){
            // For the prefixMax array, store the maximum height seen so far from the left.
            prefixMax[i] = currPrefixMaxSoFar;
            currPrefixMaxSoFar = Integer.max(height[i], currPrefixMaxSoFar);

            // For the suffixMax array, store the maximum height seen so far from the right.
            suffixMax[(height.length-1)-i] = currSuffixMaxSoFar;
            currSuffixMaxSoFar = Integer.max(height[(height.length-1)-i], currSuffixMaxSoFar);
        }
        int maxWater = 0;
        // Calculate the amount of water that can be trapped at each index and sum it up.
        for(int i=0; i < height.length; i++) {
            // Calculate the amount of water that can be trapped at index i.
            int waterAtI = Integer.min(prefixMax[i], suffixMax[i]) - height[i];

            // If waterAtI is positive, add it to the total amount of water.
            if(waterAtI > 0){
                maxWater+=waterAtI;
            }
        }
        return maxWater;

    }
}

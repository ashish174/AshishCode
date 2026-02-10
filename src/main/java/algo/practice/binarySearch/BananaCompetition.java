package algo.practice.binarySearch;

/**
 *
 * You are given an integer array piles where piles[i] is the number of bananas in the ith pile. You are also given an integer h, which represents the number of hours you have to eat all the bananas.
 * You may decide your bananas-per-hour eating rate of k. Each hour, you may choose a pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, you may finish eating the pile but you can not eat from another pile in the same hour.
 * Return the minimum integer k such that you can eat all the bananas within h hours.
 *
 * Example 1:
 * Input: piles = [1,4,3,2], h = 9
 * Output: 2
 *
 * Input: piles = [25,10,23,4], h = 4
 * Output: 25
 *
 * Approach:
 *  *   - The minimum speed (k) can be 1 banana/hour.
 *  *   - The maximum speed can be the size of the largest pile.
 *  *   - k value can be between (1...largestPileSize). This means the answer lies in a sorted search space from 1 to max(piles).
 *  *   - Use binary search to find the least k for which all bananas can be eaten in <= h hours.
 *
 *
 */
public class BananaCompetition {
    /**
     * Returns the minimum eating speed (k) to finish all bananas within h hours.
     */
    public int minEatingSpeed(int[] piles, int h) {
        // Special case: If more piles than hours, it's impossible to finish.
        // (every pile requires at least one hour)
        if(piles.length>h){
            return -1;
        }
        int maxK = findMax(piles);
        //Now k value will go from 1..maxK
        // We have to find the minimum K
        //Do binary search to find the min value between 1..maxK
        int l = 1;
        int r = maxK;
        //init with largest k value
        int minK=maxK;
        while(l<=r){
            int mid = (l+r)/2;
            int timeTaken = getTimeTaken(piles, mid);
            if(timeTaken <= h){
                // If we can finish in h hours, try a slower (smaller) speed.
                //some candidate for k found
                minK = Math.min(minK, mid);
                r = mid-1;
            } else{
                // If not enough, try a faster (larger) speed.
                l = mid+1;
            }
        }
        return minK;
    }

    /**
     * Returns the maximum value from the piles array.
     * This is the largest possible value for k.
     */
    int findMax(int[] piles){
        int max=0;
        for(int heapSize: piles){
            max = Integer.max(max, heapSize);
        }
        return max;
    }

    /**
     * Given a speed 'speed', returns how many hours it would take to
     * finish all piles at that eating speed.
     *
     * For each pile, the hour required is ceil(heapSize / speed).
     */
    int getTimeTaken(int[] piles, int speed){
        int timeTaken = 0;
        for(int heapSize: piles){
            // Divide the pile with current speed and take the ceiling to account for leftovers.
            timeTaken= timeTaken + (int) Math.ceil((double) heapSize/speed);
        }
        return timeTaken;
    }

    public int findMin(int[] nums) {
        int low=0, high=nums.length-1;
        while(low <= high) {
            int mid = (low+high)/2;
            if(mid < high && nums[mid] > nums[mid+1]) {
                return nums[mid+1];
            }
            if(mid > low && nums[mid] < nums[mid-1]) {
                return nums[mid];
            }
            if(nums[low] >= nums[mid]){
                high = mid -1;
            } else {
                low = mid + 1;
            }

        }
        return 5;

    }

}

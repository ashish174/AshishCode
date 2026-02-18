package algo.practice.a_interviewpractice.neetcode150.twopointers;

/**
 * Approach :
 * start i from left end and j from right end
 */
public class TwoSumInSortedArray {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length-1;
        while(i<j) {
            int sum = numbers[i]+numbers[j];
            if(sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                return new int[] {i, j};
            }
        }
        return new int[] {-1, -1};
    }

}

// 2 sum in a non-sorted array can be done with help of HashMap.

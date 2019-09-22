package company.gs;

import java.util.Set;

public class Solution {

  public static int consecutive(long num) {
    // Write your code here
    long upperBound = (long) Math.ceil(num / 2.0) + 1;
    int consecutiveCount = 0;
    long startPointer = 0;
    long currSum = 0;
    long endPointer = 0;
    while (endPointer < upperBound) {
      if (currSum > num) {
        startPointer++;
        currSum = currSum - startPointer;
      } else if (currSum <= num) {
        if (currSum == num)
          consecutiveCount++;
        endPointer++;
        currSum = currSum + endPointer;
      }
    }
    return consecutiveCount;
  }

  public static void main(String[] args) {
    consecutive(15);
  }
}

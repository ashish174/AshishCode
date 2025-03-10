package algo.practice.google;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MajorityElement {
  public static final Logger LOGGER = LoggerFactory.getLogger(MajorityElement.class);

  public static void main(String[] args) {
    int[] A = new int[] {2, 1, 4, 4, 5, 4, 7, 4, 4};
    LOGGER.info("Majority element in Array {} is : {}", A, findMajorityElement(A));
  }

  /**
   * Since majority element count is > n/2. Hence we try replacing non matching elements with each
   * other, last remaining element will be majority element. The findMajorityElement method uses the
   * Boyer-Moore Voting Algorithm to find the majority element in an array. whenever we encounter
   * the same element as the current majority element, we increment the counter. Whenever we
   * encounter a different element, we decrement the counter.
   *
   * @param A
   * @return
   */
  public static int findMajorityElement(int[] A) {
    int count = 1;
    int majorityNumber = A[0];
    for (int i = 1; i < A.length; i++) {
      if (majorityNumber == A[i]) {
        count++;
      } else {
        // If number dont match
        if (count > 0) {
          count--;
        } else {
          majorityNumber = A[i];
          count = 1;
        }
      }
    }
    return majorityNumber;
  }
}

package company.gs;

import java.util.List;

public class CountSubsequenceWithSumN {

  static int countSubSequence(List<Integer> inputSeq, int targetSum) {
    int i = 0;
    int subSeqCount = 0;
    int sumSubSeq;
    while (i < inputSeq.size()) {
      int j = i;
      sumSubSeq = 0;
      while (j < inputSeq.size()) {
        sumSubSeq = sumSubSeq + inputSeq.get(j);
        if (sumSubSeq == targetSum) {
          subSeqCount++;
        } else if (sumSubSeq > targetSum) {
          break;
        }
        j++;
      }
      i++;
    }
    return subSeqCount;
  }

  public static void main(String[] args) {

  }
}

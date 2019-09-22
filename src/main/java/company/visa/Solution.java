package company.visa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  public static String rollTheString(String s, List<Integer> roll) {
    // Write your code here
    int[] rollForCharacterArray = new int[s.length()];
    int rollCount;
    for (int i = 0; i < roll.size(); i++) {
      rollCount = roll.get(i);
      for (int j = rollCount - 1; j >= 0; j--) {
        rollForCharacterArray[j] += 1;
      }
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (int j = 0; j < s.length(); j++) {
      char finalChar = (char) (((s.charAt(j) - 97 + rollForCharacterArray[j]) % 26) + 97);
      stringBuilder.append(finalChar);
    }
    return stringBuilder.toString();
  }

  public static void main(String[] args) {
    System.out.println("Solution: " + rollTheString("abz", Arrays.asList(2, 2, 2)));
  }

}

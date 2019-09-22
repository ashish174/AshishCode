package company.gs;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

public class Solution1 {


  public static long substringCalculator(String s) {
    // Write your code here
    Set<String> uniqueSubStrings = new HashSet<>();
    String uniqueString;
    for (int i = 0; i < s.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {
        uniqueString = s.substring(i, j);
        uniqueSubStrings.add(uniqueString);
      }
    }
    return uniqueSubStrings.size();
  }


  public static long substringCalculator222222(String s) {
    // Write your code here
    Set<Integer> uniqueHashes = new HashSet<>();
    String subString;
    for(int i=0; i<s.length(); i++){
      for(int j=i+1;j<=s.length(); j++){
        subString = s.substring(i, j);
        int hashCode = subString.hashCode();
        if (!uniqueHashes.contains(hashCode)) {
          uniqueHashes.add(hashCode);
        }
      }
    }
    return uniqueHashes.size();
  }


  public static long substringCalculator1(String s) {
    // Write your code here
    Set<String> uniqueSubStrings = new HashSet<>();
    getPermutation(s, 0, s.length(), uniqueSubStrings);
    return uniqueSubStrings.size();
  }

  static int getPermutation(String s, int i, int j, Set uniqueSubStrings){
    if(i>j)
      return 0;
    if(i==j){
      uniqueSubStrings.add(s.substring(i,j));
      return 1;

    }
    uniqueSubStrings.add(s.substring(i,j));
    return 1+getPermutation(s,i+1, j, uniqueSubStrings)+getPermutation(s, i, j-1, uniqueSubStrings);
  }

  public static void main(String[] args) {
    substringCalculator("ab");
  }

}

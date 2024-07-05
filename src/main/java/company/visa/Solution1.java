package company.visa;


import java.util.*;

public class Solution1 {

  public static List<Long> maxMin(List<String> operations, List<Integer> x) {
    // Write your code here
    List<Long> maxMinList = new ArrayList<>();
    Map<Integer, Integer> xCounts = new HashMap<>();
    SortedSet<Integer> sortedSet = new TreeSet<>();
    for (int i = 0; i < operations.size(); i++) {
      Integer element = x.get(i);
      if (operations.get(i).equals("push")) {
        addElement(xCounts, element);
        sortedSet.add(element);
      } else {
        int currentCount = removeElement(xCounts, element);
        if (currentCount == 0) {
          sortedSet.remove(element);
        }
      }
      maxMinList.add((long) (sortedSet.first() * sortedSet.last()));
    }
    return maxMinList;
  }

  private static int removeElement(Map<Integer, Integer> xCounts, Integer element) {
    Integer count = xCounts.get(element);
    count--;
    if (count == 0) xCounts.remove(element);
    return count;
  }

  private static void addElement(Map<Integer, Integer> xCounts, Integer element) {
    if (!xCounts.containsKey(element)) {
      xCounts.put(element, 1);
    } else {
      xCounts.put(element, xCounts.get(element) + 1);
    }
  }

}





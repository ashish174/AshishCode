package company.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class Solution {
    public static void main(String[] args) {
        List<String> groupTransactions = groupTransactions(Arrays.asList("can", "can", "could", "could", "alpha"));
        System.out.println(groupTransactions);
    }

    public static List<String> groupTransactions(List<String> transactions) {
        Map<String, Integer> transactionCount = new HashMap<>();
        for (int i = 0; i < transactions.size(); i++) {
            transactionCount.put(transactions.get(i), transactionCount.getOrDefault(transactions.get(i), 0) + 1);
        }
        Set<Map.Entry<String, Integer>> entrySet = transactionCount.entrySet();
        List<Map.Entry<String, Integer>> entrySetList = new ArrayList<>(entrySet);
        Collections.sort(entrySetList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return 0;
            }
        });
        Collections.sort(entrySetList, (o1, o2) -> {
            if (!o1.getValue().equals(o2.getValue())) {
                return o2.getValue() - o1.getValue();
            }
            return o1.getKey().compareTo(o2.getKey());
        });
        return entrySetList.stream().map(entry -> entry.getKey() + " " + entry.getValue())
                .collect(toList());
    }
}

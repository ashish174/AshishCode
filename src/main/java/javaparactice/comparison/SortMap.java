package javaparactice.comparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortMap {
    private static Logger logger = LoggerFactory.getLogger(SortMap.class);

    public static void main(String[] args) {
        Map<String, Integer> stringCount = new HashMap<>();
        stringCount.put("juli", 1);
        stringCount.put("ashish", 3);
        stringCount.put("anshu", 5);
        List<Map.Entry<String, Integer>> mapEntries = new ArrayList<>(stringCount.entrySet());
        logger.info("Unsorted Map entries : {}", mapEntries);
        Collections.sort(mapEntries, (o1, o2) -> {
            int compare = o1.getKey().compareTo(o2.getKey());
            return compare!=0 ? compare : o1.getValue() - o2.getValue();
        });
        logger.info("Sorted Map entries by key, value : {}", mapEntries);
    }


}

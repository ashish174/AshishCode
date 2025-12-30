package algo.practice.a_common_code_functions;

import javaparactice.collection.mycustommap.Entry;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class MapSample {

    // For < 10 entries
    Map<String, Integer> nameAgeMap = new HashMap<>(Map.of(
            "Ashish", 42,
            "Ravi", 32,
            "Bae", 32
    ));

    // For > 10 entries
    Map<Integer, String> idNameMap = Map.ofEntries(
            Map.entry(1, "Ash"),
            Map.entry(6, "Ravi"),
            Map.entry(3, "Abhi"),
            Map.entry(9, "Raas"),
            Map.entry(10, "Ram")
    );

    void sampleFunction() {

        List<String> sortedNames = nameAgeMap.entrySet().stream()
                .map(entry -> entry.getKey())
                .sorted((o1, o2) -> o1.compareTo(o2))
                .collect(Collectors.toList());
        log.info("Sorted Name is {}", sortedNames);

        //Sort by Age, then name
        List<String> sortByAgeAndName = nameAgeMap.entrySet().stream()
                .sorted((o1, o2) -> {
                    int compareByAge = o1.getValue() - o2.getValue();
                    return compareByAge != 0 ? compareByAge : o1.getKey().compareTo(o2.getKey());
                })
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        log.info("Sort by Age and Name is {}", sortByAgeAndName);





    }

    public static void main(String[] args){
        MapSample sample = new MapSample();
        sample.sampleFunction();

    }
}

package algo.practice.a_common_code_functions;

import javaparactice.collection.mycustommap.Entry;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class MapSample {

    //countMap.put(s.charAt(r), countMap.getOrDefault(s.charAt(r), 0) + 1)


    // For < 10 entries, mutable
    // Map.of is immutable, but since you have wrapped in HashMap, so new map is mutable
    Map<String, Integer> nameAgeMap = new HashMap<>(Map.of(
            "Ashish", 42,
            "Ravi", 32,
            "Bae", 32
    ));

    // For > 10 entries, immutable map
    Map<Integer, String> idNameMap = Map.ofEntries(
            Map.entry(1, "Ash"),
            Map.entry(6, "Ravi"),
            Map.entry(3, "Abhi"),
            Map.entry(9, "Raas"),
            Map.entry(10, "Ram")
    );

    //Mutable map
    Map<Integer, String> idNameMutableMap = new HashMap<>(idNameMap);

    void sampleFunction() {

        Integer unknown = nameAgeMap.getOrDefault("Unknown", 15);
        nameAgeMap.putIfAbsent("Ramesh", 40);

        nameAgeMap.containsKey("Ashish");
        nameAgeMap.containsValue(42);

        Map<String, Integer> nameAgeMapCopy = new HashMap<>(nameAgeMap);
        // copy elems
        nameAgeMapCopy.putAll(nameAgeMap);

        //compute/computeIfAbsent/computeIfPresent/merge are all thread safe and atomic operations

        //recompute the value for this key, whether it exists or not. You need to add a null check on value, in case key/value is not present.
        // put does a blind overwrite, while compute help you read old value and then update
        // map.put(k, map.get(k) + 1)    ->     map.compute(k, (k,v) -> v==null? 1 : v+1)
        // arguement = (key, value)
        nameAgeMapCopy.compute("Ramesh", (k,v) -> v == null ? 10 : v+10 );


        // If key exists → merge values. If not → just put the new value.
        // If key exist, merge value with old value using function else insert value
        nameAgeMapCopy.merge("Ritesh", 10, (k,v) -> v + 10);
        nameAgeMapCopy.merge("Ritesh", 10, Integer::sum);


        //computeIfAbsent/computeIfPresent safely update map values without manual checks like containsKey(), And if condition not satisfies it dont get executed at all.

        //For Init : Compute and insert a value only if the key is NOT already present (or mapped to null)
        // arguement = key
        nameAgeMapCopy.computeIfAbsent("Mahima", k -> 23);

        //For Update : Compute a new value only if the key IS already present and non-null
        // arguement = (key, value)
        nameAgeMapCopy.computeIfPresent("Mahima", (k,v) -> v + 6);


        // clear the map
        nameAgeMapCopy.clear();


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


        //sortByAgeAndNameCopy - Sort by Age, then name
        List<String> sortByAgeAndNameCopy = nameAgeMap.entrySet().stream()
                .sorted(Comparator
                        .comparing((Map.Entry<String, Integer> entry) -> entry.getValue())
                        .thenComparing(entry -> entry.getKey()))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        log.info("Sort by Age and Name is {}", sortByAgeAndNameCopy);


        Set<Map.Entry<String, Integer>> entries = nameAgeMap.entrySet();
        Set<String> strings = nameAgeMap.keySet();

    }

    public static void main(String[] args){
        MapSample sample = new MapSample();
        sample.sampleFunction();

    }
}

package algo.practice.a_common_code_functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorsLambdaSample {
    public static void main(String[] args){
        // For < 10 entries, immutable map
        Map<String, Integer> nameAgeMap = new HashMap<>(Map.of(
                "Ashish", 42,
                "Ravi", 32,
                "Bae", 32
        ));

        List<String> sortedNames = nameAgeMap.entrySet().stream()
                .map(entry -> entry.getKey())
                .sorted((o1, o2) -> o1.compareTo(o2))
                .collect(Collectors.toList());

        //Collectors.toList()
        // Collectors.toSet()
        // Collectors.toMap()

    }


}

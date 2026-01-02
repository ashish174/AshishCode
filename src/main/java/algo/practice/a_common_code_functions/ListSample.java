package algo.practice.a_common_code_functions;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * String → number → parseXxx()
 * number → String → String.valueOf()
 * Primitive → Wrapper → auto
 * Wrapper → Primitive → auto
 * Bigger → smaller type → cast needed
 * Array ↔ List → Arrays.asList() / toArray()
 *
 */
@Slf4j
public class ListSample {
  List<Integer> integerList = new ArrayList<>(Arrays.asList(3, 6, 7, 8));
  List<String> stringList = new ArrayList<>(Arrays.asList("Ram", "Rahim", "Peter", "Kareem"));

    void sampleFunction(){
      Collections.sort(stringList, (o1, o2) -> o1.compareTo(o2));
      log.info("Sorted String List : {}", stringList);
    }
    public static void main(String[] args){
      ListSample listSample = new ListSample();
      listSample.sampleFunction();

    }
}

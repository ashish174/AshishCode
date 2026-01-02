package algo.practice.a_common_code_functions;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class SetSample {
    Set<Integer> integerSet1 = new HashSet<>(Arrays.asList(1, 5, 7, 11));
    Set<Integer> integerSet2 = new HashSet<>(Arrays.asList(3, 5, 9));


    //set1.containsAll(set2) : whether set2 is subset of set1
    //union : set1.addAll(set2)
    //difference : set1.removeAll(set2)
    //intersection : set1.retainAll(set2)

    void sampleFunction(){
        // Union  / Add
        Set<Integer> unionSet = new HashSet<>(integerSet1);
        unionSet.addAll(integerSet2);
        log.info("unionSet: {}", unionSet);

        // Intersection / Overlap
        Set<Integer> intersectionSet = new HashSet<>(integerSet1);
        intersectionSet.retainAll(integerSet2);
        log.info("intersectionSet: {}", intersectionSet);

        // Difference / Subtract (A-B)
        Set<Integer> diffSet1 = new HashSet<>(integerSet1);
        diffSet1.removeAll(integerSet2);
        log.info("diffSet1: {}", diffSet1);
    }

    public static void main(String[] args){
        SetSample setSample = new SetSample();
        setSample.sampleFunction();
    }


}

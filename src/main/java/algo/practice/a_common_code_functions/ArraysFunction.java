package algo.practice.a_common_code_functions;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;

@Slf4j
public class ArraysFunction {
    public static void main(String[] args){
        int[] arr = {5, 9, 3, 0, 11};
        log.info("Min : {}", Arrays.stream(arr).min().getAsInt());


        int[][] arrUnsorted = { {3, 6}, {4, 7}, {1, 2}};
        log.info("{}", arrUnsorted);
        Arrays.sort(arrUnsorted, (a,b) -> a[0] - b[0] );
        Arrays.sort(arrUnsorted, Comparator.comparing((int[] pair) -> pair[0]) );
        log.info("{}", Arrays.deepToString(arrUnsorted));
    }
}

package algo.practice.a_common_code_functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsSample {

        public static void main(String[] args){
            List<Integer> list = new ArrayList<>(Arrays.asList(6,2,9,4,8,7));
            Collections.reverse(list);
            Collections.sort(list);

        }

}

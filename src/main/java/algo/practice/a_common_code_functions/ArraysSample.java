package algo.practice.a_common_code_functions;

import java.util.Arrays;
import java.util.Collections;

public class ArraysSample {
    // array [] uses length, String uses length(), Collection uses size()

    //Arrays.fill, Arrays.sort

    Integer[] arr = {1,2,3,4,5};
    Integer[][] arr2D = {{1,10}, {2, 20}, {3, 30}};

    void sample(){
        Arrays.sort(arr, Collections.reverseOrder());
        Arrays.sort(arr, ((o1,o2) -> o2-o1));


        Arrays.fill(arr, 4);
    }

    public static void main(String[] args){
        //Arrays.sort(arr);
    }



}

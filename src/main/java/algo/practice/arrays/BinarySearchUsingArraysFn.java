package algo.practice.arrays;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class BinarySearchUsingArraysFn {
    public static void main(String[] args){
        int[] arr = {1, 3, 6, 7, 9, 11, 19, 21};
        int key = 7;
        int index = Arrays.binarySearch(arr, key);
        log.info("Index of key {} is {}", key, index);
    }
}

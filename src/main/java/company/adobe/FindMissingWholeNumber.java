package company.adobe;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of unique nonnegative integers,
 * implement a function that finds the smallest nonnegative integer that is not in the array.
 *
 * [0,2,1,3] -- answer is 4
 *
 * [2,3,0,6,4] – answer is 1—here 1 and 5 both are missing but is the smaller than 5
 * [01, ]
 *
 * count = 0, 1
 *
 * 0,2,3,6,4  TC - ( nlgn + n), SC - 0
 *
 *  2 , 0     SC - {2, 3, 6, 4, 10} - O(n)
 *  3,  0     TC - {n+n} - O(n)
 *  0,  1
 *  6   1
 *  4   1
 *  10, 1
 *  1,  1
 *   ,  2
 *   ,  3
 *   ,  4
 *   ,  5
 */
public class FindMissingWholeNumber {

    public static void main(String[] args) {
        int[] numbers = new int[]{2,3,0,6,4};

    }
    public static int findSmallestMissingWholeNumber(int[] numbers){
        int currNumber = 0;
        Set<Integer> numberSet = new HashSet<>();
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i]!=currNumber){
                numberSet.add(numbers[i]);
            } else {
                currNumber++;
                while(numberSet.contains(currNumber)){
                    currNumber++;
                }
            }
        }
        return currNumber;
    }

}

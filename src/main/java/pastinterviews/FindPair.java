package pastinterviews;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * arr[] = [5,3,1,6,8], target = 9
 * out = true;
 * 2 + {3+1}
 * [1,2,3,7,10] - 6
 * 4,
 * 6,
 * 8,
 *
 * A1 = 1, 5 , 7 , 10, 11, 17 = 6
 * B1 =    3 , 5 , 9, 11,  17 = 5
 *
 */
public class FindPair {

    public static void main(String[] args) {
        System.out.println(findTripletMatchingTarget(new int[]{5, 3, 1, 6, 8}, 11));
    }

    public static boolean findTripletMatchingTarget(int[] arr, int target) {
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++) {
            int pairSumNeeded = target - arr[i];
            if(findPairMatchingTargetWithoutExtraSpace(arr, pairSumNeeded, i+1)){
                return true;
            }
        }
        return false;
    }

    public static boolean findPairMatchingTargetWithoutExtraSpace(int[] arr, int target, int st_index) {
        int i = st_index;
        int j = arr.length - 1;
        while (j > i) {
            int sum = arr[i] + arr[j];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    public static int[] findPairMatchingTargetWithoutExtraSpace(int[] arr, int target) {
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length - 1;
        while (j > i) {
            int sum = arr[i] + arr[j];
            if (sum == target) {
                return new int[]{i,j};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[1];
    }

    public static boolean findPairMatchingTarget(int[] arr, int target) {
        if (arr.length < 2) {
            return false;
        }
        Set<Integer> differenceSet = new HashSet<>();
        for (int currentElem : arr) {
            if (currentElem <= target) {
                if (!differenceSet.isEmpty() && differenceSet.contains(currentElem)) {
                    return true;
                } else {
                    differenceSet.add(target - currentElem);
                }
            }
        }
        return false;
    }




}

package algo.practice.greedy;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Input:
 *      arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}
 *      dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
 *
 *      For Arrival, increase platform count, for departure decrease platform count
 *
 *      arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}
 *      dep[] = {9:10, 11:20, 11:30, 12:00, 19:00, 20:00}
 *      Iterate like Merge process over both array,
 *          if there is arrival, increase platformCount,
 *          if there is departure, decrease platformCount.
 *          Check if max < platformCount
 *
 *
 *
 * Assume departure is sorted by time
 */
public class PlatformForTrains {
    public static final Logger LOGGER = LoggerFactory.getLogger(PlatformForTrains.class);

    public static int findMinNumOfPlatform(double[] arr, double[] dep){
        Arrays.sort(arr);
        Arrays.sort(dep);
        int i = 0;
        int j = 0;
        int maxPlatform = 0;
        int numOfPlatform = 0;
        int numOfTrain = arr.length;
        while(i < numOfTrain && j < numOfTrain){
            //for arrival case
            if(arr[i] <= dep[j]){
                numOfPlatform++;
                i++;
            } else{
                //departure case : arr[i] > dep[j]
                numOfPlatform--;
                j++;
            }
            maxPlatform = Math.max(maxPlatform, numOfPlatform);
        }
        LOGGER.info("Max Platform need for these {} trains : {}", numOfTrain, maxPlatform);
        return maxPlatform;
    }

    public static void main(String[] args) {
        double[] arr = {9.00, 9.40, 9.50, 11.00, 15.00, 18.00};
        double[] dep = {9.10, 12.00, 11.20, 11.30, 19.00, 20.00};
        findMinNumOfPlatform(arr, dep);


    }
}

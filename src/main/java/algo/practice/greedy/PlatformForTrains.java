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


    /**
     * Finds the minimum number of platforms required for a given set of train arrivals and departures.
     *
     * This method assumes that the input arrays represent the arrival and departure times of trains.
     * It sorts the input arrays and then iterates through them simultaneously, incrementing a platform counter when an arrival occurs
     * and decrementing it when a departure occurs. The maximum value of the platform counter during this
     * iteration represents the minimum number of platforms required.
     *
     */
    public static int findMinNumOfPlatform(double[] arrival, double[] departure){
        Arrays.sort(arrival);
        Arrays.sort(departure);
        int i = 0;
        int j = 0;
        int maxPlatform = 0;
        int numOfPlatform = 0;
        int numOfTrain = arrival.length;
        while(i < numOfTrain && j < numOfTrain){
            //for arrival case
            if(arrival[i] <= departure[j]){
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

    public static int findMinNumOfPlatformV2(double[] arrival, double[] departure) {
        if(arrival.length != departure.length) {
            return -1;
        }
        Arrays.sort(arrival);
        Arrays.sort(departure);
        int platformCount = 0;
        int maxPlatformSoFar = 0;
        int arrivalIndex = 0;
        int departureIndex = 0;
        while(arrivalIndex < arrival.length &&  departureIndex < departure.length) {
            if(arrival[arrivalIndex] < departure[departureIndex]) {
                platformCount++;
                maxPlatformSoFar = Math.max(maxPlatformSoFar, platformCount);
                arrivalIndex++;
            } else {
                platformCount--;
                departureIndex++;
            }
        }
        if(arrivalIndex<departureIndex) {
            LOGGER.info("Invalid departure data, as it finishes before arrival");
            return -1;
        }
        // No need to process remaining departures
        LOGGER.info("V2 - Max Platform need for these trains : {}", maxPlatformSoFar);
        return maxPlatformSoFar;
    }

    public static void main(String[] args) {
        double[] arr = {9.00, 9.40, 9.50, 11.00, 15.00, 18.00};
        double[] dep = {9.10, 12.00, 11.20, 11.30, 19.00, 20.00};
        findMinNumOfPlatform(arr, dep);
        findMinNumOfPlatformV2(arr,dep);


    }
}

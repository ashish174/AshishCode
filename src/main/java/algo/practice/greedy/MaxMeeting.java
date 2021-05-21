package algo.practice.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaxMeeting {
    private static Logger logger = LoggerFactory.getLogger(MaxMeeting.class);


    /**
     * Activity Selection problem : To select optimal number of activities
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int findMaxMeetings(int[] startTime, int[] endTime) {
        //Assuming endTime[] is sorted in increasing order
        if (startTime == null || endTime == null) {
            return 0;
        }
        int selected = 0;
        int maxMeetCount = 1;
        int noOfMeeting = endTime.length;
        logger.info("Meeting : [{}, {}]", startTime[selected], endTime[selected]);
        for (int i = 1; i < noOfMeeting; i++) {
            if (endTime[selected] <= startTime[i]) {
                selected = i;
                logger.info("Meeting : [{}, {}]", startTime[selected], endTime[selected]);
                maxMeetCount++;
            }
        }
        logger.info("Max number of Meeting : {}", maxMeetCount);
        return maxMeetCount;
    }

    public static void main(String[] args) {
        int[] startTimeList = {1, 3, 0, 5, 8, 5};
        int[] endTimeList = {2, 4, 6, 7, 9, 9};
        findMaxMeetings(startTimeList, endTimeList);
    }
}

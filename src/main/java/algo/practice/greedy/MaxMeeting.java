package algo.practice.greedy;

public class MaxMeeting {
    int findMaxMeetings(int[] startTime, int[] endTime){
        //Assuming endTime[] is sorted
        if(startTime==null || endTime==null){
            return 0;
        }
        int selected = 0;
        int maxMeetCount = 1;
        int noOfMeeting = endTime.length;
        for(int i=1; i<noOfMeeting; i++){
            if(endTime[selected]<=startTime[i]){
                selected = i;
                maxMeetCount++;
            }
        }
        return maxMeetCount;
    }
}

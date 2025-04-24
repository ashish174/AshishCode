package algo.practice.greedy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 *
 * Sort MeetingInvite by finish_Time
 * Put first meetingInvite to a new List(Room) and store its endTime = e1
 * Now Start comparing with next element.
 *      If endTime is compatible then include this Meeting invite in same room
 *      else allocate a new List(Room) and store its endTime = e1
 *
 */
@Slf4j
public class FindMinNumberOfRoomsForNMeetings {

    /**
     * Finds the minimum number of rooms required to accommodate all meetings without conflicts.
     *
     * This method takes an array of meeting invites where each invite is represented as an array of two integers,
     * the start time and end time of the meeting respectively. It sorts the meeting invites based on their end times,
     * assigns them to rooms such that no two meetings overlap in the same room, and returns the total number of rooms used.
     *
     * @param meetingInvites A 2D array where each sub-array represents a meeting invite with start and end times.
     * @return The minimum number of rooms required to accommodate all meetings without conflicts.
     */
    public int findMinimumNumberOfRoomsForMeeting(int[][] meetingInvites) {
        int[][] sortedMeetingInviteByEndTime = meetingInvites.clone();
        Arrays.sort(sortedMeetingInviteByEndTime, (a,b) -> a[1] - b[1]);
        log.info("meetingInvite sorted by endTime {}", Arrays.deepToString(sortedMeetingInviteByEndTime));
        if(meetingInvites.length==0) {
            return 0;
        }
        Map<Integer, Stack<int[]>> roomWithMeetings = new HashMap<>();
        List<int[]> roomMeetingInviteList = new LinkedList<>();
        for(int i =0; i < sortedMeetingInviteByEndTime.length; i++) {
            int[] meetingInvite = sortedMeetingInviteByEndTime[i];
            assignRoom(meetingInvite, roomWithMeetings);
        }
        doPrint(roomWithMeetings);
        return roomWithMeetings.size();
    }

    private void assignRoom(int[] meetingInvite, Map<Integer, Stack<int[]>> roomWithMeetings) {
        boolean assigned = false;
        for(int roomNumber : roomWithMeetings.keySet()) {
            // If init for first invite and first room
            if(MapUtils.isEmpty(roomWithMeetings)) {
                Stack<int[]> meetingInvites = new Stack<>();
                meetingInvites.push(meetingInvite);
                roomWithMeetings.put(1, meetingInvites);
                assigned = true;
            } else {
                int currRoomLastMeetingEndTime = roomWithMeetings.get(roomNumber).peek()[1];
                if(currRoomLastMeetingEndTime <= meetingInvite[0]){
                    roomWithMeetings.get(roomNumber).push(meetingInvite);
                    assigned = true;
                }
            }
        }
        // If meeting invite is not adjusted in any existing room.
        if(!assigned) {
            Stack<int[]> meetingInvites = new Stack<>();
            meetingInvites.push(meetingInvite);
            roomWithMeetings.put(roomWithMeetings.size()+1, meetingInvites);
        }
    }

    private void doPrint(Map<Integer, Stack<int[]>> roomWithMeetings) {
        for (Map.Entry<Integer, Stack<int[]>> entry : roomWithMeetings.entrySet()) {
            System.out.println("Room " + entry.getKey() + ":");
            for (int[] meeting : entry.getValue()) {
                System.out.println("  " + Arrays.toString(meeting));
            }
        }
    }

    public static void main(String[] args){
        int[][] meetingInvites = {{1,20},{2,10},{3,5},{4,9},{6,8}};
        log.info("meetingInvite {}", Arrays.deepToString(meetingInvites));
        FindMinNumberOfRoomsForNMeetings findMinNumberOfRoomsForNMeetings = new FindMinNumberOfRoomsForNMeetings();
        findMinNumberOfRoomsForNMeetings.findMinimumNumberOfRoomsForMeeting(meetingInvites);
    }
}

package company.oracle;



/*
Design a calendar, by giving array of meeting invite (start time and end time).
Input:
[11, 19, 0], [2, 3, 1], [3, 5, 2], [3, 6, 2], [11, 12, 0], [15, 16, 1]

H [3,5,2] [3,6,2]
M  [2,3,1] [15,16,1]
L  [11,19, 0] [11,12,0]


[2,3,1] [3,5,2] [15,16,1] [11,12,0]

priority, endTime







[[11,19,0], [2,3,1], [], [3,6]]



Output:
[11, 19], [2, 3], [3, 5] =>

Rule:

First come first serve.


startTime - [11,19]   [2,3] - endTime

  [1, 2]  [[5,6] [3,4]]   [7,8]

          [4,5]

          [[2, 3]] [3, 5]



 */



/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

import java.util.ArrayList;
import java.util.List;

class FindCompatibleMeetingInFifo {
    public static void main(String[] args) {

    }

    public static List<MeetingInvite> acceptCompatibleMeetingInviteInFIFO(List<MeetingInvite> meetingInvites) {
        List<MeetingInvite> compatibleMeetingInvites = new ArrayList<>();
        for (MeetingInvite meetingInvite : meetingInvites) {
            if (checkIfMeetingIsCompatible(meetingInvite, compatibleMeetingInvites)) {
                compatibleMeetingInvites.add(meetingInvite);
            }
        }
        return compatibleMeetingInvites;
    }


    public static boolean checkIfMeetingIsCompatible(MeetingInvite meetingInvite, List<MeetingInvite> compatibleMeetingInvites) {
        for (MeetingInvite meetingInviteListItem : compatibleMeetingInvites) {
            if (!(meetingInvite.endTime <= meetingInviteListItem.startTime || meetingInvite.startTime >= meetingInviteListItem.endTime)) {
                return false;
            }
        }
        return true;
    }

    class MeetingInvite {
        private int startTime;
        private int endTime;

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }
    }


}


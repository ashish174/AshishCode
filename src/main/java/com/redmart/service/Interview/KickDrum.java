package com.redmart.service.Interview;

import java.util.*;

public class KickDrum {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int eventDetailsCount = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

    String[] eventDetails = new String[eventDetailsCount];

    for (int eventDetailsItr = 0; eventDetailsItr < eventDetailsCount; eventDetailsItr++) {
      String eventDetailsItem = scanner.nextLine();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");
      eventDetails[eventDetailsItr] = eventDetailsItem;
    }

    findMaximumEvents(eventDetails);

    scanner.close();
  }

   static int findMaximumEvents(String[] eventDetails) {
    /*
     * Write your code here.
     */
     System.out.println("*"+eventDetails[0]+"*");
    int num_event = Integer.parseInt(eventDetails[0].trim());
    List<Event> eventList = new ArrayList();
    for(int i=1; i< eventDetails.length; i++){
      System.out.println("*"+eventDetails[i]+"*");
      String[] str = eventDetails[i].split(" ");
      Event e = new Event(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
      eventList.add(e);
    }
    Collections.sort(eventList, new sortByFinishTime());

    int max_event_count = 0;
    Event prev_event = null;
    for(Event e : eventList){
      if(prev_event==null){
        max_event_count++;
        prev_event = e;
        continue;
      }
      if(prev_event.finishTime < e.startTime){
        max_event_count++;
        prev_event = e;
      }

    }
     System.out.println(max_event_count);
  return max_event_count;

  }

   static class Event {
    int startTime;
    int finishTime;

    public Event(int startTime, int finishTime) {
      this.startTime = startTime;
      this.finishTime = finishTime;
    }



  }

  static class sortByFinishTime implements Comparator<Event>{

    @Override
    public int compare(Event o1, Event o2) {
      return o1.finishTime-o2.finishTime;
    }
  }

}

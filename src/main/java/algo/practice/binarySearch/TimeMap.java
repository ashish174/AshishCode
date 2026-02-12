package algo.practice.binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Time Based Key-Value Store
 *  * Implements a time-based key-value data structure.
 *  * Each key stores a list of (value, timestamp) pairs.
 *  * Supports retrieving the value at a key for the most recent version at or before a given timestamp.
 *
 * This is actually just a “Google Docs version history” for values,
 * where you have to return most recent value just before a given timeStamp.
 * Each version is tagged with a timestamp, so you keep a full edit history of that key’s values.
 * When you get(key, time), you open the document version that existed at that moment (or the closest earlier one).
 *
 *         if timeStamps are : 1, 4, 7, 9 ,10
 *         if we are searching at timeStamp 6 -> 4 is the nearest value
 *
 * Implement a time-based key-value data structure that supports:
 *
 * Storing multiple values for the same key at specified time stamps
 * Retrieving the key's value at a specified timestamp
 * Implement the TimeMap class:
 *
 * TimeMap() Initializes the object.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns the most recent value of key if set was previously called on it and the most recent timestamp for that key prev_timestamp is less than or equal to the given timestamp (prev_timestamp <= timestamp). If there are no values, it returns "".
 *
 *
 */
public class TimeMap {
    Map<String, List<ValueNode>> timeMap;

    public TimeMap() {
        timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        ValueNode node =  new ValueNode(value, timestamp);
        List<ValueNode> nodeList = null;
        if(!timeMap.containsKey(key)){
            nodeList = new ArrayList<>();
            nodeList.add(node);
            timeMap.put(key, nodeList);
        } else {
            timeMap.get(key).add(node);
        }
    }

    public String get(String key, int timestamp) {
        if(!timeMap.containsKey(key)){
            return "";
        }
        List<ValueNode> nodeList = timeMap.get(key);
        int l = 0;
        int r = nodeList.size() -1;
        ValueNode maxValueLessThanTS = new ValueNode("", 0) ;
        //if timeStamps are : 1, 4, 7, 9 ,10
        //if we are searching at timeStamp 6 -> 4 is the nearest value
        while(l <= r){
            int mid = (l+r)/2;
            if(nodeList.get(mid).timeStamp > timestamp){
                // Current mid timestamp is too new; search to the left
                r = mid-1;
            } else {
                // Current mid timestamp is valid; remember it and search to the right for later match
                // (we want the latest possible timestamp not exceeding query)
                maxValueLessThanTS = nodeList.get(mid).timeStamp > maxValueLessThanTS.timeStamp
                        ? nodeList.get(mid)
                        : maxValueLessThanTS;
                l = mid+1;
            }
        }
        // Return value of the found node, or "" if not found
        return maxValueLessThanTS.value;

    }

    class ValueNode{
        String value;
        int timeStamp;

        public ValueNode(String value, int timeStamp){
            this.value = value;
            this.timeStamp = timeStamp;
        }
    }
}

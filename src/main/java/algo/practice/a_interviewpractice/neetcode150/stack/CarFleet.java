package algo.practice.a_interviewpractice.neetcode150.stack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * This class provides a solution to the "Car Fleet" problem,
 * where we need to determine the number of car fleets that will arrive at a target destination.
 *
 * There are n cars traveling to the same destination on a one-lane highway.
 *
 * You are given two arrays of integers position and speed, both of length n.
 *
 * position[i] is the position of the ith car (in miles)
 * speed[i] is the speed of the ith car (in miles per hour)
 * The destination is at position target miles.
 *
 * A car can not pass another car ahead of it. It can only catch up to another car and then drive at the same speed as the car ahead of it.
 * A car fleet is a non-empty set of cars driving at the same position and same speed. A single car is also considered a car fleet.
 * If a car catches up to a car fleet the moment the fleet reaches the destination, then the car is considered to be part of the fleet.
 * Return the number of different car fleets that will arrive at the destination.
 * Input: target = 10, position = [4,1,0,7], speed = [2,2,1,1]
 * Output: 3
 *
 * Approach:
 * Calculates the time each car needs to reach the target and sorts the cars based on their starting position (closest to target first).
 * Processes cars from front to back, using a stack to track fleet leaders: a car forms a new fleet if it takes longer than all cars ahead to reach the target;
 * otherwise, it joins an existing fleet. The stack size at the end reflects the total number of fleets.
 *
 * The stack is used to keep track of the time it takes for each car fleet to reach the target.
 * As cars are processed from closest to farthest from the target, the stack stores the arrival times of fleet leaders.
 * If a car’s time to reach the target is greater than the current fleet's (on top of the stack),
 * it can't catch up and forms a new fleet—so its time is pushed onto the stack.
 * Otherwise, it catches up and joins the current fleet, and nothing is pushed.
 * At the end, the stack’s size gives the total number of car fleets.
 */
public class CarFleet {

  public int carFleet(int target, int[] position, int[] speed) {
    if (position.length == 0) {
      return 0;
    }
    float[][] remainingDistanceAndTime = new float[position.length][2];
    for (int i = 0; i < position.length; i++) {
      // remaining distance from target for each car
      remainingDistanceAndTime[i][0] = target - position[i];
      // remaining time from target for each car
      remainingDistanceAndTime[i][1] = remainingDistanceAndTime[i][0] / speed[i];
    }
      // Sort the cars based on their remaining distance to the target (i.e., their position on the highway)(closest to target first)
      // This is done so that we can process the cars from front to back - least distance from target to more distance
      Arrays.sort(remainingDistanceAndTime, Comparator.comparingDouble(rowElem -> rowElem[0]));

      // Use a stack to keep track of the time it takes for all car fleet to reach the target
        Stack<Float> timeUpperBoundForFleet = new Stack<>();
        timeUpperBoundForFleet.push(remainingDistanceAndTime[0][1]);
        for (int l = 0; l < remainingDistanceAndTime.length; l++) {
            // If the current car takes more time to reach the target than the previous car fleet,
            // it means the current car will not catch up to the previous car fleet, so it forms a new fleet

            if (remainingDistanceAndTime[l][1] > timeUpperBoundForFleet.peek()) {
              //add time for new/next fleet
                timeUpperBoundForFleet.push(remainingDistanceAndTime[l][1]);
            }
            //else If the current car takes more time to reach the target than the previous car fleet,
            // then it will become part of previous fleet
            // So no need to push anything at stack. stack.peek() will represent that fleet
        }
      // The number of car fleets is equal to the number of elements in the stack
      return timeUpperBoundForFleet.size();
  }
}

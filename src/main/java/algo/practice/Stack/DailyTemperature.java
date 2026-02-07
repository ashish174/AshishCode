package algo.practice.Stack;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 *
 * You are given an array of integers temperatures where temperatures[i] represents the daily temperatures on the ith day.
 *
 * Return an array result where result[i] is the number of days after the ith day before a warmer temperature
 * appears on a future day. If there is no day in the future where a warmer temperature will appear for the ith day,
 * set result[i] to 0 instead.
 *
 * Input: temperatures = [30,38,30,36,35,40,28]
 *
 * Output: [1,4,1,2,1,0,0]
 *
 */
@Slf4j
public class DailyTemperature {
  /**
   *  You stand on a particular day, and check all previous days which are in stack.
   *  Now you check if stack top is less than particular day, then it is first warmer day for stack top.
   *  You can keep popping stack, until stack.pop temp < particular day temp
   *
   *  stack is used to keep temperature in a decreasing order, and it store the day index
   *  Ex: days with temperature [2, 1, 1]
   * @param temperatures
   * @return
   */
  public int[] dailyTemperatures(int[] temperatures) {
        if(temperatures.length==0){
            return new int[0];
        }
      // Stack to keep track of indices of previous days (stack stores indices)
      Stack<Integer> prefixDayIndexStack = new Stack<>();
        int[] warmerDaysGap = new int[temperatures.length];
        prefixDayIndexStack.push(0);
        for(int j=1; j<temperatures.length;j++){
            // While stack is not empty and the temp at stack's top is less than current day's temp
            // this means current day is the next warmer day for the days in the stack
            while(!prefixDayIndexStack.isEmpty()
                    && temperatures[prefixDayIndexStack.peek()] < temperatures[j]) {
                int day = prefixDayIndexStack.pop();
                warmerDaysGap[day] = j-day;
                log.info("First warm day for {} : {}", temperatures[day], temperatures[j]);
            }
            // Push the current day's index onto the stack for future comparison
            prefixDayIndexStack.push(j);
        }
      // For any remaining days in the stack, there was no warmer day in the future,
      // so we set their answer to 0
        while(!prefixDayIndexStack.isEmpty()){
            int day = prefixDayIndexStack.pop();
            warmerDaysGap[day] = 0;
        }
        return warmerDaysGap;
    }

    public static void main(String[] args){
        int[] temperatures = new int[]{30,38,30,36,35,40,28};
        log.info("{} : {}", temperatures, new DailyTemperature().dailyTemperatures(temperatures));
    }


}

package algo.practice.arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReverseInteger {
    private static Logger logger = LoggerFactory.getLogger(ReverseInteger.class);
    private static int reverseNum = 0;

    public static void main(String[] args) {
        int num = 23761; //56732
        logger.info("Reverse of {} : {}", num, reverseInteger(num));
        logger.info("Recursive Reverse of {} : {}", num, reverseIntegerRecursive(num));
        logger.info("RecursiveV2 Reverse of {} : {}", num, reverseIntegerRecursiveV2(num, 0));


    }


    /**
     * Reverses an integer by iterating through its digits from right to left,
     * appending each digit to the result, and removing it from the original number.
     *
     * For example, reversing the number 12345 would result in 54321.
     *
     * @param number the integer to be reversed
     * @return the reversed integer
     */
    public static int reverseInteger(int number){
        int reverseNum = 0;
        while(number > 0){
            reverseNum = reverseNum*10 + number % 10;
            number = number/10;
        }
        return reverseNum;
    }

    /**
     * Recursively reverses an integer by appending each digit at the end of
     * the reversed number and removing it from the original number.
     *
     * Note: This implementation uses a static variable 'reverseNum' to store
     *       the reversed number across recursive calls. It may not be suitable
     *       for multi-threaded environments or when multiple invocations are needed.
     *
     * @param number the integer to be reversed
     * @return the reversed integer
     */
    public static int reverseIntegerRecursive(int number){
        if(number==0){
            return reverseNum;
        }
        reverseNum = reverseNum*10 + number%10;
        reverseIntegerRecursive(number/10);
        return reverseNum;
    }

    public static int reverseIntegerRecursiveV2(int number, int reverseNumber) {
        if(number == 0) {
            return reverseNumber;
        } else {
            reverseNumber = reverseNumber*10 + number%10;
            return reverseIntegerRecursiveV2(number/10, reverseNumber);
        }
    }
}

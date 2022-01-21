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


    }

    public static int reverseInteger(int number){
        int reverseNum = 0;
        while(number > 0){
            reverseNum = reverseNum*10 + number % 10;
            number = number/10;
        }
        return reverseNum;
    }

    public static int reverseIntegerRecursive(int number){
        if(number==0){
            return reverseNum;
        }
        reverseNum = reverseNum*10 + number%10;
        reverseIntegerRecursive(number/10);
        return reverseNum;
    }
}

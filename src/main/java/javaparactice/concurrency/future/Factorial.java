package javaparactice.concurrency.future;


import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Factorial {
    private static Logger logger = LoggerFactory.getLogger(Factorial.class);

    public static int findFactorial(int n){
        Stopwatch stopwatch = Stopwatch.createStarted();
        int fact = calculate(n);
        logger.info("Factorial {} , Time Elapsed : {} ms", fact, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return fact;
    }
    public static int calculate(int n){
        if(n==0 || n==1){
            return 1;
        }
        return calculate(n - 1) + calculate(n - 2);
    }
}

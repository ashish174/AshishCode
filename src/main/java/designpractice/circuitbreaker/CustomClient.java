package designpractice.circuitbreaker;


import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class CustomClient {

    private static final int FULL_OPEN_DURATION_IN_SEC = 60;
    private static final int FULL_OPEN_FAILING_REQUEST_THRESHOLD = 5;

    private static final int HALF_OPEN_DURATION_IN_SEC = 30;
    private static final int HALF_OPEN_PASSING_REQUEST_THRESHOLD = 2;

    int CallServiceB(boolean shouldPass){
        if(shouldPass) {
            log.info("yes");
        }
        return 0;
    }
}

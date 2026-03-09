package lldcodingRound.oopsdesign.utils;

import java.util.Date;

public class CommonUtils {
    public static Long generateUniqueIdentifier(){
        long epoch = new Date().getTime();
        return epoch;
    }
}

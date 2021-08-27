package oopsdesign.utils;

import java.math.BigInteger;
import java.util.Date;

public class CommonUtils {
    public static Long generateUniqueIdentifier(){
        long epoch = new Date().getTime();
        return epoch;
    }
}

package rateLimiter;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sometime customer want to hit more requests in short duration and then become idle. So we should allow these cases
 * where we should restrict limit on larger duration like Hour/Day.
 */
public class AnshuRateLimiter {
    public static Map<String, Map<DurationType, RateLimitInfo>> rateLimitMap = new ConcurrentHashMap<>();

    private static final int SECOND_DURATION_REQUEST_LIMIT = 10;
    private static final int MINUTE_DURATION_REQUEST_LIMIT = 30;
    private static final int HOUR_DURATION_REQUEST_LIMIT = 100;
    private static final int DAY_DURATION_REQUEST_LIMIT = 500;


    public static boolean checkIFRequestAllowed(String ip) {
        long currentTime = new Date().getTime();
        if (!rateLimitMap.containsKey(ip)) {
            long ttlForSecond = currentTime + DurationType.S.getDurationInSeconds() * 1000;
            long ttlForMinute = currentTime + DurationType.M.getDurationInSeconds() * 1000;
            long ttlForHour = currentTime + DurationType.H.getDurationInSeconds() * 1000;
            long ttlForDay = currentTime + DurationType.D.getDurationInSeconds() * 1000;
            Map<DurationType, RateLimitInfo> durationMap = new ConcurrentHashMap<>();
            durationMap.put(DurationType.S, new RateLimitInfo(ip, ttlForSecond, 1));
            durationMap.put(DurationType.S, new RateLimitInfo(ip, ttlForMinute, 1));
            durationMap.put(DurationType.S, new RateLimitInfo(ip, ttlForHour, 1));
            durationMap.put(DurationType.S, new RateLimitInfo(ip, ttlForDay, 1));
            return true;
        } else {


        }
        return true;
    }
}



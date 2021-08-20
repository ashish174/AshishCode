package rateLimiter;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AshishRateLimiter {
    public static Map<String, RateLimitInfo> rateLimitInfoMap = new ConcurrentHashMap<>();

    private static final int REQUEST_WINDOW_SHORT_DURATION_IN_MINUTE = 1;

    boolean checkIfRequestIsAllowed(String ip) {
        long currentTimeStamp = new Date().getTime();
        long ttl;
        if (!rateLimitInfoMap.containsKey(ip)) {
            // if ip is hitting for first time, include it in cache
            ttl = currentTimeStamp + REQUEST_WINDOW_SHORT_DURATION_IN_MINUTE * 60 * 1000;
            rateLimitInfoMap.put(ip, new RateLimitInfo(ip, ttl, 1));
            return true;
        } else {
            // if ip is already in cache
            RateLimitInfo rateLimitInfo = rateLimitInfoMap.get(ip);
            if (currentTimeStamp > rateLimitInfo.getTimeToLeave()) {
                // Window has expired. create a new window with new ttl
                ttl = currentTimeStamp + REQUEST_WINDOW_SHORT_DURATION_IN_MINUTE * 60 * 1000;
                rateLimitInfo.setTimeToLeave(ttl);
                rateLimitInfo.setRequestCount(1);
                return true;
            } else {
                // Check if new request in within thresold limit
                int requestCount = rateLimitInfo.getRequestCount();
                rateLimitInfo.setRequestCount(++requestCount);
                return false;
            }
        }
    }

}

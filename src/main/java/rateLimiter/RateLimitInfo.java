package rateLimiter;

public class RateLimitInfo {
    private String ip;
    private long timeToLeave;
    private int requestCount;

    public RateLimitInfo(String ip, long timeToLeave, int requestCount) {
        this.ip = ip;
        this.timeToLeave = timeToLeave;
        this.requestCount = requestCount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getTimeToLeave() {
        return timeToLeave;
    }

    public void setTimeToLeave(long timeToLeave) {
        this.timeToLeave = timeToLeave;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }
}

package rateLimiter;

public enum DurationType {
    S(1),M(60),H(3600),D(86400);
    int duration;
    DurationType(int i) {
        duration = i;
    }

    int getDurationInSeconds(){
       return duration;
    }
}

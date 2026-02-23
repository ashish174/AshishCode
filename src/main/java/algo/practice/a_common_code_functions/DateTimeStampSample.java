package algo.practice.a_common_code_functions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateTimeStampSample {

    /**
     * Date & TimeStamp are part of legacy API
     * Add/Subtract few days/month/hour/second to a date
     */
    void doDateOperationsUsingLegacyAPIs() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // Add 2 days
        cal.add(Calendar.DATE, 2);

        // Subtract 3 months
        cal.add(Calendar.MONTH, -3);

        // Add 4 hours
        cal.add(Calendar.HOUR_OF_DAY, 4);

        // Subtract 15 seconds
        cal.add(Calendar.SECOND, -15);

        Date resultDate = cal.getTime();

    }

    /**
     * Java8 shifted on java.time :- LocalDate, LocalDateTime, ZonedDateTime, Instant.
     * Add/Subtract few days/month/hour/second to a date
     */
    void doDateOperationsUsingJava8APIs(){
        LocalDate date = LocalDate.now();

        // Add 2 days
        LocalDate plusDays = date.plusDays(2);

        // Subtract 3 months
        LocalDate minusMonths = date.minusMonths(3);



        LocalDateTime dateTime = LocalDateTime.now();

        // Add 4 hours
        LocalDateTime plusHours = dateTime.plusHours(4);

        // Subtract 15 seconds
        LocalDateTime minusSeconds = dateTime.minusSeconds(15);
    }
    public static void main(String[] args){
        DateTimeStampSample dateTimeStampSample = new DateTimeStampSample();
        dateTimeStampSample.doDateOperationsUsingLegacyAPIs();
        dateTimeStampSample.doDateOperationsUsingJava8APIs();
    }

}
